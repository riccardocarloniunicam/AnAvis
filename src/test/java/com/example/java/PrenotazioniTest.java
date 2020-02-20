package com.example.java;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.example.java.model.Prenotazioni;
import com.example.java.repository.PrenotazioniRepository;
import com.example.java.repository.UserRepository;
import com.example.java.service.PrenotazioniService;
import com.example.java.service.PrenotazioniServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class PrenotazioniTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @InjectMocks
    private PrenotazioniService prenotazioniService = new PrenotazioniServiceImpl();

    @Mock
    private UserRepository userRepository;
    @Mock
    private PrenotazioniRepository prenotazioniRepository;
    private final static String nome = "riccardo";
    private final static String orario = "9:00";
    private final static String data = "20/10/2019";
    private final static int sede_id = 1;


    @Test
    public void testFindByOrarioEdata() throws  Exception{
        List<Prenotazioni>  prenotazionis = new ArrayList<>();
        when(prenotazioniRepository.findByOrarioAndData(orario,data,sede_id)).thenReturn(prenotazionis);
        assertEquals(prenotazionis,prenotazioniService.findByorarioedata(orario,data,sede_id));
        verify(prenotazioniRepository,times(1)).findByOrarioAndData(orario,data,sede_id);
    }

    @Test
    public void testprenotazioneExist() throws  Exception{
        when(prenotazioniRepository.prenotazioneExist(data,orario,sede_id)).thenReturn(0 | 1);
        assertEquals(true,prenotazioniService.checkPrenotazione(data,orario,sede_id));
        verify(prenotazioniRepository,times(1)).prenotazioneExist(data,orario,sede_id);
    }


    @Test
    public void testFindBynome() throws  Exception{
        List<Prenotazioni> prenotazionis = new ArrayList<>();
        when(prenotazioniRepository.findByNome(nome)).thenReturn(prenotazionis);
        assertEquals(prenotazionis,prenotazioniService.findByNome(nome));
        verify(prenotazioniRepository,times(1)).findByNome(nome);
    }
}
