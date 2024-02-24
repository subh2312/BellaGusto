package org.subhankar.restaurant.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.subhankar.restaurant.model.DO.Address;
import org.subhankar.restaurant.model.DO.Restaurant;
import org.subhankar.restaurant.model.DTO.Result;
import org.subhankar.restaurant.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public Result addRestaurant(@RequestBody Restaurant restaurant){
        return restaurantService.createRestaurant(restaurant);
    }

    @PostMapping("/{id}/address")
    public Result addAddress(@PathVariable(name = "id")String id, @RequestBody Address address){
        return restaurantService.addAddress(id,address);
    }

    @GetMapping("/{id}")
    public Result getRestaurantById(@PathVariable(name = "id")String id) throws JsonProcessingException {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping
    public Result getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @PutMapping("/{id}")
    public Result updateRestaurant(@PathVariable(name = "id")String id, @RequestBody Restaurant restaurant){
        return restaurantService.updateRestaurant(id,restaurant);
    }

    @DeleteMapping("/{id}")
    public Result deleteRestaurant(@PathVariable(name = "id")String id){
        return restaurantService.deleteRestaurant(id);
    }


}
