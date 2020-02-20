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

    private final static Prenotazioni prenotazioni = new Prenotazioni();
    private final static List<Prenotazioni> listaPrenotazioni  = new ArrayList();
    private final static String nome = "riccardo";
    private final static String orario = "9:00";
    private final static String data = "20/10/2019";
    private final static int sede_id = 1;
    private final static int user_id = 39;
    private final static String sede_email = "avis_ancona@anavis.it";
    private final static int prenotazione_id = 356;



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
        when(prenotazioniRepository.findByNome(nome)).thenReturn(listaPrenotazioni);
        assertEquals(listaPrenotazioni,prenotazioniService.findByNome(nome));
        verify(prenotazioniRepository,times(1)).findByNome(nome);
    }



    @Test
    public void testfindPrenotazioneById() throws Exception{

        when(prenotazioniRepository.findPrenotazioneById(prenotazione_id)).thenReturn(prenotazioni);
        assertEquals(prenotazioni,prenotazioniService.getPrenotazionebyID(prenotazione_id));
        verify(prenotazioniRepository,times(1)).findPrenotazioneById(prenotazione_id);
    }


    @Test
    public void testprenotazioneGiaEffettuata() throws Exception{
        when(prenotazioniRepository.prenotazioneGiaEffettuata(user_id)).thenReturn(1 | 0);
        assertEquals(true,prenotazioniService.prenotazioneEffettuata(user_id));
        verify(prenotazioniRepository,times(1)).prenotazioneGiaEffettuata(user_id);
    }

    @Test
    public void testdeletePrenotazioni() throws Exception{
        prenotazioniService.delete(prenotazione_id);
        verify(prenotazioniRepository,times(1)).deletePrenotazione(prenotazione_id);
    }



    @Test
    public void testretriveAppuntamentiPerSede() throws  Exception{
        when(prenotazioniRepository.retriveAppuntamentiPerSede(sede_email)).thenReturn(listaPrenotazioni);
        assertEquals(listaPrenotazioni,prenotazioniService.appuntamentiInSede(sede_email));
        verify(prenotazioniRepository,times(1)).retriveAppuntamentiPerSede(sede_email);
    }

    @Test
    public void testretriveAppuntamentiPerSedeEseguiti() throws Exception{

        when(prenotazioniRepository.retriveAppuntamentiPerSedeEseguiti(sede_email)).thenReturn(listaPrenotazioni);
        assertEquals(listaPrenotazioni,prenotazioniService.appuntamentiInSedeEseguiti(sede_email));
        verify(prenotazioniRepository,times(1)).retriveAppuntamentiPerSedeEseguiti(sede_email);
    }


    @Test
    public void testCountOf() throws Exception{
        //
        when(prenotazioniRepository.retrivePrenotazioniCount(sede_id)).thenReturn(1);
        assertEquals(1,0,prenotazioniService.getCountPrenotazioni(sede_id));
        verify(prenotazioniRepository,times(1)).retrivePrenotazioniCount(sede_id);
        //
        when(prenotazioniRepository.retrivePrenotazioniCountEseguite(sede_id)).thenReturn(1);
        assertEquals(1,0,prenotazioniService.retriveEseguite(sede_id));
        verify(prenotazioniRepository,times(1)).retrivePrenotazioniCountEseguite(sede_id);
        //
        when(prenotazioniRepository.analisiDaCaricare(sede_id)).thenReturn(1);
        assertEquals(1,0,prenotazioniService.analisiDaCaricare(sede_id));
        verify(prenotazioniRepository,times(1)).analisiDaCaricare(sede_id);
    }


    @Test
    public void testupdateStato() throws Exception{
        when(prenotazioniRepository.updateStato(prenotazione_id)).thenReturn(1);
        assertEquals(1,0,prenotazioniService.updateState(prenotazione_id));
        verify(prenotazioniRepository,times(1)).updateStato(prenotazione_id);
    }

    @Test
    public void testupdateParametroAnalisi() throws Exception{
        when(prenotazioniRepository.updateParametroAnalisi(prenotazione_id)).thenReturn(1);
        assertEquals(1,0,prenotazioniService.updateAnalisiParamentro(prenotazione_id));
        verify(prenotazioniRepository,times(1)).updateParametroAnalisi(prenotazione_id);
    }


    @Test
    public void testfindNameLike() throws Exception{
        when(prenotazioniRepository.findByNameLike(nome,sede_id)).thenReturn(listaPrenotazioni);
        assertEquals(listaPrenotazioni,prenotazioniService.findLike(nome,sede_id));
        verify(prenotazioniRepository,times(1)).findByNameLike(nome,sede_id);
    }

}
