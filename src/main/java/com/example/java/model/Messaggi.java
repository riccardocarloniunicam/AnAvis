package com.example.java.model;


import javax.persistence.*;

@Entity
@Table(name = "messaggi")
public class Messaggi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "oggetto")
    private String oggetto;

    @Column(name = "messaggio")
    private String messaggio;

    @Column(name = "sede_email")
    private String sede_email;

    @Column(name = "user_id")
    private int user_id;


    @Column(name = "data")
    private String data;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getSede_email() {
        return sede_email;
    }

    public void setSede_email(String sede_email) {
        this.sede_email = sede_email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

