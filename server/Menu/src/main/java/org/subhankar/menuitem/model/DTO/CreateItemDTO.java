package org.subhankar.menuitem.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateItemDTO {
    private String name;

    private Double price;

    private String description;

    private String imageUrl;

    private String calories;
    private String categoryId;
    private String type;
}
