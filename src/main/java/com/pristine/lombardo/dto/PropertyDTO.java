package com.pristine.lombardo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PropertyDTO {
    private String name;
    private String type;
    private Float price;
    private Long clientId;
}
