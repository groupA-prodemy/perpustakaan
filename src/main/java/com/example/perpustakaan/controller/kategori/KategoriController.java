package com.example.perpustakaan.controller.kategori;

import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.KategoriDto;
import com.example.perpustakaan.model.entity.Kategori;
import com.example.perpustakaan.repository.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kategori")
public class KategoriController {

    @Autowired
    private KategoriRepository kategoriRepositori;

    @PostMapping("/save")
    public DefaultResponse<KategoriDto> savekategori(@RequestBody KategoriDto kategoriDto){
        Kategori kategori = convertDtoToEntity(kategoriDto);
        DefaultResponse<KategoriDto> response = new DefaultResponse();
        Optional<Kategori> optional = kategoriRepositori.findById(kategoriDto.getIdKategori());
        if(optional.isPresent()){
            response.setStatus(Boolean.FALSE);
            response.setMessage("Error, Data Sudah Tersedia");
        } else {
            kategoriRepositori.save(kategori);
            response.setStatus(Boolean.TRUE);
            response.setMessage("Berhasil Simpan Data");
        }

        return response;
    }
    public Kategori convertDtoToEntity(KategoriDto dto){
        Kategori kategori = new Kategori();
        kategori.setIdKategori(dto.getIdKategori());
        kategori.setNamaKategori(dto.getNamaKategori());

        return kategori;
    }

    @GetMapping("/listkategori")
    public List<KategoriDto> getListKategori(){
        List<KategoriDto> list = new ArrayList();
        for(Kategori kategori :kategoriRepositori.findAll()){
            list.add(convertEntityToDto(kategori));
        }
        return list;
    }

    public KategoriDto convertEntityToDto(Kategori entity){
        KategoriDto dto = new KategoriDto();
        dto.setIdKategori(entity.getIdKategori());
        dto.setNamaKategori(entity.getNamaKategori());

        return dto;
    }

    @DeleteMapping("/delete/{idKategori}")
    public DefaultResponse deleteById(@PathVariable("idKategori") Integer idKategori) {
        DefaultResponse df = new DefaultResponse();
        Optional<Kategori> optionalKategori =kategoriRepositori.findById(idKategori);
        if (optionalKategori.isPresent()){
            kategoriRepositori.delete(optionalKategori.get());
            df.setStatus(Boolean.TRUE);
            df.setMessage("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Data Tidak Ditemukan");
        }
        return df;
    }

    @PutMapping("/update/{idKategori}")
    public DefaultResponse update(@PathVariable("idKategori") Integer idKategori, @RequestBody KategoriDto kategoriDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Kategori> optionalKategori = kategoriRepositori.findById(idKategori);
        Kategori kategori = optionalKategori.get();
        if (optionalKategori.isPresent()) {
            kategori.setIdKategori(kategoriDto.getIdKategori());
            kategori.setNamaKategori(kategoriDto.getNamaKategori());
            kategoriRepositori.save(kategori);
            df.setStatus(Boolean.TRUE);
            df.setMessage("Data berhasil diperbarui");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Data Sudah Terdaftar");
        }
        return df;
    }


}

