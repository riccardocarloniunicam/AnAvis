package com.example.java.repository;


import com.example.java.model.User;
import com.example.java.model.UtenteSedi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("utentesediRepository")
public interface UtenteSediRepository extends JpaRepository<UtenteSedi,Long> {



    @Query(value = "SELECT * from utente_sedi where email=?1",nativeQuery = true)
    UtenteSedi findByEmail(String email);

}
