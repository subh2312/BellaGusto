package org.subhankar.restaurant.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.subhankar.restaurant.config.FeignConfig;
import org.subhankar.restaurant.model.DO.Menu;
import org.subhankar.restaurant.model.DTO.Result;

@FeignClient(name = "MENU-SERVICE",configuration = FeignConfig.class)
public interface MenuService {
    @GetMapping("/menu/restaurant/{id}")
    Menu getMenu(@PathVariable(name = "id") String id);

}
