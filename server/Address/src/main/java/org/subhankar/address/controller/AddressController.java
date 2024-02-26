package org.subhankar.address.controller;

import jakarta.servlet.http.HttpServletRequest;
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
    public Result getAddress(@PathVariable String id, HttpServletRequest request){
        return addressService.getAddress(id,request);
    }

    @GetMapping("/city/{city}")
    public Result getAddressByCity(@PathVariable String city, HttpServletRequest request){
        return addressService.getAddressByCity(city,request);
    }

    @GetMapping("/state/{state}")
    public Result getAddressByState(@PathVariable String state, HttpServletRequest request){
        return addressService.getAddressByState(state,request);
    }

    @GetMapping("/country/{country}")
    public Result getAddressByCountry(@PathVariable String country, HttpServletRequest request){
        return addressService.getAddressByCountry(country,request);
    }

    @GetMapping("/zipCode/{zipCode}")
    public Result getAddressByZipCode(@PathVariable String zipCode,HttpServletRequest request){
        return addressService.getAddressByZipCode(zipCode,request);
    }

    @PostMapping
    public Result createAddress(@RequestBody Address address, HttpServletRequest request){
        return addressService.createAddress(address,request);
    }

    @PutMapping("/{id}")
    public Result updateAddress(@PathVariable String id,@RequestBody Address address, HttpServletRequest request){
        return addressService.updateAddress(id,address,request);
    }

    @DeleteMapping("/{id}")
    public Result deleteAddress(@PathVariable String id, HttpServletRequest request){
        return addressService.deleteAddress(id,request);
    }

    @GetMapping()
    public Result getAllAddress(HttpServletRequest request){
        return addressService.getAllAddress(request);
    }


    @GetMapping("/find")
    public List<Address> getAddressByIdentifier(@RequestParam String identifier){
        return addressService.getAddressByIdentifier(identifier);
    }
}
