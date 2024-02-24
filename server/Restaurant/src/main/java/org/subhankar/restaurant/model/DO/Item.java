package org.subhankar.restaurant.model.DO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private String id;
    private String name;
    private Double price;
    private String description;
    private String imageUrl;
    private String calories;
}
