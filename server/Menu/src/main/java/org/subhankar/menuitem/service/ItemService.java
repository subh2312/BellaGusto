package org.subhankar.menuitem.service;

import jakarta.servlet.http.HttpServletRequest;
import org.subhankar.menuitem.model.DTO.CreateItemDTO;
import org.subhankar.menuitem.model.DTO.Result;

public interface ItemService {
    Result createItem(CreateItemDTO createItemDTO, HttpServletRequest request);
}
