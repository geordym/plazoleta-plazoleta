package com.plazoleta.plazoleta.application.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDeliverRequestDto {
    private Long orderId;
    private Integer reclaimCode;
}
