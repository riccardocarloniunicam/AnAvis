package com.example.java;


import com.example.java.model.Prenotazioni;

import com.example.java.repository.PrenotazioniRepository;
import com.example.java.service.PrenotazioniService;
import com.example.java.service.PrenotazioniServiceImpl;
import com.example.java.service.UserService;
import com.example.java.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.java.model.User;
import com.example.java.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebMvcTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @InjectMocks
    private UserService userService = new UserServiceImpl();
    @InjectMocks
    private PrenotazioniService prenotazioniService = new PrenotazioniServiceImpl();

    @Mock
    private UserRepository userRepository;
    @Mock
    private PrenotazioniRepository prenotazioniRepository;

private static final int id = 39;


    @Test
    public void testUserFindAll(){
        User user = new User();
        when(userRepository.save(any(User.class))).thenAnswer(i->i.getArguments()[0]);
    }

    @Test
    public void testSaveUser() throws  Exception{
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
    }

    @Test
    public void testFindAllToapprove() throws  Exception{
        List<User> user = userRepository.findAllToApprove();
        when(userRepository.findAllToApprove()).thenReturn(user);
    }

    @Test
    public void testFindByEmail() throws  Exception{
        User user = new User();
        String email = "admin@admin.it";
        when(userRepository.findByEmail(email)).thenReturn(user);
    }

    @Test
    public void testFindTheUser() throws  Exception{
        User user = new User();
        when(userRepository.findTheUser(id)).thenReturn(user);
        assertEquals(user,userService.findByIdForApprovazione(id));
        verify(userRepository,times(1)).findTheUser(id);
    }

    @Test
    public void testUpgradeDonatore() throws  Exception{
        User user = new User();
        when(userRepository.UpgradeDonatore(id)).thenReturn(1 | 0);
    }

    @Test
    public void testUppareParametroDonatore() throws  Exception{
      when(userRepository.UppaParametroDonatore(id)).thenReturn(1 | 0);
      assertEquals(1,0,userService.UpgradeParamDonatore(id));
        verify(userRepository,times(1)).UppaParametroDonatore(id);
    }

    @Test
    public void testDowngradeParamModulo() throws  Exception{
        when(userRepository.DownGrandeParametroModulo(id)).thenReturn(0);
        assertEquals(0,0,userService.downGradeParamModulo(id));
        verify(userRepository,times(1)).DownGrandeParametroModulo(id);
    }

    @Test
    public void testRetriveUserAppuntamento() throws  Exception{
        List<User> user = new ArrayList<>();
        String email = "admin@admin.it";

        List<User> users = userRepository.retriveUserAppuntamento(email);
        when(userRepository.retriveUserAppuntamento(email)).thenReturn(user);
        assertEquals(user,userService.findUserOfPrenotazione(email));
    }
}