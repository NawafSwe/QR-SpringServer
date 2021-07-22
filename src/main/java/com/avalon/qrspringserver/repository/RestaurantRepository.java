package com.avalon.qrspringserver.repository;

import com.avalon.qrspringserver.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
}
