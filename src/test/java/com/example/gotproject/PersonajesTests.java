package com.example.gotproject;

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
    private Llave llave=new Llave();

    @InjectMocks
    private Stark stark;

    @Test
    public void testCambiarEstadoPuerta(){

        Mockito.doReturn(this.puerta).when(this.sala).getPuerta();
        Mockito.doReturn(7).when(this.llaves).size();
        Mockito.doReturn(this.llaves).when(this.llaves).pop();
        Mockito.doReturn(this.llaves).when(this.llaves).peek();
        Mockito.doNothing().when(this.puerta).probarLlave(this.llave);

        stark.cambiarEstadoPuerta(this.sala);

        Mockito.verify(this.sala).getPuerta();
        Mockito.verify(this.llaves).size();
        Mockito.verify(this.llaves).pop();
        Mockito.verify(this.llaves.peek());
    }
}
