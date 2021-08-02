package com.avalon.qrspringserver.security;

import com.avalon.qrspringserver.model.UserModel;
import com.avalon.qrspringserver.repository.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class ServerUserDetailsService implements UserDetailsService {
    private UserRepository repository;

    public ServerUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }


    /**
     * This example is a service with a secure method. If Spring creates a @Bean of this type,
     * it is proxied and callers have to go through a security interceptor before the method is actually executed.
     * If access is denied, the caller gets an AccessDeniedException instead of the actual method result.
     */
    @Secured("Admin")
    public boolean secure() {
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel findUser = repository.findUserByEmail(email);
        System.out.println("user found ===> " + findUser.toString());
        if (findUser == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(findUser.getEmail(), findUser.getPassword(), emptyList());
    }
}
