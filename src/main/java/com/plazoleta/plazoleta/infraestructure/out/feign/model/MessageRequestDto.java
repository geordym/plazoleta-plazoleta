package com.plazoleta.plazoleta.infraestructure.out.feign.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageRequestDto {
    private String phoneNumber;
    private String message;
}
