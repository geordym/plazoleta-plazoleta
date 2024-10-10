package com.plazoleta.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    private String name;
    private Long nit;
    private String address;
    private String phone;
    private String logoUrl;
    private Long ownerId;
}
