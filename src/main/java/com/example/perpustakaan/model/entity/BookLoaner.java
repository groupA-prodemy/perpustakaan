package com.example.perpustakaan.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_loaner")
public class BookLoaner {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "Id_Loaner_Seq")
    @SequenceGenerator(name="Id_Loaner_Seq",sequenceName="Id_Loaner_Seq",allocationSize=1)
    @Column(name = "t_id")
    private int id;
    @Column(name = "t_name")
    private String name;
    @Column(name = "t_username")
    private String username;
    @Column(name = "t_password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
