package org.subhankar.authservice.model.DO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    private String id;
    private String code;
    private String name;
    private String description;
}
