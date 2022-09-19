package com.example.perpustakaan.controller.user.converter;

import com.example.perpustakaan.model.dto.user.UserDtoListUser;
import com.example.perpustakaan.model.dto.user.UserDtoProfileUser;
import com.example.perpustakaan.model.dto.user.UserDtoRegister;
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
        userDtoListUser.setUserId(user.getId());
        userDtoListUser.setName(user.getName());
        userDtoListUser.setUsername(user.getUsername());
        userDtoListUser.setRoleId(user.getRoleId());
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
