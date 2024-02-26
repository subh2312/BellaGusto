package org.subhankar.restaurant.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subhankar.restaurant.config.JwtUtil;
import org.subhankar.restaurant.exception.ResourceNotFoundException;
import org.subhankar.restaurant.integration.AddressIntegration;
import org.subhankar.restaurant.integration.MenuService;
import org.subhankar.restaurant.model.DO.Address;
import org.subhankar.restaurant.model.DO.Menu;
import org.subhankar.restaurant.model.DO.Restaurant;
import org.subhankar.restaurant.model.DO.Status;
import org.subhankar.restaurant.model.DTO.Result;
import org.subhankar.restaurant.repository.RestaurantRepository;
import org.subhankar.restaurant.service.RestaurantService;

import java.util.*;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuService menuService;
    private final AddressIntegration addressIntegration;
    private final JwtUtil jwtUtil;
        @Override
        public Result getRestaurantById(String id, HttpServletRequest request) throws JsonProcessingException {
            Optional<Restaurant> optional = restaurantRepository.findById(id);
            if (optional.isEmpty()){
                throw new ResourceNotFoundException("Restaurant", "id", id);
            }
            Restaurant restaurant = optional.get();
            String owner = jwtUtil.getIdFromToken(Arrays.stream(request.getCookies())
                    .filter(cookie -> "token".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Token not found")));
            if(!restaurant.getOwner().equals(owner)){
                return Result.builder()
                        .code("FDAAS-0001")
                        .message("Restaurant Not Found")
                        .data(null)
                        .build();
            }
//            Menu menu = menuService.getMenu(id);
//            restaurant.setMenu(menu);
//            List<Address> addresses = addressIntegration.getAddressByIdentifier(id);
//            List<String> addressIds = new ArrayList<>();
//            for(Address address: addresses){
//                addressIds.add(address.getId());
//            }
//            restaurant.setAddresses(addressIds);
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("Restaurant found")
                    .data(restaurant)
                    .build();
        }

    @Override
    public Result getAllRestaurants(HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Token not found"));
        String owner = jwtUtil.getIdFromToken(token);
        List<Restaurant> restaurants = restaurantRepository.findAll();
        if(jwtUtil.hasRole(token, "Restaurant Owner")){
            restaurants.removeIf(restaurant -> !restaurant.getOwner().equals(owner));
        }
        return Result.builder()
                .code("FDAAS-0001")
                .message("Restaurants found")
                .data(restaurants)
                .build();
    }

    @Override
    public Result createRestaurant(Restaurant restaurant, HttpServletRequest request) {
            String token = Arrays.stream(request.getCookies())
                    .filter(cookie -> "token".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Token not found"));
            if(!jwtUtil.hasRole(token, "Restaurant Owner")){
                return Result.builder()
                        .code("FDAAS-0001")
                        .message("You are not authorized to create a restaurant. Please register/login as owner.")
                        .data(null)
                        .build();
            }
            String owner = jwtUtil.getIdFromToken(token);
        return Result.builder()
                .code("FDAAS-0001")
                .message("Restaurant found")
                .data(restaurantRepository.save(Restaurant
                        .builder()
                                .name(restaurant.getName())
                                .description(restaurant.getDescription())
                                .phone(restaurant.getPhone())
                                .logo(restaurant.getLogo())
                                .openingTime(restaurant.getOpeningTime())
                                .closingTime(restaurant.getClosingTime())
                                .owner(owner)
                                .status(Status.PENDING)
                        .build()))
                .build();
    }

    @Override
    public Result updateRestaurant(String id, HttpServletRequest request, Restaurant restaurant) {
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Token not found"));
        if(!jwtUtil.hasRole(token, "Restaurant Owner")){
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("You are not authorized to create a restaurant. Please register/login as owner.")
                    .data(null)
                    .build();
        }
        Optional<Restaurant> optional = restaurantRepository.findById(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Restaurant", "id", id);
        }
        Restaurant restaurant1 = optional.get();
        String userId = jwtUtil.getIdFromToken(token);
        if(!restaurant1.getOwner().equals(userId)){
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("You are not authorized to update this restaurant. Please register/login as owner.")
                    .data(null)
                    .build();
        }
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
    public Result deleteRestaurant(String id, HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Token not found"));
        if(!jwtUtil.hasRole(token, "Restaurant Owner")){
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("You are not authorized to create a restaurant. Please register/login as owner.")
                    .data(null)
                    .build();
        }
        Optional<Restaurant> optional = restaurantRepository.findById(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Restaurant", "id", id);
        }
        String userId = jwtUtil.getIdFromToken(token);
        if(!optional.get().getOwner().equals(userId)){
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("You are not authorized to update this restaurant. Please register/login as owner.")
                    .data(null)
                    .build();
        }
        restaurantRepository.deleteById(id);
        return Result.builder()
                .code("FDAAS-0001")
                .message("Restaurant Deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public Result changeOwner(String id, HttpServletRequest request, String owner) {
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Token not found"));
        if(!jwtUtil.hasRole(token, "Restaurant Owner")){
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("You are not authorized to create a restaurant. Please register/login as owner.")
                    .data(null)
                    .build();
        }
        Optional<Restaurant> optional = restaurantRepository.findById(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Restaurant", "id", id);
        }
        String userId = jwtUtil.getIdFromToken(token);
        if(!optional.get().getOwner().equals(userId)){
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("You are not authorized to update this restaurant. Please register/login as owner.")
                    .data(null)
                    .build();
        }
        Restaurant restaurant = optional.get();
        restaurant.setOwner(owner);
        return Result.builder()
                .code("FDAAS-0001")
                .message("Restaurant found")
                .data(restaurantRepository.save(restaurant))
                .build();
    }

    @Override
    public Result changeStatus(String id, HttpServletRequest request, String status) {
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Token not found"));
        if(!jwtUtil.hasRole(token, "Admin")){
            return Result.builder()
                    .code("FDAAS-0001")
                    .message("You are not authorized to change status. Please contact admin.")
                    .data(null)
                    .build();
        }
        Optional<Restaurant> optional = restaurantRepository.findById(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Restaurant", "id", id);
        }
        Restaurant restaurant = optional.get();
        restaurant.setStatus(Status.valueOf(status.toUpperCase()));
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
