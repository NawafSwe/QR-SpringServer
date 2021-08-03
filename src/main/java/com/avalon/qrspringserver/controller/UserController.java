package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.error.userErrors.UserDuplicatedEmail;
import com.avalon.qrspringserver.model.Admin;
import com.avalon.qrspringserver.model.UserModel;
import com.avalon.qrspringserver.repository.RestaurantRepository;
import com.avalon.qrspringserver.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RestaurantRepository restaurantRepository;

    public UserController(UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * @description register normal user by admin
     * it requires an authentication by the admin // need to be moved into the restaurant controller, we need to pass the email or id of restaurant
     * then find restaurant and add user then save it into restaurant
     */
    @PostMapping(path = "/register")
    public String postUser(HttpServletRequest req) {
        System.out.println(req);
        try {
            UserModel user = new ObjectMapper().readValue(req.getReader(), UserModel.class);
            System.out.println(user);
            // TODO: check user db if email exist
            UserModel findUser = userRepository.findUserByEmail(user.getEmail());
            if (findUser != null) {
                throw new UserDuplicatedEmail("given user email is already in user");
            } else {
                user.setPassword(encoder.encode(user.getPassword()));
                // TODO: send user with jwt
                // register user
                userRepository.save(user);
                return "Registered";
            }

        } catch (IOException error) {
            return "";
        }
    }

    /**
     * @description register user of type admin
     */

    // users/api/secure
    @GetMapping(path = "admin/register")
    public String registerAdminWithRestaurant(@RequestBody Admin admin) {
        UserModel findUser = userRepository.findUserByEmail(admin.getEmail());
        if (findUser != null) {
            // throw an error
            throw new UserDuplicatedEmail("given user email is already in user");
        } else {
            admin.setPassword(encoder.encode(admin.getPassword()));
            // register admin
            userRepository.save(admin);
            // set restaurant admin
            admin.getRestaurant().setAdmin(admin);
            // save restaurant with all info
            restaurantRepository.save(admin.getRestaurant());
            return "Registered";
        }
    }


    @GetMapping(path = "api/v/secure/getAllUsers")
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

}
