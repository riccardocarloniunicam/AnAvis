package com.example.java.service;


import com.example.java.model.Sede;


import java.util.List;



public interface SedeService {
    List<Sede> listAll();

    Sede getSede(Integer id);




}