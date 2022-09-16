package com.example.perpustakaan.controller.user.converter;

import com.example.perpustakaan.model.dto.UserDtoRegister;
import com.example.perpustakaan.model.entity.User;

public class DtoToEntity {


    public User convertDtoToEntityRegister(UserDtoRegister userDtoRegister) {
        User user = new User();
        user.setName(userDtoRegister.getName());
        user.setUsername(userDtoRegister.getUsername());
        user.setPassword(userDtoRegister.getPassword());
        user.setRoleId(userDtoRegister.getRoleId());

        return user;

    }

}
