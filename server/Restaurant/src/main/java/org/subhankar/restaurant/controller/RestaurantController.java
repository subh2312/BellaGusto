package org.subhankar.restaurant.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
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
    public Result addRestaurant(@RequestBody Restaurant restaurant, HttpServletRequest request){
        return restaurantService.createRestaurant(restaurant,request);
    }

    @GetMapping("/{id}")
    public Result getRestaurantById(@PathVariable(name = "id")String id,HttpServletRequest request) throws JsonProcessingException {
        return restaurantService.getRestaurantById(id, request);
    }

    @GetMapping("/basic/{id}")
    public Result getRestaurantBasicInfo(@PathVariable(name = "id")String id) throws JsonProcessingException {
        return restaurantService.getRestaurantBasicInfo(id);
    }

    @GetMapping
    public Result getAllRestaurants(HttpServletRequest request){
        return restaurantService.getAllRestaurants(request);
    }

    @PutMapping("/{id}")
    public Result updateRestaurant(@PathVariable(name = "id")String id, HttpServletRequest request, @RequestBody Restaurant restaurant){
        return restaurantService.updateRestaurant(id,request,restaurant);
    }

    @DeleteMapping("/{id}")
    public Result deleteRestaurant(@PathVariable(name = "id")String id, HttpServletRequest request){
        return restaurantService.deleteRestaurant(id,request);
    }

    @GetMapping("owner/{id}")
    public Result changeOwner(@PathVariable(name = "id")String id, HttpServletRequest request, @RequestParam String owner){
        return restaurantService.changeOwner(id,request,owner);
    }

    @GetMapping("/status/{id}")
    public Result changeStatus(@PathVariable(name = "id")String id, HttpServletRequest request, @RequestParam String status){
        return restaurantService.changeStatus(id,request,status);
    }


}
