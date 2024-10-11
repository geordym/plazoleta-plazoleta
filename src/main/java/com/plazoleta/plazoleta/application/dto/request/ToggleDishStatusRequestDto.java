package com.plazoleta.plazoleta.application.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ToggleDishStatusRequestDto {
    private Long dishId;
    private Boolean active;
}
