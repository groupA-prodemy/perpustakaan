package com.example.perpustakaan.controller.user.converter;

import com.example.perpustakaan.model.dto.UserDtoListUser;
import com.example.perpustakaan.model.dto.UserDtoProfileUser;
import com.example.perpustakaan.model.dto.UserDtoRegister;
import com.example.perpustakaan.model.entity.User;

public class EntityToDto {

    public UserDtoRegister convertEntityToDtoRegister(User user){
        UserDtoRegister userDtoRegister = new UserDtoRegister();
        userDtoRegister.setName(user.getName());
        userDtoRegister.setUsername(user.getUsername());
        userDtoRegister.setPassword(user.getPassword());
        userDtoRegister.setRoleId(user.getRoleId());

        return userDtoRegister;
    }

    public UserDtoListUser convertEntityToDtoListUser(User user){
        UserDtoListUser userDtoListUser = new UserDtoListUser();
        userDtoListUser.setName(user.getName());
        userDtoListUser.setUsername(user.getUsername());
        userDtoListUser.setPassword(user.getPassword());
        userDtoListUser.setRoleName(user.getRole().getRoleName());

        return userDtoListUser;
    }

    public UserDtoProfileUser convertEntityToDtoProfileUser(User user){
        UserDtoProfileUser userDtoProfileUserUser = new UserDtoProfileUser();
        userDtoProfileUserUser.setId(user.getId());
        userDtoProfileUserUser.setName(user.getName());
        userDtoProfileUserUser.setUsername(user.getUsername());
        userDtoProfileUserUser.setPassword(user.getPassword());
        userDtoProfileUserUser.setRoleName(user.getRole().getRoleName());

        return userDtoProfileUserUser;
    }

}