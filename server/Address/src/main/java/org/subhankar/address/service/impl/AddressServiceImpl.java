package org.subhankar.address.service.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subhankar.address.config.JwtUtil;
import org.subhankar.address.exception.ResourceNotFoundException;
import org.subhankar.address.model.DO.Address;
import org.subhankar.address.model.DTO.Result;
import org.subhankar.address.repository.AddressRepository;
import org.subhankar.address.service.AddressService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final JwtUtil jwtUtil;
    private final AddressRepository addressRepository;

    @Override
    public Result getAddress(String id,String type, HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String userId = jwtUtil.getIdFromToken(token);
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            throw new ResourceNotFoundException("Address", "id", id);
        }

        Address address = optionalAddress.get();
        if(!jwtUtil.hasRole(token,"Admin")) {
            //when fetching an address belonging to different user
            if (!address.getIdentifier().equals(userId)&&type.equals("user")) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Address not found")
                        .data(null)
                        .build();
            }
        }
        return Result.builder()
                .code("FDAAS-0001")
                .message("Address found")
                .data(address)
                .build();
    }

    @Override
    public Result getAddressByCity(String city,String type, HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String userId = jwtUtil.getIdFromToken(token);
        List<Address> address = addressRepository.findByCity(city);
        if(!jwtUtil.hasRole(token,"Admin")&&type.equals("user")) {
            address.removeIf(a -> !a.getIdentifier().equals(userId));
        }
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
    public Result getAddressByState(String state,String type, HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String userId = jwtUtil.getIdFromToken(token);
        List<Address> addresses = addressRepository.findByState(state);
        if(!jwtUtil.hasRole(token,"Admin") && type.equals("user")) {
            addresses.removeIf(a -> !a.getIdentifier().equals(userId));
        }
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
    public Result getAddressByCountry(String country,String type, HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String userId = jwtUtil.getIdFromToken(token);
        List<Address> addresses = addressRepository.findByCountry(country);
        if(!jwtUtil.hasRole(token,"Admin") && type.equals("user")) {
            addresses.removeIf(a -> !a.getIdentifier().equals(userId));
        }
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
    public Result getAddressByZipCode(String zipCode,String type, HttpServletRequest request) {


        List<Address> addresses = addressRepository.findByZipCode(zipCode);
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String userId = jwtUtil.getIdFromToken(token);

        if(!jwtUtil.hasRole(token,"Admin") && type.equals("user")){
            addresses.removeIf(a -> !a.getIdentifier().equals(userId));
        }

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
    public Result createAddress(Address address, HttpServletRequest request) {
        Address newAddress = Address.builder()
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .tag(address.getTag())
                .identifier(jwtUtil.getIdFromToken(request.getCookies()[0].getValue()))
                .build();
        return Result.builder()
                .code("FDAAS-0001")
                .message("Address created")
                .data(addressRepository.save(newAddress))
                .build();
    }

    @Override
    public Result updateAddress(String id,String type, Address address, HttpServletRequest request) {

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            throw new ResourceNotFoundException("Address", "id", id);
        }
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String userId = jwtUtil.getIdFromToken(token);
        if(!jwtUtil.hasRole(token,"Admin")) {
            if (!optionalAddress.get().getIdentifier().equals(userId) && type.equals("user")) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Address not found")
                        .data(null)
                        .build();
            }
//            if(type.equals("restaurant")){
//                Restaurant restaurant = restaurantIntegration.getById(optionalAddress.get().getIdentifier());
//                if(restaurant.isEmpty()){
//                    return Result.builder()
//                            .code("FDAAS-0002")
//                            .message("Restaurant not found")
//                            .data(null)
//                            .build();
//                }
//                if(!restaurant.owner.equals(userId)){
//                    return Result.builder()
//                            .code("FDAAS-0002")
//                            .message("Address not found")
//                            .data(null)
//                            .build();
//                }
//            }
        }
        Address oldAddress = optionalAddress.get();
        updatePropertyIfNotEmpty(address.getAddressLine1(), oldAddress::setAddressLine1);
        updatePropertyIfNotEmpty(address.getAddressLine2(), oldAddress::setAddressLine2);
        updatePropertyIfNotEmpty(address.getCity(), oldAddress::setCity);
        updatePropertyIfNotEmpty(address.getState(), oldAddress::setState);
        updatePropertyIfNotEmpty(address.getCountry(), oldAddress::setCountry);
        updatePropertyIfNotEmpty(address.getZipCode(), oldAddress::setZipCode);
        updatePropertyIfNotEmpty(userId, oldAddress::setIdentifier);
        updatePropertyIfNotEmpty(address.getTag(), oldAddress::setTag);
        addressRepository.save(oldAddress);
        return Result.builder()
                .code("FDAAS-0001")
                .message("Address updated successfully")
                .data(oldAddress)
                .build();
    }

    @Override
    public Result deleteAddress(String id,String type, HttpServletRequest request) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            throw new ResourceNotFoundException("Address", "id", id);
        }
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String userId = jwtUtil.getIdFromToken(token);
        if(!jwtUtil.hasRole(token,"Admin")) {
            if (!optionalAddress.get().getIdentifier().equals(userId) && type.equals("user")) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Address not found")
                        .data(null)
                        .build();
            }
            //            if(type.equals("restaurant")){
//                Restaurant restaurant = restaurantIntegration.getById(optionalAddress.get().getIdentifier());
//                if(restaurant.isEmpty()){
//                    return Result.builder()
//                            .code("FDAAS-0002")
//                            .message("Restaurant not found")
//                            .data(null)
//                            .build();
//                }
//                if(!restaurant.owner.equals(userId)){
//                    return Result.builder()
//                            .code("FDAAS-0002")
//                            .message("Address not found")
//                            .data(null)
//                            .build();
//                }
//            }
        }
        addressRepository.deleteById(id);

        return Result.builder()
                .code("FDAAS-0001")
                .message("Address deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public Result getAllAddress(String type,HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String userId = jwtUtil.getIdFromToken(token);
        List<Address> addresses = addressRepository.findAll();
        if(!jwtUtil.hasRole(token,"Admin") && type.equals("user")) {
            addresses.removeIf(a -> !a.getIdentifier().equals(userId));
        }

        return Result.builder()
                .code("FDAAS-0001")
                .message("Address found")
                .data(addresses)
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
