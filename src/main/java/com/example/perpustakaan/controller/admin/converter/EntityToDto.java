package com.example.perpustakaan.controller.admin.converter;

import com.example.perpustakaan.model.dto.AdminDto;
import com.example.perpustakaan.model.entity.Admin;

public class EntityToDto {

    public AdminDto convertEntityToDto(Admin admin){
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setName(admin.getName());
        adminDto.setUsername(admin.getUsername());
        adminDto.setPassword(admin.getPassword());

        return adminDto;
    }

}
