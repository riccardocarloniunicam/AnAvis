package com.example.java.repository;

import com.example.java.model.Modulo;
import com.example.java.model.Prenotazioni;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("moduloRepository")
public interface ModuloRepository  extends JpaRepository<Modulo,Long> {

    @Query(value = "SELECT * FROM modulo WHERE user_id =?1",nativeQuery = true)
    List<Modulo> findByUserId(Integer user_id);

    @Modifying
    @Query("update User u set u.modulo = 1 where u.id = ?1")
    Integer putModuleParameter(Integer id);


    @Query(value = "SELECT modulo FROM user WHERE id = ?1",nativeQuery = true)
    Integer ModuleExist(Integer id);



    @Query(value = "select * from modulo where user_id =?1",nativeQuery = true)
    Modulo findModuloById(Integer id);

    @Modifying
    @Query("delete from Modulo u where u.user_id =?1")
    void deleteModuloById(Integer id);



}
