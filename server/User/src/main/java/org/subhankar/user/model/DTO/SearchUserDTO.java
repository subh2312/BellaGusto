package org.subhankar.user.model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchUserDTO {
    private String name;
    private String email;
    private String phone;
    private String city;
    private String state;
    private String country;
    private String dob;
    private String role;


}
