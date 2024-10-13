package com.plazoleta.plazoleta.application.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private Long nit;
    private String address;
    private String phone;
    private String logoUrl;
    private Long ownerId;
}
