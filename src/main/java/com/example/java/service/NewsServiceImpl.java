package com.example.java.service;


import com.example.java.model.News;
import com.example.java.repository.ModuloRepository;
import com.example.java.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Qualifier("newsRepository")
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> listall() {
        return newsRepository.listaDesc();


    }

    @Override
    public void saveNews(News news,Integer sede_id,String email) {
        LocalDate localDate = LocalDate.now();
        int giorno = localDate.getDayOfMonth();
        Month mese = localDate.getMonth();
        int anno = localDate.getYear();
        String data = giorno+" "+mese+" "+anno;
        news.setData(data);
        news.setEmail(email);
        news.setSede_id(sede_id);
        newsRepository.save(news);
    }
}
