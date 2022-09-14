package com.example.perpustakaan.controller.user.converter;

import com.example.perpustakaan.model.dto.UserDto;
import com.example.perpustakaan.model.entity.User;

public class EntityToDto {

    public UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRoleId(user.getRoleId());
        userDto.setRoleName(user.getRoleName());

        return userDto;
    }

}
