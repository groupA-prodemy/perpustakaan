package com.example.perpustakaan.controller.login.crud;

import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.LoginDto;
import com.example.perpustakaan.model.entity.Admin;
import com.example.perpustakaan.model.entity.BookLoaner;
import com.example.perpustakaan.repository.AdminRepository;
import com.example.perpustakaan.repository.BookLoanerRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AdminRepository adminRepository;
    private final BookLoanerRepository bookLoanerRepository;

    public LoginController(AdminRepository adminRepository, BookLoanerRepository bookLoanerRepository) {
        this.adminRepository = adminRepository;
        this.bookLoanerRepository = bookLoanerRepository;
    }

    @PostMapping("/loginadmin")
    public DefaultResponse LoginAdmin(@RequestBody LoginDto loginDto){
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<Admin> optionalAdminUsername = adminRepository.findByUsername(loginDto.getUsername());
        Optional<Admin> optionalAdminPassword = adminRepository.findByPassword(loginDto.getPassword());
        if (optionalAdminUsername.isPresent()){
            if(optionalAdminPassword.isPresent()){
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setMessage("Succeeded Login");
            }else {
                defaultResponse.setStatus(Boolean.FALSE);
                defaultResponse.setMessage("Wrong Password!!!");
            }
        }else {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Username Unregistered");
        }
        return defaultResponse;
    }

    @PostMapping("/loginloaner")
    public DefaultResponse LoginBookLoaner(@RequestBody LoginDto loginDto){
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<BookLoaner> optionalBookLoanerUsername = bookLoanerRepository.findByUsername(loginDto.getUsername());
        Optional<BookLoaner> optionalBookLoanerPassword = bookLoanerRepository.findByPassword(loginDto.getPassword());
        if (optionalBookLoanerUsername.isPresent()){
            if(optionalBookLoanerPassword.isPresent()){
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setMessage("Succeeded Login");
            }else {
                defaultResponse.setStatus(Boolean.FALSE);
                defaultResponse.setMessage("Wrong Password!!!");
            }
        }else {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Username Unregistered");
        }
        return defaultResponse;
    }
}
