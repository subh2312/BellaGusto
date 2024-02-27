package org.subhankar.menuitem.integration.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicRestaurantInfoDTO {
    private String id;
    private String name;
    private String owner;
    private String status;
}
