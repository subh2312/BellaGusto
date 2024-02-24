package org.subhankar.authservice.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserClaim {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String dob;
    private String roleCode;
}
