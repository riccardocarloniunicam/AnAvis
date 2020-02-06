package com.example.java.model;

import javax.persistence.*;

@Entity
@Table(name = "sede")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sede_id;


    @Column(name = "regione")
    private String regione;

    @Column(name = "città")
    private String citta;

    @Column(name = "presidente")
    private String presidente;

    @Column(name = "indirizzo")
    private String indirizzo;


    @Column(name = "cap")
    private String cap;

    @Column(name = "provincia")
    private String provincia;



    @Column(name = "telefono")
    private String telefono;

    @Column(name = "cellulare")
    private String cellulare;



    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "sito")
    private String sito;


    @Column(name = "social")
    private String social;

    @Column(name = "regionale")
    private int regionale;


    public Integer getSede_id() {
        return sede_id;
    }

    public void setSede_id(Integer sede_id) {
        this.sede_id = sede_id;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSito() {
        return sito;
    }

    public void setSito(String sito) {
        this.sito = sito;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public int getRegionale() {
        return regionale;
    }

    public void setRegionale(int regionale) {
        this.regionale = regionale;
    }
}
