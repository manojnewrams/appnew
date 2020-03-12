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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

    @InjectMocks
    private ProfileImplementation ProfileImplementation;
    private Response Response ;

    @Mock
    private UserRepository repo;

    @Mock
    private Login Login ;
    private Users users ;

    @Before
    public void setup(){
        Response = new Response();
        users =new Users();
        users.setPassword("aaa");
        users.setUsername("aaa");
        Login.setPassword("aaa");
        Login.setUsername("aaa");
    }


    @Test
    public void getProfile() {
        Mockito.when(repo.getOne(Mockito.anyString())).thenReturn(users);
        Object data = ProfileImplementation.getProfile("aa");
        assertNotNull(data);
        Assert.assertEquals(Response, data);
        verify(repo).getOne("aa");
    }

}