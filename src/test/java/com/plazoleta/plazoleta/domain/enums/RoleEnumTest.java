package com.plazoleta.plazoleta.domain.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class RoleEnumTest {


    @Test
    public void testGetId() {
        assertThat(RoleEnum.ADMINISTRATOR.getId()).isEqualTo(1L);
        assertThat(RoleEnum.CLIENTE.getId()).isEqualTo(2L);
        assertThat(RoleEnum.OWNER.getId()).isEqualTo(3L);
        assertThat(RoleEnum.EMPLOYEE.getId()).isEqualTo(4L);
    }

    @Test
    public void testGetNameBd() {
        assertThat(RoleEnum.ADMINISTRATOR.getNameBd()).isEqualTo("ROLE_ADMINISTRATOR");
        assertThat(RoleEnum.CLIENTE.getNameBd()).isEqualTo("ROLE_CLIENT");
        assertThat(RoleEnum.OWNER.getNameBd()).isEqualTo("ROLE_OWNER");
        assertThat(RoleEnum.EMPLOYEE.getNameBd()).isEqualTo("ROLE_EMPLOYEE");
    }

    @Test
    public void testGetName() {
        assertThat(RoleEnum.ADMINISTRATOR.getName()).isEqualTo("ADMINISTRATOR");
        assertThat(RoleEnum.CLIENTE.getName()).isEqualTo("CLIENT");
        assertThat(RoleEnum.OWNER.getName()).isEqualTo("OWNER");
        assertThat(RoleEnum.EMPLOYEE.getName()).isEqualTo("EMPLOYEE");
    }

    @Test
    public void testFromName_ValidName() {
        assertThat(RoleEnum.fromName("ADMINISTRATOR")).isEqualTo(RoleEnum.ADMINISTRATOR);
        assertThat(RoleEnum.fromName("CLIENT")).isEqualTo(RoleEnum.CLIENTE);
        assertThat(RoleEnum.fromName("OWNER")).isEqualTo(RoleEnum.OWNER);
        assertThat(RoleEnum.fromName("EMPLOYEE")).isEqualTo(RoleEnum.EMPLOYEE);
    }

    @Test
    public void testFromName_InvalidName() {
        assertThatThrownBy(() -> RoleEnum.fromName("INVALID"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No se encontró el rol con el nombre: INVALID");
    }

    @Test
    public void testFromId_ValidId() {
        assertThat(RoleEnum.fromId(1L)).isEqualTo(RoleEnum.ADMINISTRATOR);
        assertThat(RoleEnum.fromId(2L)).isEqualTo(RoleEnum.CLIENTE);
        assertThat(RoleEnum.fromId(3L)).isEqualTo(RoleEnum.OWNER);
        assertThat(RoleEnum.fromId(4L)).isEqualTo(RoleEnum.EMPLOYEE);
    }

    @Test
    public void testFromId_InvalidId() {
        assertThatThrownBy(() -> RoleEnum.fromId(99L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No se encontró el rol con el ID: 99");
    }

    @Test
    public void testGetAllRoles() {
        List<RoleEnum> roles = RoleEnum.getAllRoles();
        assertThat(roles).containsExactlyInAnyOrder(
                RoleEnum.ADMINISTRATOR,
                RoleEnum.CLIENTE,
                RoleEnum.OWNER,
                RoleEnum.EMPLOYEE
        );
    }

    @Test
    public void testGetAllIds() {
        List<Long> ids = RoleEnum.getAllIds();
        assertThat(ids).containsExactlyInAnyOrder(1L, 2L, 3L, 4L);
    }
}
