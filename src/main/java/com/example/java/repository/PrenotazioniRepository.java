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

   @Query(value = "SELECT * FROM prenotazioni WHERE data =?1 AND orario=?2 AND sede_id=?3",nativeQuery = true)
   List<Prenotazioni> findByOrarioAndData(String data,String orario,Integer sede_id);

   @Query(value = " select count(*) from prenotazioni where data=?1 and orario =?2 and sede_id = ?3",nativeQuery = true)
   Integer prenotazioneExist(String data,String orario,Integer sede_id);



   @Query(value = "SELECT * FROM prenotazioni WHERE user_id=?1",nativeQuery = true)
   Prenotazioni findPrenotazioneByUserID(Integer id);



   @Query(value = "SELECT * FROM prenotazioni WHERE id=?1",nativeQuery = true)
   Prenotazioni findPrenotazioneById(Integer id);

   //vede se l'utente ha già effettuato la sua prenotazione
   @Query(value = "SELECT count(*) FROM prenotazioni where user_id = ?1",nativeQuery = true)
   Integer prenotazioneGiaEffettuata(Integer user_id);

}