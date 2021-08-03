package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.error.userErrors.UserDuplicatedEmail;
import com.avalon.qrspringserver.model.Admin;
import com.avalon.qrspringserver.model.Restaurant;
import com.avalon.qrspringserver.model.UserModel;
import com.avalon.qrspringserver.repository.AdminRepository;
import com.avalon.qrspringserver.repository.RestaurantRepository;
import com.avalon.qrspringserver.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

import static com.avalon.qrspringserver.security.SecurityConstants.HEADER_NAME;


@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RestaurantRepository restaurantRepository;
    private final AdminRepository adminRepository;

    public UserController(UserRepository userRepository, RestaurantRepository restaurantRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
        this.restaurantRepository = restaurantRepository;
        this.adminRepository = adminRepository;
    }

    /**
     * @description register normal user by admin
     * it requires an authentication by the admin // need to be moved into the restaurant controller, we need to pass the email or id of restaurant
     * then find restaurant and add user then save it into restaurant
     */
    @PostMapping(path = "/register")
    public String postUser(@RequestBody UserModel user, HttpServletRequest req) {
        String[] chunks = req.getHeader(HEADER_NAME).split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        // payload contains the email of the user
        String payload = new String(decoder.decode(chunks[1]));
        payload = payload.substring(payload.indexOf(":") + 1, payload.indexOf(","));
        payload = payload.substring(1, payload.length() - 1);
        Admin findAdmin = adminRepository.findAdminByEmail(payload);
        UserModel findUser = userRepository.findUserByEmail(user.getEmail());
        if (findUser != null) {
            throw new UserDuplicatedEmail("given user email is already in user");
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            // register user
            userRepository.save(user);
            // add user to restaurant
            findAdmin.getRestaurant().getUsers().add(user);
            restaurantRepository.save(findAdmin.getRestaurant());
            adminRepository.save(findAdmin);
            return "user Added to restaurant successfully";
        }
    }

    /**
     * @description register user of type admin
     */

    // users/api/secure
    @PostMapping(path = "/admin/register")
    public String registerAdminWithRestaurant(@RequestBody Admin admin) {
        UserModel findUser = userRepository.findUserByEmail(admin.getEmail());
        if (findUser != null) {
            // throw an error
            throw new UserDuplicatedEmail("given user email is already in user");
        } else {
            admin.setPassword(encoder.encode(admin.getPassword()));
            Restaurant holdRestaurant = admin.getRestaurant();
            admin.setRestaurant(null);
            userRepository.save(admin);
            // set restaurant admin
            admin.setRestaurant(holdRestaurant);
            restaurantRepository.save(holdRestaurant);
            // save restaurant with all info
            restaurantRepository.save(admin.getRestaurant());
            // save admin again
            return "Registered";
        }
    }


    @GetMapping(path = "api/v/secure/getAllUsers")
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

}
