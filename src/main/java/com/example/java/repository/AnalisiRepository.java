package com.example.java.repository;


import com.example.java.model.Analisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("analisiRepository")
public interface AnalisiRepository extends JpaRepository<Analisi,Long> {



    @Query(value = "SELECT  * FROM analisi where user_id = ?1",nativeQuery = true)
    List<Analisi> findAanalisiByID(Integer id);
}
