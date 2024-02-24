package org.subhankar.user.model.DO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String identifier;
    private String tag;
}
