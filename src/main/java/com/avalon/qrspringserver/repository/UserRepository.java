package com.avalon.qrspringserver.repository;

import com.avalon.qrspringserver.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserModel, String> {
    public UserModel findUserByEmail(String email);

    public void deleteUserByEmail(String email);
}
