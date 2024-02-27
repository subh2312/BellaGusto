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
import org.subhankar.menuitem.model.DTO.CreteCategoryDTO;
import org.subhankar.menuitem.model.DTO.Result;
import org.subhankar.menuitem.repository.CategoryRepository;
import org.subhankar.menuitem.repository.ItemRepository;
import org.subhankar.menuitem.repository.MenuRepository;
import org.subhankar.menuitem.service.CategoryService;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final RestaurantService restaurantService;
    private final JwtUtil jwtUtil;

    @Override
    public Result createCategory(CreteCategoryDTO createCategoryDTO, HttpServletRequest request) {
        Menu menu = menuRepository.findById(createCategoryDTO.getMenuId()).orElseThrow(() -> new ResourceNotFoundException("Menu","id",createCategoryDTO.getMenuId()));
        String checkIfAuthorized = checkIfAuthorized(menu.getRestaurantId(), request);
        if (!checkIfAuthorized.equals("OK")) {
            return Result.builder().code("400").message(checkIfAuthorized).build();
        }
        return Result.builder().code("200").message("Success").data(categoryRepository.save(Category
                .builder()
                .name(createCategoryDTO.getName())
                .description(createCategoryDTO.getDescription())
                .menu(menu)
                .build())).build();
    }

    @Override
    public Result getCategoryById(String id, HttpServletRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","id",id));
        Menu menu = category.getMenu();
        String checkIfAuthorizedToView = checkIfAuthorizedToView(menu.getRestaurantId(), request);
        if (!checkIfAuthorizedToView.equals("OK")) {
            return Result.builder().code("400").message(checkIfAuthorizedToView).build();
        }
        List<Item> items = itemRepository.findByCategoryId(category.getId());
        category.setItems(items);
        return Result.builder().code("200").message("Success").data(category).build();
    }

    @Override
    public Result getCategoriesByMenuId(String id, HttpServletRequest request) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Menu","id",id));
        String checkIfAuthorizedToView = checkIfAuthorizedToView(menu.getRestaurantId(), request);
        if (!checkIfAuthorizedToView.equals("OK")) {
            return Result.builder().code("400").message(checkIfAuthorizedToView).build();
        }
        List<Category> categories = categoryRepository.findByMenu(id);
        for (Category category : categories) {
            List<Item> items = itemRepository.findByCategoryId(category.getId());
            category.setItems(items);
        }
        return Result.builder().code("200").message("Success").data(categories).build();
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
