package com.example.perpustakaan.controller.role.converter;

import com.example.perpustakaan.model.dto.RoleDto;
import com.example.perpustakaan.model.entity.Role;


public class DtoToEntity {

    public Role convertDtoToEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setRoleId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());
        return role;
    }
}
