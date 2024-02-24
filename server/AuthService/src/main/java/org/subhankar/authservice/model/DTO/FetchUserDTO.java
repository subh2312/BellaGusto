package org.subhankar.authservice.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FetchUserDTO {
    private String email;

}
