package com.example.java.service;

import com.example.java.model.News;

import java.util.List;

public interface NewsService {


    List<News> listall();

    void saveNews(News news,Integer sede_id,String email);
}
