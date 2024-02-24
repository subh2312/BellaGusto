package org.subhankar.menuitem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subhankar.menuitem.model.DO.Menu;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, String> {
    Optional<Menu> findByRestaurantId(String id);
}
