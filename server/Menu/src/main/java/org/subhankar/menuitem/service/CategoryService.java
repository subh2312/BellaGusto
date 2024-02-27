package org.subhankar.menuitem.service;

import jakarta.servlet.http.HttpServletRequest;
import org.subhankar.menuitem.model.DTO.CreteCategoryDTO;
import org.subhankar.menuitem.model.DTO.Result;

public interface CategoryService {
    Result createCategory(CreteCategoryDTO createCategoryDTO, HttpServletRequest request);

    Result getCategoryById(String id, HttpServletRequest request);

    Result getCategoriesByMenuId(String id, HttpServletRequest request);
}
