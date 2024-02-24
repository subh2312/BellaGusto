package org.subhankar.address.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subhankar.address.exception.ResourceNotFoundException;
import org.subhankar.address.model.DO.Address;
import org.subhankar.address.model.DTO.Result;
import org.subhankar.address.repository.AddressRepository;
import org.subhankar.address.service.AddressService;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Result getAddress(String id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            throw new ResourceNotFoundException("Address", "id", id);
        }

        Address address = optionalAddress.get();
        return Result.builder()
                .code("FDAAS-0001")
                .message("Address found")
                .data(address)
                .build();
    }

    @Override
    public Result getAddressByCity(String city) {
        List<Address> address = addressRepository.findByCity(city);
        if (address.isEmpty()){
            throw new ResourceNotFoundException("Address", "city", city);
        }


        return Result.builder()
                .code("FDAAS-0001")
                .message("Address found")
                .data(address)
                .build();
    }

    @Override
    public Result getAddressByState(String state) {
        List<Address> addresses = addressRepository.findByState(state);
        if (addresses.isEmpty()){
            throw new ResourceNotFoundException("Address", "state", state);
        }

        return Result.builder()
                .code("FDAAS-0001")
                .message("Address found")
                .data(addresses)
                .build();
    }

    @Override
    public Result getAddressByCountry(String country) {
        List<Address> addresses = addressRepository.findByCountry(country);
        if (addresses.isEmpty()){
            throw new ResourceNotFoundException("Address", "country", country);
        }


        return Result.builder()
                .code("FDAAS-0001")
                .message("Address found")
                .data(addresses)
                .build();
    }

    @Override
    public Result getAddressByZipCode(String zipCode) {
        List<Address> addresses = addressRepository.findByZipCode(zipCode);
        if (addresses.isEmpty()){
            throw new ResourceNotFoundException("Address", "zipCode", zipCode);
        }

        return Result.builder()
                .code("FDAAS-0001")
                .message("Address found")
                .data(addresses)
                .build();
    }

    @Override
    public Result createAddress(Address address) {
        Address savedAddress = addressRepository.save(address);
        return Result.builder()
                .code("FDAAS-0001")
                .message("Address created")
                .data(savedAddress)
                .build();
    }

    @Override
    public Result updateAddress(String id, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            throw new ResourceNotFoundException("Address", "id", id);
        }

        Address oldAddress = optionalAddress.get();
        updatePropertyIfNotEmpty(address.getAddressLine1(), oldAddress::setAddressLine1);
        updatePropertyIfNotEmpty(address.getAddressLine2(), oldAddress::setAddressLine2);
        updatePropertyIfNotEmpty(address.getCity(), oldAddress::setCity);
        updatePropertyIfNotEmpty(address.getState(), oldAddress::setState);
        updatePropertyIfNotEmpty(address.getCountry(), oldAddress::setCountry);
        updatePropertyIfNotEmpty(address.getZipCode(), oldAddress::setZipCode);
        updatePropertyIfNotEmpty(address.getIdentifier(), oldAddress::setIdentifier);

        addressRepository.save(oldAddress);
        return Result.builder()
                .code("FDAAS-0001")
                .message("Address updated successfully")
                .data(oldAddress)
                .build();
    }

    @Override
    public Result deleteAddress(String id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            throw new ResourceNotFoundException("Address", "id", id);
        }

        addressRepository.deleteById(id);

        return Result.builder()
                .code("FDAAS-0001")
                .message("Address deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public Result getAllAddress() {
        return Result.builder()
                .code("FDAAS-0001")
                .message("Address found")
                .data(addressRepository.findAll())
                .build();
    }

    @Override
    public List<Address> getAddressByIdentifier(String identifier) {
        return addressRepository.findByIdentifier(identifier);
    }

    private static void updatePropertyIfNotEmpty(String newValue, Consumer<String> setter) {
        if (newValue != null && !newValue.isEmpty()) {
            setter.accept(newValue);
        }
    }


}
