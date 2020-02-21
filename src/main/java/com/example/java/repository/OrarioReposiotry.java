package com.example.java.repository;


import com.example.java.model.Orario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orarioRepository")
public interface OrarioReposiotry extends JpaRepository<Orario,Long> {

    @Query(value = "select distinct * from orario where ora not in (select  ora from prenotazioni,orario where orario.ora = prenotazioni.orario and sede_id=?1 and data =?2)",nativeQuery = true)
    List<Orario> getOrario(String sede_id,String data);
}
