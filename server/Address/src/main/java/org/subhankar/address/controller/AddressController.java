package org.subhankar.address.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.subhankar.address.model.DO.Address;
import org.subhankar.address.model.DTO.Result;
import org.subhankar.address.service.AddressService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{id}")
    public Result getAddress(@PathVariable String id){
        return addressService.getAddress(id);
    }

    @GetMapping("/city/{city}")
    public Result getAddressByCity(@PathVariable String city){
        return addressService.getAddressByCity(city);
    }

    @GetMapping("/state/{state}")
    public Result getAddressByState(@PathVariable String state){
        return addressService.getAddressByState(state);
    }

    @GetMapping("/country/{country}")
    public Result getAddressByCountry(@PathVariable String country){
        return addressService.getAddressByCountry(country);
    }

    @GetMapping("/zipCode/{zipCode}")
    public Result getAddressByZipCode(@PathVariable String zipCode){
        return addressService.getAddressByZipCode(zipCode);
    }

    @PostMapping
    public Result createAddress(@RequestBody Address address){
        return addressService.createAddress(address);
    }

    @PutMapping("/{id}")
    public Result updateAddress(@PathVariable String id,@RequestBody Address address){
        return addressService.updateAddress(id,address);
    }

    @DeleteMapping("/{id}")
    public Result deleteAddress(@PathVariable String id){
        return addressService.deleteAddress(id);
    }

    @GetMapping()
    public Result getAllAddress(){
        return addressService.getAllAddress();
    }


    @GetMapping("/find")
    public List<Address> getAddressByIdentifier(@RequestParam String identifier){
        return addressService.getAddressByIdentifier(identifier);
    }
}
