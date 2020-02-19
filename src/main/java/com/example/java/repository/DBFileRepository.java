package com.example.java.repository;

import com.example.java.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

    @Query(value = "select * from prenotazioni inner join files on (prenotazioni.id = files.pid) and files.utente=?1",nativeQuery = true)
    List<DBFile> reportFile(Integer user_id);

}