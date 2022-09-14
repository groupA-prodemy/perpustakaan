package com.example.perpustakaan.controller.user.converter;

import com.example.perpustakaan.model.dto.UserDto;
import com.example.perpustakaan.model.entity.Role;
import com.example.perpustakaan.model.entity.User;
import com.example.perpustakaan.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DtoToEntity {


    public User convertDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setRoleId(userDto.getRoleId());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;

    }

}
