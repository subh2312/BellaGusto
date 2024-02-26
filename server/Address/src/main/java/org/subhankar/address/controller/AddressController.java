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
    public Result getAddress(@PathVariable String id,@RequestParam String type, HttpServletRequest request){
        return addressService.getAddress(id,type,request);
    }

    @GetMapping("/city/{city}")
    public Result getAddressByCity(@PathVariable String city,@RequestParam String type, HttpServletRequest request){
        return addressService.getAddressByCity(city,type,request);
    }

    @GetMapping("/state/{state}")
    public Result getAddressByState(@PathVariable String state,@RequestParam String type, HttpServletRequest request){
        return addressService.getAddressByState(state,type,request);
    }

    @GetMapping("/country/{country}")
    public Result getAddressByCountry(@PathVariable String country,@RequestParam String type, HttpServletRequest request){
        return addressService.getAddressByCountry(country,type,request);
    }

    @GetMapping("/zipCode/{zipCode}")
    public Result getAddressByZipCode(@PathVariable String zipCode,@RequestParam String type,HttpServletRequest request){
        return addressService.getAddressByZipCode(zipCode,type,request);
    }

    @PostMapping
    public Result createAddress(@RequestBody Address address, HttpServletRequest request){
        return addressService.createAddress(address,request);
    }

    @PutMapping("/{id}")
    public Result updateAddress(@PathVariable String id,@RequestParam String type,@RequestBody Address address, HttpServletRequest request){
        return addressService.updateAddress(id,type,address,request);
    }

    @DeleteMapping("/{id}")
    public Result deleteAddress(@PathVariable String id,@RequestParam String type, HttpServletRequest request){
        return addressService.deleteAddress(id,type,request);
    }

    @GetMapping()
    public Result getAllAddress(@RequestParam String type,HttpServletRequest request){
        return addressService.getAllAddress(type,request);
    }


    @GetMapping("/find")
    public List<Address> getAddressByIdentifier(@RequestParam String identifier){
        return addressService.getAddressByIdentifier(identifier);
    }
}
