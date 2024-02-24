package org.subhankar.user.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserResponseDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String dob;
}
