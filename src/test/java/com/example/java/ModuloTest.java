package com.example.java;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.example.java.model.Modulo;
import com.example.java.repository.ModuloRepository;
import com.example.java.repository.PrenotazioniRepository;
import com.example.java.repository.UserRepository;
import com.example.java.service.*;
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

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class ModuloTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @InjectMocks
    private UserService userService = new UserServiceImpl();
    @InjectMocks
    private ModuloService moduloService = new ModuloServiceImpl();

    @Mock
    private ModuloRepository moduloRepository;

    private final static int modulo_id = 100;
    private final static int user_id = 39;
    private final static List<Modulo> listModulo = new ArrayList<>();
    private final static Modulo modulo = new Modulo();
    @Test
    public void testFindByUserId() throws Exception{
        when(moduloRepository.findByUserId(user_id)).thenReturn(listModulo);
        assertEquals(listModulo,moduloService.findbyid(user_id));
        verify(moduloRepository,times(1)).findByUserId(user_id);
    }

    @Test
    public void testSaveModulo() throws Exception{
        Modulo modulo1 = new Modulo();
        LocalDate localDate = LocalDate.now();
        int giorno = localDate.getDayOfMonth();
        Month mese = localDate.getMonth();
        int anno = localDate.getYear();
        String data = giorno+" "+mese+" "+anno;
        modulo1.setData(data);
        modulo1.setStatus("INVIATO");
        modulo1.setUser_id(user_id);
        when(moduloRepository.save(modulo1)).thenReturn(modulo1);

    }

    @Test
    public void testPutModulePrameter() throws Exception{
        when(moduloRepository.putModuleParameter(user_id)).thenReturn(1);
        assertEquals(1,0,moduloService.putModuleParameter(user_id));
        verify(moduloRepository,times(1)).putModuleParameter(user_id);
    }


    @Test
    public void testModuloExist() throws Exception{
        when(moduloRepository.ModuleExist(user_id)).thenReturn(1);
        assertEquals(true,moduloService.moduloEsiste(user_id));
        verify(moduloRepository,times(1)).ModuleExist(user_id);
    }

    @Test
    public void findModuloById() throws Exception{
        when(moduloRepository.findModuloById(modulo_id)).thenReturn(modulo);
        verify(moduloRepository,times(0)).findModuloById(modulo_id);
    }


    @Test
    public void deleteModulo() throws Exception{
        moduloService.deleteById(modulo_id);
        verify(moduloRepository,times(1)).deleteModuloById(modulo_id);
    }



}

