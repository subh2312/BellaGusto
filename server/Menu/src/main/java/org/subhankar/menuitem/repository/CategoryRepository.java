package org.subhankar.menuitem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.subhankar.menuitem.model.DO.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("select c from Category c where c.menu.id = :id")
    List<Category> findByMenu(String id);
}
