package org.subhankar.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subhankar.restaurant.model.DO.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
}
