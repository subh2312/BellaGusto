package org.subhankar.menuitem.service.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subhankar.menuitem.config.JwtUtil;
import org.subhankar.menuitem.exception.ResourceNotFoundException;
import org.subhankar.menuitem.integration.model.BasicRestaurantInfoDTO;
import org.subhankar.menuitem.integration.service.RestaurantService;
import org.subhankar.menuitem.model.DO.Category;
import org.subhankar.menuitem.model.DO.Item;
import org.subhankar.menuitem.model.DO.Menu;
import org.subhankar.menuitem.model.DTO.CreateItemDTO;
import org.subhankar.menuitem.model.DTO.CreteCategoryDTO;
import org.subhankar.menuitem.model.DTO.Result;
import org.subhankar.menuitem.repository.CategoryRepository;
import org.subhankar.menuitem.repository.ItemRepository;
import org.subhankar.menuitem.repository.MenuRepository;
import org.subhankar.menuitem.service.CategoryService;
import org.subhankar.menuitem.service.ItemService;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final RestaurantService restaurantService;
    private final JwtUtil jwtUtil;



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

    @Override
    public Result createItem(CreateItemDTO createItemDTO, HttpServletRequest request) {
        Category category = categoryRepository.findById(createItemDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        String restaurantId = category.getMenu().getRestaurantId();
        String authorized = checkIfAuthorized(restaurantId, request);
        if (!authorized.equals("OK")) {
            return Result.builder().code("401").message(authorized).build();
        }
        return Result.builder().code("200").message("Success").data(itemRepository.save(Item
                .builder()
                .name(createItemDTO.getName())
                .description(createItemDTO.getDescription())
                .price(createItemDTO.getPrice())
                .category(category)
                .type(createItemDTO.getType())
                .build())).build();
    }
}
