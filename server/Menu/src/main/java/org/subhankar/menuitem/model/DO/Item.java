package org.subhankar.menuitem.model.DO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Double price;

    private String description;

    private String imageUrl;

    private String calories;

    private String type;

    @ManyToOne
    @JsonBackReference
    private Category category;
}
