package org.subhankar.user.model.DTO;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String gender;
    private String dob;
    private String roleId;

}
