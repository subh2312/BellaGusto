package org.subhankar.menuitem.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMenuDTO {
    private String code;
    private String restaurantId;
}
