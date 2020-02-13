package com.example.java.repository;


import com.example.java.model.Messaggi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messaggiRepository")
public interface MessaggiRepository extends JpaRepository<Messaggi,Long> {

    //retrive messaggi
    @Query(value = "SELECT * FROM messaggi WHERE user_id =?1 order by id desc ",nativeQuery = true)
    List<Messaggi> findMessaggiByUserId(Integer user_id);

}
