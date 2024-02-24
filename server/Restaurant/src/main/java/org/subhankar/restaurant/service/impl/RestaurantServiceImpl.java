package org.subhankar.restaurant.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subhankar.restaurant.exception.ResourceNotFoundException;
import org.subhankar.restaurant.integration.AddressIntegration;
import org.subhankar.restaurant.integration.MenuService;
import org.subhankar.restaurant.model.DO.Address;
import org.subhankar.restaurant.model.DO.Menu;
import org.subhankar.restaurant.model.DO.Restaurant;
import org.subhankar.restaurant.model.DTO.Result;
import org.subhankar.restaurant.repository.RestaurantRepository;
import org.subhankar.restaurant.service.RestaurantService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuService menuService;
    private final AddressIntegration addressIntegration;
        @Override
        public Result getRestaurantById(String id) throws JsonProcessingException {
            Optional<Restaurant> optional = restaurantRepository.findById(id);
            if (optional.isEmpty()){
                throw new ResourceNotFoundException("Restaurant", "id", id);
            }
            Restaurant restaurant = optional.get();
            Menu menu = menuService.getMenu(id);
            restaurant.setMenu(menu);
           List<Address> addresses = addressIntegration.getAddressByIdentifier(id);
            List<String> addressIds = new ArrayList<>();
            for(Address address: addresses){
                addressIds.add(address.getId());
            }
            restaurant.setAddresses(addressIds);
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("Restaurant found")
                    .data(restaurant)
                    .build();
        }

    @Override
    public Result getAllRestaurants() {
        return Result.builder()
                .code("FDAAS-0001")
                .message("Restaurant found")
                .data(restaurantRepository.findAll())
                .build();
    }

    @Override
    public Result createRestaurant(Restaurant restaurant) {
        return Result.builder()
                .code("FDAAS-0001")
                .message("Restaurant found")
                .data(restaurantRepository.save(restaurant))
                .build();
    }

    @Override
    public Result updateRestaurant(String id, Restaurant restaurant) {
        Optional<Restaurant> optional = restaurantRepository.findById(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Restaurant", "id", id);
        }
        Restaurant restaurant1 = optional.get();
        updatePropertyIfNotEmpty(restaurant.getName(), restaurant1::setName);
        updatePropertyIfNotEmpty(restaurant.getLogo(), restaurant1::setLogo);
        updatePropertyIfNotEmpty(restaurant.getPhone(), restaurant1::setPhone);
        updatePropertyIfNotEmpty(restaurant.getDescription(), restaurant1::setDescription);
        updatePropertyIfNotEmpty(restaurant.getOpeningTime(), restaurant1::setOpeningTime);
        updatePropertyIfNotEmpty(restaurant.getClosingTime(), restaurant1::setClosingTime);
        return Result.builder()
                .code("FDAAS-0001")
                .message("Restaurant found")
                .data(restaurantRepository.save(restaurant1))
                .build();
    }

    @Override
    public Result deleteRestaurant(String id) {
        Optional<Restaurant> optional = restaurantRepository.findById(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Restaurant", "id", id);
        }
        restaurantRepository.deleteById(id);
        return Result.builder()
                .code("FDAAS-0001")
                .message("Restaurant Deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public Result addAddress(String id, Address address) {
        Optional<Restaurant> optional = restaurantRepository.findById(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Restaurant", "id", id);
        }
        Address restaurantAddress = Address
                .builder()
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .identifier(id)
                .tag(address.getTag())
                .build();
        Result addressResult = addressIntegration.createAddress(restaurantAddress);
        HashMap<String, String> addressData = (HashMap<String, String>) addressResult.getData();
        String addressId = addressData.get("id");
        Restaurant restaurant = optional.get();
        restaurant.setAddresses(List.of(addressId));


        return Result.builder()
                .code("FDAAS-0001")
                .message("Restaurant found")
                .data(restaurantRepository.save(restaurant))
                .build();
    }

    private static void updatePropertyIfNotEmpty(String newValue, Consumer<String> setter) {
        if (newValue != null && !newValue.isEmpty()) {
            setter.accept(newValue);
        }
    }
}
