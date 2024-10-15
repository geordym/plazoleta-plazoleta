package com.plazoleta.plazoleta.domain.model.external;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderLog {
    private Long orderId;
    private Long restaurantId;
    private Long customerId;
    private Long employeeId;
    private String status;
    private String description;
}
