package org.subhankar.menuitem.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreteCategoryDTO {
    private String name;
    private String description;
    private String menuId;
}
