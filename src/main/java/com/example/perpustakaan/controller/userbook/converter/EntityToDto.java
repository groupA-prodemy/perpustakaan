package com.example.perpustakaan.controller.userbook.converter;

import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.userbook.UserBookDto;
import com.example.perpustakaan.model.entity.UserBook;

public class EntityToDto {

    public DefaultResponse<UserBookDto> convertEntityToDto (UserBook userBook) {
        UserBookDto userBookDto = new UserBookDto();
        DefaultResponse df = new DefaultResponse();
        try{
            userBookDto.setUserbookId(userBook.getUserBookId());
            userBookDto.setBookId(userBook.getBook().getBookId());
            userBookDto.setBookTitle(userBook.getBook().getBookTitle());
            userBookDto.setUserId(userBook.getUser().getId());
            userBookDto.setUserName(userBook.getUser().getUsername());
            userBookDto.setStartDate(userBook.getStartDate());
            userBookDto.setDueDate(userBook.getDueDate());
            userBookDto.setReturnDate(userBook.getReturnDate());
            df.setStatus(Boolean.TRUE);
            df.setMessage("Ini berhasil");
        }catch (Exception e){
            df.setStatus(Boolean.FALSE);
            df.setMessage("Ini error karena ID ga ada");

        }
        return df;
    }
}
