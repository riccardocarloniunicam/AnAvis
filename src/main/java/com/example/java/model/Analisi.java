package com.example.java.model;


import javax.persistence.*;

@Entity
@Table(name = "analisi")
public class Analisi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "prenotazione_id")
    private int prenotazione_id;

    @Column(name = "user_id")
    private int user_id;


    @Column(name = "download_path")
    private String download_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrenotazione_id() {
        return prenotazione_id;
    }

    public void setPrenotazione_id(int prenotazione_id) {
        this.prenotazione_id = prenotazione_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDownload_path() {
        return download_path;
    }

    public void setDownload_path(String download_path) {
        this.download_path = download_path;
    }
}
