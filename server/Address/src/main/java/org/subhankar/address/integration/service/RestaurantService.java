package org.subhankar.address.integration.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.subhankar.address.model.DTO.Result;

@FeignClient(name = "RESTAURANT-SERVICE")
public interface RestaurantService {
    @GetMapping("/restaurant/basic/{id}")
    Result getRestaurantBasicInfo(@PathVariable String id);
}
