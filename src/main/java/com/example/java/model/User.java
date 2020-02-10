package com.example.java.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "user")
public class User {



        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;


        @Column(name = "email")
        private String email;

        @Column(name = "nome")
        private String nome;

        @Column(name = "cognome")
        private String cognome;

        @Column(name = "password")
        private String password;
        @Column(name = "cf")
        private String cf;

        @Column(name = "active")
        private int active;

        @Column(name = "indirizzo")
        private String indirizzo;

        @Column(name = "citta")
        private String citta;

        @Column(name = "provincia")
        private String provincia;

        @Column(name = "cap")
        private String cap;

        @Column(name = "telefono")
        private int telefono;

        @Column (name = "tipo_documento")
        private String tipo_documento;

        @Column(name = "numerodoc")
        private String numerodoc;

        @Column(name = "grupposanguinio")
        private String grupposanguinio;
        @Column(name = "donatore")
        private int donatore;
        @Column(name = "emergenza")
        private int emergenza;

    @Column(name = "modulo")
    private int modulo;




    @ManyToMany(cascade=CascadeType.ALL)
        @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
        private Set<Role> roles;


    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

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


        public void setCf(String cf){
            this.cf = cf.toUpperCase();
        }
        public String getCf(){
            return cf;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public Set<Role> getRoles() {
            return roles;
        }

        public void setRoles(Set<Role> roles) {
            this.roles = roles;
        }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public void setCap(String cap){
            this.cap = cap;
    }
    public String getCap(){
            return this.cap;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNumerodoc() {
        return numerodoc;
    }

    public void setNumerodoc(String numerodoc) {
        this.numerodoc = numerodoc;
    }

    public String getGrupposanguinio() {
        return grupposanguinio;
    }

    public void setGrupposanguinio(String grupposanguinio) {
        this.grupposanguinio = grupposanguinio;
    }

    public int getDonatore() {
        return donatore;
    }

    public void setDonatore(int donatore) {
        this.donatore = donatore;
    }

    public int getEmergenza() {
        return emergenza;
    }

    public void setEmergenza(int emergenza) {
        this.emergenza = emergenza;
    }




}
