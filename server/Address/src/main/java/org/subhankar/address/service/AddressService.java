package org.subhankar.address.service;

import org.subhankar.address.model.DO.Address;
import org.subhankar.address.model.DTO.Result;

import java.util.List;

public interface AddressService {

    Result getAddress(String id);
    Result getAddressByCity(String city);

    Result getAddressByState(String state);

    Result getAddressByCountry(String country);

    Result getAddressByZipCode(String zipCode);

    Result createAddress(Address address);

    Result updateAddress(String id,Address address);

    Result deleteAddress(String id);

    Result getAllAddress();

    List<Address> getAddressByIdentifier(String identifier);
}
