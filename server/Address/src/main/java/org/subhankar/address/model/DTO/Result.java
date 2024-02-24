package org.subhankar.address.model.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    private String code;
    private String message;
    private Object data;
}
