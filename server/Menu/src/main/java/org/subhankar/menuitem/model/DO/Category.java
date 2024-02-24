package org.subhankar.menuitem.model.DO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    @ManyToOne
    @JsonBackReference
    private Menu menu;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Item> items;
}
