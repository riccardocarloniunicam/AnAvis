package com.example.java.repository;

import com.example.java.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("newsRepository")
public interface NewsRepository extends JpaRepository<News,Long> {

    @Query(value = "SELECT * FROM news order by id desc",nativeQuery = true)
    List<News> listaDesc();
}
