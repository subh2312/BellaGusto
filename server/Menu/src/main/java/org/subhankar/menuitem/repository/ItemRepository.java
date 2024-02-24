package org.subhankar.menuitem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.subhankar.menuitem.model.DO.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, String> {
    @Query("SELECT i FROM Item i WHERE i.category.id = :id")
    List<Item> findByCategoryId(String id);
}
