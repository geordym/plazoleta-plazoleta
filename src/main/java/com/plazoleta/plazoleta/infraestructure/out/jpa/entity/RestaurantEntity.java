package com.plazoleta.plazoleta.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@Getter
@Setter
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long nit;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    private String logoUrl;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

}