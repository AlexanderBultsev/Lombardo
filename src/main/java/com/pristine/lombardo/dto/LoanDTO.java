package com.pristine.lombardo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoanDTO {
    private Long clientId;
    private Long propertyId;
}
