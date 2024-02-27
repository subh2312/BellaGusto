package org.subhankar.address.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressDTO {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String identifier;
    private String tag;
    private String addressType;
}
