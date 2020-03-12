package com.example.api.controller;

import com.example.api.domain.Login;
import com.example.api.domain.RegisterUser;
import com.example.api.domain.Response;
import com.example.api.services.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @InjectMocks
    private RegistryController registrycontroller;

    @Mock
    private ProfileImplementation ProfileImplementation;
    @Mock
    private LoginService LoginService;
    @Mock
    private RegistryCreationService RegistryCreationService;
    @Mock
    private Object response;
    private Login Login;
    private RegisterUser registerUser;


    @Before
    public void setup(){
        registerUser = new RegisterUser();
         Login = new Login();
    }

    @Test
    public void access(){
        Mockito.when(LoginService.access((Login))).thenReturn(response);
        ResponseEntity data = registrycontroller.access(Login);
        assertNotNull(data);
        Assert.assertEquals(response, data.getBody());
        verify(LoginService).access(Login);
        verifyNoMoreInteractions(LoginService);
    }

    @Test
    public void getProfile() {
        Mockito.when(ProfileImplementation.getProfile(Mockito.anyString())).thenReturn(response);
        ResponseEntity data = registrycontroller.profile("a");
        assertNotNull(data);
        Assert.assertEquals(response, data.getBody());
        verify(ProfileImplementation).getProfile(Mockito.anyString());
        verifyNoMoreInteractions(ProfileImplementation);
    }


    @Test
    public void create() {
        Mockito.when(RegistryCreationService.create(registerUser)).thenReturn(response);
        ResponseEntity data = registrycontroller.register(registerUser);
        assertNotNull(data);
        Assert.assertEquals(response, data.getBody());
        verify(RegistryCreationService).create(registerUser);
        verifyNoMoreInteractions(RegistryCreationService);
    }




}