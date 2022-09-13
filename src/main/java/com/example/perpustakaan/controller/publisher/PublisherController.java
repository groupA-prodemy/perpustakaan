package com.example.perpustakaan.controller.publisher;

import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.PublisherDto;
import com.example.perpustakaan.model.entity.Publisher;
import com.example.perpustakaan.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publisher")

public class PublisherController {

    @Autowired
    private PublisherRepository publisherRepository;

    @PostMapping("/save")
    public DefaultResponse<PublisherDto> savepublisher(@RequestBody PublisherDto publisherDto){
        Publisher publisher = convertDtoToEntity(publisherDto);
        DefaultResponse<PublisherDto> response = new DefaultResponse();
        Optional<Publisher> optional = publisherRepository.findById(publisherDto.getId());
        if(optional.isPresent()){
            response.setStatus(Boolean.FALSE);
            response.setMessage("Error, Data Sudah Tersedia");
        } else {
            publisherRepository.save(publisher);
            response.setStatus(Boolean.TRUE);
            response.setMessage("Berhasil Simpan Data");
        }

        return response;
    }

    public Publisher convertDtoToEntity(PublisherDto dto){
        Publisher publisher = new Publisher();
        publisher.setIdPublisher(dto.getId());
        publisher.setNamePublisher(dto.getName());
        publisher.setAdressPublisher(dto.getAdress());

        return publisher;
    }

    @GetMapping("/list")
    public List<PublisherDto> getList(){
        List<PublisherDto> list = new ArrayList();
        for(Publisher publisher :publisherRepository.findAll()){
            list.add(convertEntityToDto(publisher));
        }
        return list;
    }

    public PublisherDto convertEntityToDto(Publisher entity){
        PublisherDto dto = new PublisherDto();
        dto.setId(entity.getIdPublisher());
        dto.setName(entity.getNamePublisher());
        dto.setAdress(entity.getAdressPublisher());

        return dto;
    }

    @PutMapping("/update/{id}")
    public DefaultResponse updateById (@PathVariable Integer id, @RequestBody PublisherDto publisherDto){
        DefaultResponse defaultResponse = new DefaultResponse();
        try{
            Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
            Publisher publisher = optionalPublisher.get();
            if (optionalPublisher.isPresent()){
                publisher.setNamePublisher(publisherDto.getName());
                publisher.setAdressPublisher(publisherDto.getAdress());
                publisherRepository.save(publisher);
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setData(publisherDto);
                defaultResponse.setMessage("Succeeded update data");
            }
        }catch(Exception e){
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to update data, Id was not found");
        }
        return defaultResponse;
    }

    @DeleteMapping("/delete/{idpublisher}")
    public DefaultResponse deleteByPublisherId (@PathVariable Integer idpublisher){
        DefaultResponse df = new DefaultResponse();
        Optional<Publisher> optionalPublisher = publisherRepository.findById(idpublisher);
        if (optionalPublisher.isPresent()){
            publisherRepository.delete(optionalPublisher.get());
            df.setStatus(Boolean.TRUE);
            df.setMessage("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Data Tidak Ditemukan");
        }
        return df;

    }


}