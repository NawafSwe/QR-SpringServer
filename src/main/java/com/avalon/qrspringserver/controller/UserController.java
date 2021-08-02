package com.avalon.qrspringserver.controller;

import com.avalon.qrspringserver.model.UserModel;
import com.avalon.qrspringserver.repository.UserRepository;
import com.avalon.qrspringserver.security.SecurityConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.avalon.qrspringserver.security.SecurityConstants.HEADER_NAME;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserRepository userRepository;
    private final SecurityConfig security;

    public UserController(UserRepository userRepository, SecurityConfig security) {
        this.userRepository = userRepository;
        this.security = security;
    }


    @PostMapping(path = "/register")
    public String postUser(@RequestBody UserModel user) {
        // TODO: check user db if email exist
        user.setPassword(security.encoder().encode(user.getPassword()));
        // TODO: send user with jwt
        // register user
        userRepository.save(user);
        return "Registered";
    }

    // users/api/secure
    @GetMapping(path = "/api/secure")
    public String secure() {
        return "You are authenticated";
    }

}
