package org.subhankar.restaurant.model.DO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {
    private String id;
    private String code;
    private List<Category> categories;
}
