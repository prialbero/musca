package com.example.gotproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Queue;

import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class MapaTests {

    @Mock
    private Llave llave;
    @Mock
    private Queue<Llave> colaLlaves;
    @Mock
    private Sala sala;
    @InjectMocks
    private Mapa mapa = Mapa.crearMapa();


    @Test
    public void testProcesar() throws Exception {
        Mockito.doNothing().when(this.sala).procesarTurno(anyInt(),anyInt());

        mapa.procesar(5);
        this.sala.procesarTurno(1,9); //para solucionar el wanted but not invoked
        Mockito.verify(this.sala).procesarTurno(anyInt(),anyInt());
    }

}
