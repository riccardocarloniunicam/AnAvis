package com.example.java.repository;

import com.example.java.model.Prenotazioni;
import com.example.java.model.Sede;
import com.example.java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("prenotazioniRepository")
public interface PrenotazioniRepository extends JpaRepository<Prenotazioni, Long> {

   @Query(value = "SELECT * FROM prenotazioni WHERE data =?1 AND orario=?2 AND sede_id=?3",nativeQuery = true)
   List<Prenotazioni> findByOrarioAndData(String data,String orario,Integer sede_id);

   @Query(value = " select count(*) from prenotazioni where data=?1 and orario =?2 and sede_id = ?3",nativeQuery = true)
   Integer prenotazioneExist(String data,String orario,Integer sede_id);


   @Query(value = "SELECT * FROM prenotazioni WHERE nome=?1",nativeQuery = true)
   List<Prenotazioni> findByNome(String nome);


   @Query(value = "SELECT * FROM prenotazioni WHERE user_id=?1 and stato='PRENOTATA'",nativeQuery = true)
   Prenotazioni findPrenotazioneByUserID(Integer id);



   @Query(value = "SELECT * FROM prenotazioni WHERE id=?1",nativeQuery = true)
   Prenotazioni findPrenotazioneById(Integer id);

   //vede se l'utente ha già effettuato la sua prenotazione
   @Query(value = "SELECT count(*) FROM prenotazioni where user_id = ?1 and stato='PRENOTATA'",nativeQuery = true)
   Integer prenotazioneGiaEffettuata(Integer user_id);

   @Modifying
   @Query(value = "delete from prenotazioni where user_id=?1",nativeQuery = true)
   void deletePrenotazione(Integer id);


   //trova prenotazioni in una sede in base all'email e dati dell'utente
   @Query(value = "select *  from prenotazioni inner join utente_sedi on (prenotazioni.sede_id = utente_sedi.sede_id) inner join user on (user.id=prenotazioni.user_id) and utente_sedi.email = ?1 and prenotazioni.stato ='PRENOTATA' order by data ,orario desc",nativeQuery = true)
   List<Prenotazioni> retriveAppuntamentiPerSede(String email);

   @Query(value = "select *  from prenotazioni inner join utente_sedi on (prenotazioni.sede_id = utente_sedi.sede_id) inner join user on (user.id=prenotazioni.user_id) and utente_sedi.email = ?1 and prenotazioni.stato ='ESEGUITA' order by data ,orario desc",nativeQuery = true)
   List<Prenotazioni> retriveAppuntamentiPerSedeEseguiti(String email);





   @Query(value = "SELECT count(*) FROM prenotazioni WHERE stato = 'PRENOTATA' and sede_id =?1",nativeQuery = true)
   Integer retrivePrenotazioniCount(Integer sede_id);


   @Query(value = "SELECT count(*) FROM prenotazioni WHERE stato = 'ESEGUITA' and sede_id =?1",nativeQuery = true)
   Integer retrivePrenotazioniCountEseguite(Integer sede_id);

   @Query(value = "SELECT count(*) FROM prenotazioni WHERE stato = 'ESEGUITA' and analisi = 0 and sede_id =?1",nativeQuery = true)
   Integer analisiDaCaricare(Integer sede_id);



   @Modifying
   @Query(value = "update prenotazioni set stato = 'ESEGUITA' where id =?1",nativeQuery = true)
   Integer updateStato(Integer id);


   @Modifying
   @Query(value = "update prenotazioni set analisi = 1 where id =?1",nativeQuery = true)
   Integer updateParametroAnalisi(Integer id);


   //Funzione di ricerca per nome
   @Query(value = "SELECT * FROM prenotazioni WHERE nome LIKE %?1%  and stato ='ESEGUITA' and sede_id=?2 order by analisi,cognome,data",nativeQuery = true)
   List<Prenotazioni> findByNameLike(String name,Integer sede_id);


}