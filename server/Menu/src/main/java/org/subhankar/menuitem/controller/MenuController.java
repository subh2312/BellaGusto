package org.subhankar.menuitem.controller;

import jakarta.servlet.http.HttpServletRequest;
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
import org.subhankar.menuitem.service.MenuService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    @PostMapping
    public Result createMenu(@RequestBody CreateMenuDTO createMenuDTO, HttpServletRequest request) {
        return menuService.createMenu(createMenuDTO,request);
    }

    @GetMapping("/{id}")
    public Result getMenu(@PathVariable String id, HttpServletRequest request){
        return menuService.getMenuById(id,request);
    }

    @GetMapping("/restaurant/{id}")
    public Result getMenuByRestaurantId(@PathVariable String id,HttpServletRequest request){
        return menuService.getMenuByRestaurantId(id,request);
    }

}
