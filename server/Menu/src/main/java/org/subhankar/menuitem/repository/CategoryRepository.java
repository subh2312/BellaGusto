package org.subhankar.menuitem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subhankar.menuitem.model.DO.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
