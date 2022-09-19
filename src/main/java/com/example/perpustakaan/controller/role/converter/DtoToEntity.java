package com.example.perpustakaan.controller.role.converter;

import com.example.perpustakaan.model.dto.RoleDtoList;
import com.example.perpustakaan.model.dto.RoleDtoSave;
import com.example.perpustakaan.model.entity.Role;


public class DtoToEntity {

    public Role convertDtoToEntity(RoleDtoSave roleDtoSave) {
        Role role = new Role();
        role.setRoleName(roleDtoSave.getRoleName());
        return role;
    }
}
