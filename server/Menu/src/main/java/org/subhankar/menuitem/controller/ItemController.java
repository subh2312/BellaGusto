package org.subhankar.menuitem.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subhankar.menuitem.model.DO.Category;
import org.subhankar.menuitem.model.DO.Item;
import org.subhankar.menuitem.model.DTO.CreateItemDTO;
import org.subhankar.menuitem.model.DTO.Result;
import org.subhankar.menuitem.repository.CategoryRepository;
import org.subhankar.menuitem.repository.ItemRepository;
import org.subhankar.menuitem.service.ItemService;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @PostMapping
    public Result createItem(@RequestBody CreateItemDTO createItemDTO, HttpServletRequest request) {
       return itemService.createItem(createItemDTO,request);
    }
}
