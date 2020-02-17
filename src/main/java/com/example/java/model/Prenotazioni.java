package com.example.java.model;

import javax.persistence.*;


@Entity
@Table(name = "prenotazioni")
public class Prenotazioni {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nome")
    private  String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data")
    private String data;

    @Column(name = "orario")
    private String orario;

    @Column(name = "stato")
    private String stato;

    @Column(name = "email")
    private String email;


    @Column(name = "sede_id")
    private Integer sede_id;

    @Column(name = "user_id")
    private int user_id;
    @Column(name = "analisi")
    private int analisi;

    public int getAnalisi() {
        return analisi;
    }

    public void setAnalisi(int analisi) {
        this.analisi = analisi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario = orario;
    }

    public Integer getSede_id() {
        return sede_id;
    }

    public void setSede_id(Integer sede_id) {
        this.sede_id = sede_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
