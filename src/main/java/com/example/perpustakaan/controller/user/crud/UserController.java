package com.example.perpustakaan.controller.user.crud;

import com.example.perpustakaan.controller.user.converter.EntityToDto;
import com.example.perpustakaan.model.dto.*;
import com.example.perpustakaan.model.dto.user.UserDtoListUser;
import com.example.perpustakaan.model.dto.user.UserDtoProfileUser;
import com.example.perpustakaan.model.dto.user.UserDtoUpdate;
import com.example.perpustakaan.model.entity.User;
import com.example.perpustakaan.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/users")
public class UserController {


    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    EntityToDto entityToDto = new EntityToDto();

    @GetMapping("/list-user")
    public List<UserDtoListUser> getListUser() {
        List<UserDtoListUser> list = new ArrayList<>();
        for (User i : userRepository.findAll()) {
            list.add(entityToDto.convertEntityToDtoListUser(i));
        }
        return list;
    }

    @GetMapping("/profile/{username}")
    public DefaultResponse<UserDtoProfileUser> profile(@PathVariable ("username") String username) {
        DefaultResponse<UserDtoProfileUser> defaultResponse = new DefaultResponse<>();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Data wasn't found");
        } else {
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setData(entityToDto.convertEntityToDtoProfileUser(optionalUser.get()));
            defaultResponse.setMessage("Data was found");
        }
        return defaultResponse;
    }

    @GetMapping("/profile/{userId}")
    public DefaultResponse<UserDtoProfileUser> profile(@PathVariable ("userId") Integer userId) {
        DefaultResponse<UserDtoProfileUser> defaultResponse = new DefaultResponse<>();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Data wasn't found");
        } else {
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setData(entityToDto.convertEntityToDtoProfileUser(optionalUser.get()));
            defaultResponse.setMessage("Data was found");
        }
        return defaultResponse;
    }

    @DeleteMapping("/delete/{id}")
    public DefaultResponse deleteById(@PathVariable("id") Integer id) {
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<User> optionalUser = userRepository.findById(id);
        try{
            if (optionalUser.isPresent()) {
                userRepository.delete(optionalUser.get());
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setMessage("Succeeded delete data");
            } else {
                defaultResponse.setStatus(Boolean.FALSE);
                defaultResponse.setMessage("Failed to delete data, data was not found");
            }
        }catch(Exception e){
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Delete Failed!!! This data was referenced in user book list, delete them before delete this.");
        }
        return defaultResponse;
    }

    @PutMapping("/update/{id}")
    public DefaultResponse updateById(@PathVariable Integer id, @RequestBody UserDtoUpdate userDtoUpdate) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            User user = optionalUser.get();
            Optional<User> optionalUserUsername = userRepository.findByUsername(userDtoUpdate.getUsername());
            if (optionalUser.isPresent()) {
                user.setName(userDtoUpdate.getName());
                if(optionalUserUsername.isPresent()){
                    if(Objects.equals(user.getUsername(), userDtoUpdate.getUsername())){
                        user.setUsername(userDtoUpdate.getUsername());
                        user.setPassword(userDtoUpdate.getPassword());
                        user.setRoleId(userDtoUpdate.getRoleId());
                        try{
                            userRepository.save(user);
                            defaultResponse.setStatus(Boolean.TRUE);
                            defaultResponse.setData(userDtoUpdate);
                            defaultResponse.setMessage("Succeeded update data");
                        }catch(Exception e){
                            defaultResponse.setStatus(Boolean.FALSE);
                            defaultResponse.setMessage("Failed to update data, Role Id still empty yet in database. Please choose one others");
                        }
                    }
                    else {
                        defaultResponse.setStatus(Boolean.FALSE);
                        defaultResponse.setMessage("Failed to update data, Username was exists. Use the other one");
                    }
                }else {
                    user.setUsername(userDtoUpdate.getUsername());
                    user.setPassword(userDtoUpdate.getPassword());
                    user.setRoleId(userDtoUpdate.getRoleId());
                    try{
                        userRepository.save(user);
                        defaultResponse.setStatus(Boolean.TRUE);
                        defaultResponse.setData(userDtoUpdate);
                        defaultResponse.setMessage("Succeeded update data");
                    }catch(Exception e){
                        defaultResponse.setStatus(Boolean.FALSE);
                        defaultResponse.setMessage("Failed to update data, Role Id still empty yet in database. Please choose one others");
                    }
                }
            }
        } catch (Exception e) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to update data, Id was not found");
        }
        return defaultResponse;
    }
}
