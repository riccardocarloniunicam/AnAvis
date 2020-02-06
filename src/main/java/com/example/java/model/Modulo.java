package com.example.java.model;


import javax.persistence.*;

@Entity
@Table(name = "modulo")
public class Modulo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "luogo_nascita")
    private String luogo_nascita;

    @Column(name = "data_nascita")
    private String data_nascita;

    @Column(name = "residenza")
    private String residenza;

    @Column(name = "indirizzo")
    private String indirzzo;

    @Column(name = "professione")
    private String professione;

    @Column(name = "tellavoro")
    private String tellavoro;

    @Column(name = "telcasa")
    private String telcasa;

    @Column(name = "cellulare")
    private String cellulare;

    @Column(name = "email")
    private String email;

    @Column(name = "data")
    private String data;

    @Column(name = "user_id")
    private int user_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLuogo_nascita() {
        return luogo_nascita;
    }

    public void setLuogo_nascita(String luogo_nascita) {
        this.luogo_nascita = luogo_nascita;
    }

    public String getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(String data_nascita) {
        this.data_nascita = data_nascita;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getIndirzzo() {
        return indirzzo;
    }

    public void setIndirzzo(String indirzzo) {
        this.indirzzo = indirzzo;
    }

    public String getProfessione() {
        return professione;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public String getTellavoro() {
        return tellavoro;
    }

    public void setTellavoro(String tellavoro) {
        this.tellavoro = tellavoro;
    }

    public String getTelcasa() {
        return telcasa;
    }

    public void setTelcasa(String telcasa) {
        this.telcasa = telcasa;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
