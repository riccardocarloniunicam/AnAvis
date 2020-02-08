package com.example.java.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "utente_sedi")
public class Utente_Sedi {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "sede_id")
    private int sede_id;

    @Column(name = "role")
    private String role;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSede_id() {
        return sede_id;
    }

    public void setSede_id(int sede_id) {
        this.sede_id = sede_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
