package org.subhankar.authservice.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String gender;
    private String dob;
    private String role;

}
