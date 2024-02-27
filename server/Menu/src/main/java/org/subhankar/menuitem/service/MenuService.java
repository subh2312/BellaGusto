package org.subhankar.menuitem.service;

import jakarta.servlet.http.HttpServletRequest;
import org.subhankar.menuitem.model.DO.Menu;
import org.subhankar.menuitem.model.DTO.CreateMenuDTO;
import org.subhankar.menuitem.model.DTO.Result;

public interface MenuService {
    Result createMenu(CreateMenuDTO createMenuDTO, HttpServletRequest request);

    Result getMenuById(String id, HttpServletRequest request);

    Result getMenuByRestaurantId(String id, HttpServletRequest request);
}
