package com.plazoleta.plazoleta.domain.model.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String lastName;
    private Long identityDocument;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String password;

    private Role role;

    public User(Role role) {
        this.role = role;
    }

    public User(Long id, String name, String lastName, Long identityDocument, String phoneNumber, LocalDate dateOfBirth, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identityDocument = identityDocument;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }

}
