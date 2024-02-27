package org.subhankar.menuitem.controller;

import jakarta.servlet.http.HttpServletRequest;
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
import org.subhankar.menuitem.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;
    private final ItemRepository itemRepository;

    private final CategoryService categoryService;

    @PostMapping
    public Result createCategory(@RequestBody CreteCategoryDTO createCategoryDTO, HttpServletRequest request){
        return categoryService.createCategory(createCategoryDTO,request);
    }

    @GetMapping("/{id}")
    public Result getCategory(@PathVariable String id,HttpServletRequest request){
        return categoryService.getCategoryById(id,request);
    }

    @GetMapping("/menu/{id}")
    public Result getCategoriesByMenuId(@PathVariable String id,HttpServletRequest request){
        return categoryService.getCategoriesByMenuId(id,request);
    }
}
