package com.avalon.qrspringserver.repository;

import com.avalon.qrspringserver.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    public Restaurant findByEmail(String email);
}
