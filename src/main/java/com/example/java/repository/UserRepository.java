package com.example.java.repository;

import com.example.java.model.Prenotazioni;
import com.example.java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    //LISTA UTENTI CHE HANNO INVIATO IL MODULO E SONO IN ATTESA DI APPROVAIZONE
    @Query(value = "select * from user  inner join user_role  on (user.id = user_role.user_id) inner join modulo on (user.id = modulo.user_id) and role_id=2",nativeQuery = true)
    List<User> findAllToApprove();

    @Query(value = "select count(*) from user  inner join user_role  on (user.id = user_role.user_id) inner join modulo on (user.id = modulo.user_id) and role_id=2",nativeQuery = true)
    Integer findNumberToApprove();



    // PRENDI TUTTO USER E MODULO DELLO STESSO UTENTE
    @Query(value = "select * from user inner join modulo on (user.id = modulo.user_id) and user.id = ?1",nativeQuery = true)
    User findTheUser(Integer id);


    //UPGRADE AUTORITA'
    @Modifying
    @Query(value = "UPDATE user_role SET role_id = 3 WHERE user_id =?1",nativeQuery = true)
    Integer UpgradeDonatore(Integer id);

//parameto Donatore A 1 nella tabella user
    @Modifying
    @Query(value = "UPDATE user SET donatore = 1 WHERE id =?1",nativeQuery = true)
    Integer UppaParametroDonatore(Integer id);


    @Modifying
    @Query(value = "UPDATE user SET modulo = 0 WHERE id =?1",nativeQuery = true)
    Integer DownGrandeParametroModulo(Integer id);


    //trova prenotazioni in una sede in base all'email e dati dell'utente
    @Query(value = "select *  from prenotazioni inner join utente_sedi on (prenotazioni.sede_id = utente_sedi.sede_id) inner join user on (user.id=prenotazioni.user_id) and utente_sedi.email = ?1",nativeQuery = true)
    List<User> retriveUserAppuntamento(String email);




}