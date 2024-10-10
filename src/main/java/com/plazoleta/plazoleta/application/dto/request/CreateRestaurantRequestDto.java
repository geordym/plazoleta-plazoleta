package com.plazoleta.plazoleta.application.dto.request;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRestaurantRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "^(?!\\d+$)[\\w\\s]+$", message = "El nombre no puede ser solo números")
    private String name;

    @NotNull(message = "El NIT es obligatorio")
    @Digits(integer = 20, fraction = 0, message = "El NIT debe ser numérico")
    private Long nit;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "El teléfono debe ser numérico y máximo de 13 caracteres")
    private String phone;

    @NotBlank(message = "La URL del logo es obligatoria")
    @URL(message = "La URL del logo debe ser una URL válida")
    private String logoUrl;

    @NotNull(message = "El ID del propietario es obligatorio")
    private Long ownerId;
}
