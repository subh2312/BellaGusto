package org.subhankar.address.service;

import jakarta.servlet.http.HttpServletRequest;
import org.subhankar.address.model.DO.Address;
import org.subhankar.address.model.DTO.Result;

import java.util.List;

public interface AddressService {

    Result getAddress(String id,String type, HttpServletRequest request);
    Result getAddressByCity(String city,String type, HttpServletRequest request);

    Result getAddressByState(String state,String type, HttpServletRequest request);

    Result getAddressByCountry(String country,String type, HttpServletRequest request);

    Result getAddressByZipCode(String zipCode,String type, HttpServletRequest request);

    Result createAddress(Address address, HttpServletRequest request);

    Result updateAddress(String id,String type,Address address, HttpServletRequest request);

    Result deleteAddress(String id,String type, HttpServletRequest request);

    Result getAllAddress(String type,HttpServletRequest request);

    List<Address> getAddressByIdentifier(String identifier);
}
