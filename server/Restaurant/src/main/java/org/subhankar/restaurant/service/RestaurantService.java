package org.subhankar.restaurant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.subhankar.restaurant.model.DO.Address;
import org.subhankar.restaurant.model.DO.Restaurant;
import org.subhankar.restaurant.model.DTO.Result;

public interface RestaurantService {

    Result getRestaurantById(String id, HttpServletRequest request) throws JsonProcessingException;
    Result getAllRestaurants(HttpServletRequest request);
    Result createRestaurant(Restaurant restaurant, HttpServletRequest request);
    Result updateRestaurant(String id, HttpServletRequest request, Restaurant restaurant);
    Result deleteRestaurant(String id, HttpServletRequest request);

    Result changeOwner(String id, HttpServletRequest request, String owner);

    Result changeStatus(String id, HttpServletRequest request, String status);
}
