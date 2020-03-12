package com.example.api.services;

import com.example.api.domain.Login;
import com.example.api.domain.Response;
import com.example.api.entity.Users;
import com.example.api.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private UserRepository repo;

    @Mock
    private BCryptPasswordEncoder encoder;

    @InjectMocks
    private LoginImplementation LoginImplementation;
    private Response Response;
    @Mock
    private Login Login ;
    private Users users ;

    @Before
    public void setup(){
        Login  = new Login();
        Response = new Response();
        users =new Users();
        users.setPassword("aaa");
        users.setUsername("aaa");
        Login.setPassword("aaa");
        Login.setUsername("aaa");
    }
    @Test
    public void access() {
        final String user = "aaa";
        Mockito.when(repo.findUser(user)).thenReturn(users);
        when(encoder.matches(Login.getUsername(),Login.getPassword())).thenReturn(true);
        when(repo.save(users)).thenReturn(users);

        Object data = LoginImplementation.access(Login);

        assertNotNull(data);
        Assert.assertEquals(Response, data);
        verify(repo).findUser(user);

    }

}