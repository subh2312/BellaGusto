package org.subhankar.restaurant.model.DO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    private String id;
    private String name;
    private String description;
    private List<Item> items;
}
