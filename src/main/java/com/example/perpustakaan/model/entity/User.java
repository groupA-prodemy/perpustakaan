package com.example.perpustakaan.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Users_Id_Seq")
    @SequenceGenerator(name = "Users_Id_Seq", sequenceName = "Users_Id_Seq", allocationSize = 1)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @OneToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
