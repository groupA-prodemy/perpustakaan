package com.example.perpustakaan.controller.admin.converter;

import com.example.SistemPerpustakaan.model.dto.AdminDto;
import com.example.SistemPerpustakaan.model.entity.Admin;

public class DtoToEntity {

    public Admin convertDtoToEntity(AdminDto adminDto){
        Admin admin = new Admin();
        admin.setName(adminDto.getName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());

        return admin;
    }

}
