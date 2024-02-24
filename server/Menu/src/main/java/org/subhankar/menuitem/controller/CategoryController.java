package org.subhankar.menuitem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.subhankar.menuitem.exception.ResourceNotFoundException;
import org.subhankar.menuitem.model.DO.Category;
import org.subhankar.menuitem.model.DO.Item;
import org.subhankar.menuitem.model.DO.Menu;
import org.subhankar.menuitem.model.DTO.AddItemToCategoryDTO;
import org.subhankar.menuitem.model.DTO.CreteCategoryDTO;
import org.subhankar.menuitem.model.DTO.Result;
import org.subhankar.menuitem.repository.CategoryRepository;
import org.subhankar.menuitem.repository.ItemRepository;
import org.subhankar.menuitem.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;
    private final ItemRepository itemRepository;

    @PostMapping
    public Result createCategory(@RequestBody CreteCategoryDTO createCategoryDTO) {
        Menu menu = menuRepository.findById(createCategoryDTO.getMenuId()).orElseThrow(() -> new ResourceNotFoundException("Menu","id",createCategoryDTO.getMenuId()));
        return Result.builder().code("200").message("Success").data(categoryRepository.save(Category
                .builder()
                .name(createCategoryDTO.getName())
                .description(createCategoryDTO.getDescription())
                .menu(menu)
                .build())).build();
    }

    @GetMapping("/{id}")
    public Result getCategory(@PathVariable String id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","id",id));
        List<Item> items = itemRepository.findByCategoryId(category.getId());
        category.setItems(items);
        return Result.builder().code("200").message("Success").data(category).build();
    }
}
