package com.avalon.qrspringserver.repository;

import com.avalon.qrspringserver.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {

    Admin findAdminByEmail(String email);

}
