package org.subhankar.menuitem.model.DO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String code;

    private String restaurantId;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Category> categories;
}
