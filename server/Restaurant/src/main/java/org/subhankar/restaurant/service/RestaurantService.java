package org.subhankar.restaurant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.subhankar.restaurant.model.DO.Address;
import org.subhankar.restaurant.model.DO.Restaurant;
import org.subhankar.restaurant.model.DTO.Result;

public interface RestaurantService {

    Result getRestaurantById(String id) throws JsonProcessingException;
    Result getAllRestaurants();
    Result createRestaurant(Restaurant restaurant);
    Result updateRestaurant(String id, Restaurant restaurant);
    Result deleteRestaurant(String id);

    Result addAddress(String id, Address address);
}
