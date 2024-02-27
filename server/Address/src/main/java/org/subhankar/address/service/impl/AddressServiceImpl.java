package org.subhankar.address.service.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subhankar.address.config.JwtUtil;
import org.subhankar.address.exception.ResourceNotFoundException;
import org.subhankar.address.integration.model.BasicRestaurantInfoDTO;
import org.subhankar.address.integration.service.RestaurantService;
import org.subhankar.address.model.DO.Address;
import org.subhankar.address.model.DO.AddressType;
import org.subhankar.address.model.DO.Status;
import org.subhankar.address.model.DTO.CreateAddressDTO;
import org.subhankar.address.model.DTO.Result;
import org.subhankar.address.repository.AddressRepository;
import org.subhankar.address.service.AddressService;

import java.util.*;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final JwtUtil jwtUtil;
    private final AddressRepository addressRepository;
    private final RestaurantService restaurantIntegration;

    @Override
    public Result getAddress(String id, HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String userId = jwtUtil.getIdFromToken(token);
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            throw new ResourceNotFoundException("Address", "id", id);
        }

        Address address = optionalAddress.get();
        String addressType = address.getType().toString();
        if(jwtUtil.hasRole(token,"User") && addressType.equals(AddressType.USER.toString()) && !address.getIdentifier().equals(userId)) {
            if (!address.getIdentifier().equals(userId)) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Address not found")
                        .data(null)
                        .build();
            }
        }
        if(jwtUtil.hasRole(token,"Restaurant Owner") && addressType.equals(AddressType.RESTAURANT.toString())){
            Result restaurantResult = restaurantIntegration.getRestaurantBasicInfo(address.getIdentifier());
            LinkedHashMap<String,Object> restaurantData = (LinkedHashMap<String, Object>) restaurantResult.getData();
            String owner = (String) restaurantData.get("owner");
            if(!owner.equals(userId)){
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to view address of this restaurant")
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
    public Result getAddressByCity(String city, HttpServletRequest request) {
        
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
    public Result getAddressByState(String state, HttpServletRequest request) {
        
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
    public Result getAddressByCountry(String country, HttpServletRequest request) {
        
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
    public Result getAddressByZipCode(String zipCode, HttpServletRequest request) {


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
    public Result createAddress(CreateAddressDTO address, HttpServletRequest request) {
        AddressType addressType = AddressType.valueOf(address.getAddressType());
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        Address newAddress = Address.builder()
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .tag(address.getTag())
                .identifier(address.getIdentifier())
                .build();
        if(jwtUtil.hasRole(token,"User")) {
            if (!addressType.equals(AddressType.USER)) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to create address of this type")
                        .data(null)
                        .build();
            }
            if (!address.getIdentifier().equals(jwtUtil.getIdFromToken(token))){
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to create address for this user")
                        .data(null)
                        .build();
            }
            newAddress.setType(AddressType.USER);
            newAddress.setStatus(Status.VERIFIED);
        }else if(jwtUtil.hasRole(token,"Restaurant Owner")){
            if (!(addressType.equals(AddressType.RESTAURANT)) && !(addressType.equals(AddressType.OWNER))) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to create address of this type")
                        .data(null)
                        .build();
            }else if(addressType.equals(AddressType.RESTAURANT)){
                Result restaurantResult = restaurantIntegration.getRestaurantBasicInfo(address.getIdentifier());
                LinkedHashMap<String,Object> restaurantData = (LinkedHashMap<String, Object>) restaurantResult.getData();
                String owner = (String) restaurantData.get("owner");
                if(!owner.equals(jwtUtil.getIdFromToken(token))){
                    return Result.builder()
                            .code("FDAAS-0002")
                            .message("Not authorized to create address for this restaurant")
                            .data(null)
                            .build();
                }
            } else if (addressType.equals(AddressType.OWNER)) {
                if (!address.getIdentifier().equals(jwtUtil.getIdFromToken(token))) {
                    return Result.builder()
                            .code("FDAAS-0002")
                            .message("Not authorized to create address for this owner")
                            .data(null)
                            .build();
                }

            }

            newAddress.setType(addressType.equals(AddressType.RESTAURANT)?AddressType.RESTAURANT:AddressType.OWNER);
            newAddress.setStatus(addressType.equals(AddressType.RESTAURANT)?Status.VERIFICATION_PENDING:Status.VERIFIED);
        }else if (jwtUtil.hasRole(token,"Delivery Partner")){
            if (!addressType.equals(AddressType.DELIVERY)) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to create address of this type")
                        .data(null)
                        .build();
            }
            newAddress.setType(AddressType.DELIVERY);
            newAddress.setStatus(Status.VERIFICATION_PENDING);
        }

        return Result.builder()
                .code("FDAAS-0001")
                .message("Address created")
                .data(addressRepository.save(newAddress))
                .build();
    }

    @Override
    public Result updateAddress(String id, Address address, HttpServletRequest request) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            throw new ResourceNotFoundException("Address", "id", id);
        }

        Address oldAddress = optionalAddress.get();
        AddressType addressType = oldAddress.getType();
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        if(jwtUtil.hasRole(token,"User")) {
            if (!addressType.equals(AddressType.USER)) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to update address of this type")
                        .data(null)
                        .build();
            }
            if (!oldAddress.getIdentifier().equals(jwtUtil.getIdFromToken(token))){
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to update address for this user")
                        .data(null)
                        .build();
            }

        }else if(jwtUtil.hasRole(token,"Restaurant Owner")){
            if (!addressType.equals(AddressType.RESTAURANT) && !addressType.equals(AddressType.OWNER)) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to create address of this type")
                        .data(null)
                        .build();
            }else if(addressType.equals(AddressType.RESTAURANT)){
                Result restaurantResult = restaurantIntegration.getRestaurantBasicInfo(oldAddress.getIdentifier());
                LinkedHashMap<String,Object> restaurantData = (LinkedHashMap<String, Object>) restaurantResult.getData();
                String owner = (String) restaurantData.get("owner");
                if(!owner.equals(jwtUtil.getIdFromToken(token))){
                    return Result.builder()
                            .code("FDAAS-0002")
                            .message("Not authorized to update address for this restaurant")
                            .data(null)
                            .build();
                }
            } else if (addressType.equals(AddressType.OWNER)) {
                if (!oldAddress.getIdentifier().equals(jwtUtil.getIdFromToken(token))) {
                    return Result.builder()
                            .code("FDAAS-0002")
                            .message("Not authorized to update address for this owner")
                            .data(null)
                            .build();
                }

            }

        }else if (jwtUtil.hasRole(token,"Delivery Partner")){
            if (!addressType.equals(AddressType.DELIVERY)) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to update address of this type")
                        .data(null)
                        .build();
            }

        }

        updatePropertyIfNotEmpty(address.getAddressLine1(), oldAddress::setAddressLine1);
        updatePropertyIfNotEmpty(address.getAddressLine2(), oldAddress::setAddressLine2);
        updatePropertyIfNotEmpty(address.getCity(), oldAddress::setCity);
        updatePropertyIfNotEmpty(address.getState(), oldAddress::setState);
        updatePropertyIfNotEmpty(address.getCountry(), oldAddress::setCountry);
        updatePropertyIfNotEmpty(address.getZipCode(), oldAddress::setZipCode);
        updatePropertyIfNotEmpty(address.getTag(), oldAddress::setTag);

        addressRepository.save(oldAddress);
        return Result.builder()
                .code("FDAAS-0001")
                .message("Address updated successfully")
                .data(oldAddress)
                .build();
    }

    @Override
    public Result deleteAddress(String id, HttpServletRequest request) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            throw new ResourceNotFoundException("Address", "id", id);
        }

        Address address = optionalAddress.get();
        AddressType addressType = address.getType();
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        if(jwtUtil.hasRole(token,"User")) {
            if (!addressType.equals(AddressType.USER)) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to update address of this type")
                        .data(null)
                        .build();
            }
            if (!address.getIdentifier().equals(jwtUtil.getIdFromToken(token))){
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to delete address for this user")
                        .data(null)
                        .build();
            }

        }else if(jwtUtil.hasRole(token,"Restaurant Owner")){
            if (!addressType.equals(AddressType.RESTAURANT) || !addressType.equals(AddressType.OWNER)) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to delete address of this type")
                        .data(null)
                        .build();
            }else if(addressType.equals(AddressType.RESTAURANT)){
                Result restaurantResult = restaurantIntegration.getRestaurantBasicInfo(address.getIdentifier());
                LinkedHashMap<String,Object> restaurantData = (LinkedHashMap<String, Object>) restaurantResult.getData();
                String owner = (String) restaurantData.get("owner");
                if(!owner.equals(jwtUtil.getIdFromToken(token))){
                    return Result.builder()
                            .code("FDAAS-0002")
                            .message("Not authorized to delete address for this restaurant")
                            .data(null)
                            .build();
                }
            } else if (addressType.equals(AddressType.OWNER)) {
                if (!address.getIdentifier().equals(jwtUtil.getIdFromToken(token))) {
                    return Result.builder()
                            .code("FDAAS-0002")
                            .message("Not authorized to delete address for this owner")
                            .data(null)
                            .build();
                }

            }

        }else if (jwtUtil.hasRole(token,"Delivery Partner")){
            if (!addressType.equals(AddressType.DELIVERY)) {
                return Result.builder()
                        .code("FDAAS-0002")
                        .message("Not authorized to delete address of this type")
                        .data(null)
                        .build();
            }

        }
        addressRepository.deleteById(id);

        return Result.builder()
                .code("FDAAS-0001")
                .message("Address deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public Result getAllAddress(HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String userId = jwtUtil.getIdFromToken(token);
        List<Address> addresses = addressRepository.findAll();

        if(jwtUtil.hasRole(token,"User")) {
            addresses.removeIf(a -> !a.getIdentifier().equals(userId) && !a.getType().equals(AddressType.USER));
return Result.builder()
                    .code("FDAAS-0001")
                    .message("Address found")
                    .data(addresses)
                    .build();

        }else if(jwtUtil.hasRole(token,"Restaurant Owner")) {
            List<String> restaurantIds = new ArrayList<>();
            addresses.forEach(a -> {
                if(a.getType().equals(AddressType.RESTAURANT)){
                    Result result = restaurantIntegration.getRestaurantBasicInfo(a.getIdentifier());
                    LinkedHashMap<String,Object> restaurantData = (LinkedHashMap<String, Object>) result.getData();
                    String owner = (String) restaurantData.get("owner");
                    if(owner.equals(userId)){
                        restaurantIds.add(a.getIdentifier());
                    }
                }
            });
            List<Address> ownerAddresses = addresses.stream().filter(a -> a.getType().equals(AddressType.OWNER) && a.getIdentifier().equals(userId)).toList();
            List<Address> restaurantAddresses = addresses.stream().filter(a -> a.getType().equals(AddressType.RESTAURANT) && restaurantIds.contains(a.getIdentifier())).toList();
            List<Address> finalAddresses = new ArrayList<>();
            finalAddresses.addAll(ownerAddresses);
            finalAddresses.addAll(restaurantAddresses);
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("Address found")
                    .data(finalAddresses)
                    .build();
        }else if(jwtUtil.hasRole(token,"Delivery Partner")) {
            addresses.removeIf(a -> !a.getType().equals(AddressType.DELIVERY));
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("Address found")
                    .data(addresses)
                    .build();
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
