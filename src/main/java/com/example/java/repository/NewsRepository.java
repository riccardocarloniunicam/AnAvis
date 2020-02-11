package com.example.java.repository;

import com.example.java.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("newsRepository")
public interface NewsRepository extends JpaRepository<News,Long> {

}
