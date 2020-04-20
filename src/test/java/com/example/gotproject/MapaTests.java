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
    private Mapa mapa = new Mapa(30,5,5);


    @Test
    public void testParaDistribuirLlaves(){

        Mockito.doReturn(true).when(this.colaLlaves).add(this.llave);
        Mockito.doReturn(2).when(this.sala).getIdSala();
        Mockito.doReturn(this.colaLlaves).when(this.colaLlaves).peek();
       // Mockito.doNothing().when(this.sala).setLlavesEnSala();
        Mockito.doReturn(this.colaLlaves).when(this.colaLlaves).poll();
        int[] idLlaves ={1,2,3,4,5};
        mapa.distribuirLlaves(idLlaves);

        Mockito.verify(this.colaLlaves).add(this.llave);
        Mockito.verify(this.sala).getIdSala();
        Mockito.verify(this.colaLlaves).peek();
       // Mockito.verify(this.sala).setLlavesEnSala(any(Llave.class));
        Mockito.verify(this.colaLlaves).poll();
    }

}
