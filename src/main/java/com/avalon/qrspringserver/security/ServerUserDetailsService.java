package com.avalon.qrspringserver.security;

import com.avalon.qrspringserver.model.UserModel;
import com.avalon.qrspringserver.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class ServerUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public ServerUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel findUser = repository.findUserByEmail(email);
        if (findUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(findUser.getEmail(), findUser.getPassword(), emptyList());
    }
}
