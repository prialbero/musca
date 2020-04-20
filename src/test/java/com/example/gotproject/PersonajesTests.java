package com.example.gotproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Stack;

@RunWith(MockitoJUnitRunner.class)
public class PersonajesTests {
    @Mock
    private Stack<Llave> llaves;

    @Mock
    private Sala sala;
    @Mock
    private Puerta puerta;
    @Mock
    private Llave llave;

    @InjectMocks
    private Stark stark;
    private Lannister lannister;

    @Test
    public void testCambiarEstadoPuerta() {

        Mockito.doReturn(this.puerta).when(this.sala).getPuerta();
        Mockito.doReturn(3).when(this.llaves).size();
        Mockito.doReturn(this.llave).when(this.llaves).pop();
        Mockito.doNothing().when(this.puerta).probarLlave(this.llave);

        stark.cambiarEstadoPuerta(this.sala);

        Mockito.verify(this.sala).getPuerta();
        Mockito.verify(this.llaves).size();
        Mockito.verify(this.llaves,Mockito.times(3)).pop();
        Mockito.verify(this.puerta,Mockito.times(3)).probarLlave(this.llave);
    }


}
