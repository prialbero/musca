package com.example.gotproject;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Queue;

import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
public class SalaTests {
    @Mock
    private Puerta puerta;
    @Mock
    private Queue<Personajes> personajesEnSala;
    @Mock
    private Queue<Llave> llavesEnSala;
    @InjectMocks
    private Sala sala = new Sala();


    @Test
    public void testParaProcesarTodaLaSalaCuandoEsteLlenaDePersonajes() {

        Mockito.doReturn(7).when(this.personajesEnSala).size();
        Mockito.doReturn(1).when(this.personajesEnSala).peek().getTurno();
        Mockito.doReturn(1).when(this.personajesEnSala).peek().ejecutarAcciones(any(Sala.class), 1);
        sala.procesarTurno(1, 30);
    }
}
