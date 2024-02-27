package org.subhankar.address.model.DO;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String state;
        private String country;
        private String zipCode;
        private String identifier;
        private String tag;
        @Enumerated(EnumType.STRING)
        private AddressType type;
        @Enumerated(EnumType.STRING)
        private Status status;
}
