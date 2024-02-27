package org.subhankar.menuitem.integration.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.subhankar.menuitem.model.DTO.Result;

@FeignClient(name = "RESTAURANT-SERVICE")
public interface RestaurantService {
    @GetMapping("/restaurant/basic/{id}")
    Result getRestaurantBasicInfo(@PathVariable String id);
}
