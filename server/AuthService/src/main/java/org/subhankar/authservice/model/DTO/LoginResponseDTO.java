package org.subhankar.authservice.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private String token;
}
