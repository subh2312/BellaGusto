package org.subhankar.menuitem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.subhankar.menuitem.model.DO.Category;
import org.subhankar.menuitem.model.DO.Item;
import org.subhankar.menuitem.model.DO.Menu;
import org.subhankar.menuitem.model.DTO.AddCategoryToMenuDTO;
import org.subhankar.menuitem.model.DTO.CreateMenuDTO;
import org.subhankar.menuitem.model.DTO.Result;
import org.subhankar.menuitem.repository.CategoryRepository;
import org.subhankar.menuitem.repository.ItemRepository;
import org.subhankar.menuitem.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @PostMapping
    public Result createMenu(@RequestBody CreateMenuDTO createMenuDTO) {
        return Result.builder().code("200").message("Success").data(menuRepository.save(Menu
                .builder()
                .code(createMenuDTO.getCode())
                .restaurantId(createMenuDTO.getRestaurantId())
                .build())).build();
    }

    @PostMapping("/category")
    public Result addCategoryToMenu(@RequestBody AddCategoryToMenuDTO categoryIds){
        Menu menu = menuRepository.findById(categoryIds.getMenuId()).orElseThrow(() -> new RuntimeException("Menu not found"));
        List<Category> categories = new ArrayList<>();
        for (String categoryId : categoryIds.getCategoryIds()) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
            categories.add(category);
        }
        menu.setCategories(categories);
        return Result.builder().code("200").message("Success").data(menuRepository.save(menu)).build();

    }

    @GetMapping("/{id}")
    public Result getMenu(@PathVariable String id){
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"));
        List<Category> categories = menu.getCategories();
        for (Category category : categories) {
            List<Item> items = itemRepository.findByCategoryId(category.getId());
            category.setItems(items);
        }
        return Result.builder().code("200").message("Success").data(menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"))).build();
    }

    @GetMapping("/restaurant/{id}")
    public Menu getMenuByRestaurantId(@PathVariable String id){
        Menu menu = menuRepository.findByRestaurantId(id).orElseThrow(() -> new RuntimeException("Menu not found"));
        List<Category> categories = menu.getCategories();
        for (Category category : categories) {
            List<Item> items = itemRepository.findByCategoryId(category.getId());
            category.setItems(items);
        }
        return menu;
    }

}
