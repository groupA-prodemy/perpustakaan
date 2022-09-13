package com.example.perpustakaan.model.entity;

import javax.persistence.*;

@Entity
@Table(name ="t_publisher")

public class Publisher {

    @Id
    @GeneratedValue
    @Column(name = "id_publisher")
    private Integer idPublisher;
    @Column(name = "name_publisher")
    private String namePublisher;
    @Column(name = "adress_publisher")
    private String adressPublisher;

    public Integer getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(Integer idPublisher) {
        this.idPublisher = idPublisher;
    }

    public String getNamePublisher() {
        return namePublisher;
    }

    public void setNamePublisher(String namePublisher) {
        this.namePublisher = namePublisher;
    }

    public String getAdressPublisher() {
        return adressPublisher;
    }

    public void setAdressPublisher(String adressPublisher) {
        this.adressPublisher = adressPublisher;
    }
}
