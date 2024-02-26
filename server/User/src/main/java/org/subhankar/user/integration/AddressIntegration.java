package org.subhankar.user.integration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.subhankar.user.model.DO.Address;
import org.subhankar.user.model.DTO.Result;

import java.util.List;

@FeignClient(name = "ADDRESS-SERVICE", url = "http://localhost:9093")
public interface AddressIntegration {
    @PostMapping("/address")
    Result createAddress(@RequestBody Address address);

    @GetMapping("/address/find")
    List<Address> getAddressByIdentifier(@RequestParam String identifier);

    @PutMapping("/address/{id}")
    Result updateAddress(@PathVariable String id,@RequestBody Address address);

    @DeleteMapping("/address/{id}")
    Result deleteAddress(@PathVariable String id);

    @GetMapping("/address/{id}")
    Address getAddressById(@PathVariable String id);
}
