package com.example.perpustakaan.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_author")
    @Column(name = "id_author")
    private Integer authorId;
    @Column(name = "nama_author")
    private String authorName;
    @Column(name = "alamat_author")
    private String authorAddress;
    @Column(name = "no_hp")
    private String noHp;

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(String authorAddress) {
        this.authorAddress = authorAddress;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
