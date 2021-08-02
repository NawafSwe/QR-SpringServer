package com.avalon.qrspringserver.repository;

import com.avalon.qrspringserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    public User findUserByEmail(String email);

    public void deleteUserByEmail(String email);
}
