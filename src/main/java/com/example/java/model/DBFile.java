package com.example.java.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class DBFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

    @Column(name = "utente")
    private Integer user_id;

    @Column(name = "pid")
    private Integer prenotazione_id;
    @Lob
    private byte[] data;

    public DBFile() {

    }

    public DBFile(String fileName, String fileType, byte[] data,Integer user_id,Integer prenotazione_id) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.user_id = user_id;
        this.prenotazione_id = prenotazione_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPrenotazione_id() {
        return prenotazione_id;
    }

    public void setPrenotazione_id(Integer prenotazione_id) {
        this.prenotazione_id = prenotazione_id;
    }
}