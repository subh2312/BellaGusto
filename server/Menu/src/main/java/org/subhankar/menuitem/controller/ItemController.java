package org.subhankar.menuitem.controller;

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

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @PostMapping
    public Result createItem(@RequestBody CreateItemDTO createItemDTO) {
        Category category = categoryRepository.findById(createItemDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
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
