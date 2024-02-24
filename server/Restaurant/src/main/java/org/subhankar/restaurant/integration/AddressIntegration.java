package org.subhankar.restaurant.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.subhankar.restaurant.model.DO.Address;
import org.subhankar.restaurant.model.DTO.Result;

import java.util.List;

@FeignClient(name = "ADDRESS-SERVICE")
public interface AddressIntegration {
    @PostMapping("/address")
    Result createAddress(@RequestBody Address address);

    @GetMapping("/address/find")
    List<Address> getAddressByIdentifier(@RequestParam String identifier);
}
