package org.subhankar.restaurant.model.DO;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private String phone;
    private String logo;
    private String openingTime;
    private String closingTime;
    private String owner;
    @Transient
    private List<String> addresses = new ArrayList<>();
    @Transient
    private Menu menu;
}
