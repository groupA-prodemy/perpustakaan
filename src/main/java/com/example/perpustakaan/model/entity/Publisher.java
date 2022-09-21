package com.example.perpustakaan.model.entity;

import javax.persistence.*;

@Entity
@Table(name ="t_publisher")

public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_user")
    @Column(name = "id_publisher")
    private Integer idPublisher;
    @Column(name = "name_publisher", unique = true)
    private String publisherName;
    @Column(name = "address_publisher")
    private String addressPublisher;

    public Integer getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(Integer idPublisher) {
        this.idPublisher = idPublisher;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getAddressPublisher() {
        return addressPublisher;
    }

    public void setAddressPublisher(String addressPublisher) {
        this.addressPublisher = addressPublisher;
    }
}
