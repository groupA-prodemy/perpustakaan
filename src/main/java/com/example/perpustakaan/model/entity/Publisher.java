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
    @Column(name = "address_publisher")
    private String addressPublisher;

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

    public String getAddressPublisher() {
        return addressPublisher;
    }

    public void setAddressPublisher(String addressPublisher) {
        this.addressPublisher = addressPublisher;
    }
}
