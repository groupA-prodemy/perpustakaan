package com.example.perpustakaan.model.dto;

public class PublisherDto {

    private Integer idPublisher;
    private String publisherName;
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
