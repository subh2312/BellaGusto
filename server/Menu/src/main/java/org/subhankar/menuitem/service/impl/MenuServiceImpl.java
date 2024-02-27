package org.subhankar.menuitem.service.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subhankar.menuitem.config.JwtUtil;
import org.subhankar.menuitem.integration.model.BasicRestaurantInfoDTO;
import org.subhankar.menuitem.integration.service.RestaurantService;
import org.subhankar.menuitem.model.DO.Category;
import org.subhankar.menuitem.model.DO.Item;
import org.subhankar.menuitem.model.DO.Menu;
import org.subhankar.menuitem.model.DTO.CreateMenuDTO;
import org.subhankar.menuitem.model.DTO.Result;
import org.subhankar.menuitem.repository.ItemRepository;
import org.subhankar.menuitem.repository.MenuRepository;
import org.subhankar.menuitem.service.MenuService;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final ItemRepository itemRepository;
    private final RestaurantService restaurantService;
    private final JwtUtil jwtUtil;
    @Override
    public Result createMenu(CreateMenuDTO createMenuDTO, HttpServletRequest request) {
        String checkIfAuthorized = checkIfAuthorized(createMenuDTO.getRestaurantId(), request);
        if (!checkIfAuthorized.equals("OK")) {
            return Result.builder().code("400").message(checkIfAuthorized).build();
        }

        return Result.builder().code("200").message("OK").data(menuRepository.save(Menu.builder()
                        .code(createMenuDTO.getCode())
                        .restaurantId(createMenuDTO.getRestaurantId())
                .build())).build();
    }

    @Override
    public Result getMenuById(String id, HttpServletRequest request) {
        String checkIfAuthorizedToView = checkIfAuthorizedToView(id, request);
        if (!checkIfAuthorizedToView.equals("OK")) {
            return Result.builder().code("400").message(checkIfAuthorizedToView).build();
        }
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"));
        List<Category> categories = menu.getCategories();
        for (Category category : categories) {
            List<Item> items = itemRepository.findByCategoryId(category.getId());
            category.setItems(items);
        }
        return Result.builder().code("200").message("Success").data(menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"))).build();
    }

    @Override
    public Result getMenuByRestaurantId(String id, HttpServletRequest request) {
        String checkIfAuthorizedToView = checkIfAuthorizedToView(id, request);
        if (!checkIfAuthorizedToView.equals("OK")) {
            return Result.builder().code("400").message(checkIfAuthorizedToView).build();
        }
        Menu menu = menuRepository.findByRestaurantId(id).orElseThrow(() -> new RuntimeException("Menu not found"));
        List<Category> categories = menu.getCategories();
        for (Category category : categories) {
            List<Item> items = itemRepository.findByCategoryId(category.getId());
            category.setItems(items);
        }
        return Result.builder().code("200").message("Success").data(menu).build();
    }


    private String checkIfAuthorized(String restaurantId, HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        if(!jwtUtil.hasRole(token,"Restaurant Owner") && !jwtUtil.hasRole(token,"ADMIN")){
            return "Unauthorized";
        }
        Result restaurantResult = restaurantService.getRestaurantBasicInfo(restaurantId);
        LinkedHashMap<String, Object> restaurantInfo = (LinkedHashMap<String, Object>) restaurantResult.getData();

        if (restaurantInfo == null) {
            return "Restaurant not found";
        }
        String ownerId = jwtUtil.getIdFromToken(token);
        if (!restaurantInfo.get("owner").equals(ownerId)) {
            return "Unauthorized";
        }

        return "OK";
    }

    private String checkIfAuthorizedToView(String restaurantId, HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        Result restaurantResult = restaurantService.getRestaurantBasicInfo(restaurantId);
        LinkedHashMap<String, Object> restaurantInfo = (LinkedHashMap<String, Object>) restaurantResult.getData();
        if (restaurantInfo == null) {
            return"Restaurant not found";
        }
        String ownerId = jwtUtil.getIdFromToken(token);
        if (jwtUtil.hasRole(token, "Restaurant Owner")&&!restaurantInfo.get("owner").equals(ownerId)) {
            return "Unauthorized";
        }

        return "OK";
    }
}
