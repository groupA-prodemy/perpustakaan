package com.example.perpustakaan.controller.role.converter;

import com.example.perpustakaan.model.dto.RoleDtoList;
import com.example.perpustakaan.model.entity.Role;


public class EntityToDto {

    public RoleDtoList convertEntityToDto(Role role){
        RoleDtoList roleDtoList = new RoleDtoList();
        roleDtoList.setRoleId(role.getRoleId());
        roleDtoList.setRoleName(role.getRoleName());
        return roleDtoList;
    }

}
