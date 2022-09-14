package com.example.perpustakaan.controller.role.converter;

import com.example.perpustakaan.model.dto.RoleDto;
import com.example.perpustakaan.model.entity.Role;


public class EntityToDto {

    public RoleDto convertEntityToDto(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }

}
