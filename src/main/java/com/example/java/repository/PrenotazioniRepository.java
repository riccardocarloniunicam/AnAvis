package com.example.java.repository;

import com.example.java.model.Prenotazioni;
import com.example.java.model.Sede;
import com.example.java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("prenotazioniRepository")
public interface PrenotazioniRepository extends JpaRepository<Prenotazioni, Long> {

   @Query(value = "SELECT * FROM prenotazioni WHERE data =?1 AND orario=?2",nativeQuery = true)
   List<Prenotazioni> findByOrarioAndData(String data,String orario);


}