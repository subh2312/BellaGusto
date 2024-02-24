package org.subhankar.menuitem.model.DTO;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCategoryToMenuDTO {
    private String menuId;
    private List<String> categoryIds;
}
