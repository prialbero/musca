package com.example.gotproject;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Queue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


@RunWith(MockitoJUnitRunner.class)
public class SalaTests {
    @Mock
    private Puerta puerta;
    @Mock
    private Personajes personajes;

    @Mock
    private Queue<Personajes> personajesEnSala;
    @Mock
    private Queue<Llave> llavesEnSala;
    @InjectMocks
    private Sala sala;


    @Test
    public void testParaProcesarTodaLaSalaCuandoEsteLlenaDePersonajes() {

        Mockito.doReturn(7).when(this.personajesEnSala).size();
        Mockito.doReturn(this.personajes).when(this.personajesEnSala).peek();
        Mockito.doReturn(this.personajes).when(this.personajesEnSala).poll();
        Mockito.doReturn(1).when(this.personajes).getTurno();
        Mockito.doNothing().when(this.personajes).ejecutarAcciones(any(Sala.class), anyInt());

        sala.procesarTurno(1, 30);

        Mockito.verify(this.personajesEnSala).size();
        Mockito.verify(this.personajesEnSala,Mockito.times(7)).peek();
        Mockito.verify(this.personajesEnSala,Mockito.times(7)).poll();
        Mockito.verify(this.personajes,Mockito.times(7)).getTurno();
        Mockito.verify(this.personajes,Mockito.times(7)).ejecutarAcciones(any(Sala.class), anyInt());
    }
}
