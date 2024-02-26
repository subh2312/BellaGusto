package org.subhankar.address.service;

import jakarta.servlet.http.HttpServletRequest;
import org.subhankar.address.model.DO.Address;
import org.subhankar.address.model.DTO.Result;

import java.util.List;

public interface AddressService {

    Result getAddress(String id, HttpServletRequest request);
    Result getAddressByCity(String city, HttpServletRequest request);

    Result getAddressByState(String state, HttpServletRequest request);

    Result getAddressByCountry(String country, HttpServletRequest request);

    Result getAddressByZipCode(String zipCode, HttpServletRequest request);

    Result createAddress(Address address, HttpServletRequest request);

    Result updateAddress(String id,Address address, HttpServletRequest request);

    Result deleteAddress(String id, HttpServletRequest request);

    Result getAllAddress(HttpServletRequest request);

    List<Address> getAddressByIdentifier(String identifier);
}
