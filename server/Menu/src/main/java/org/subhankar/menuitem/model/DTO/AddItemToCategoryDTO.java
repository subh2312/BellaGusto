package org.subhankar.menuitem.model.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddItemToCategoryDTO {
    private String categoryId;
    private List<String> itemIds;
}
