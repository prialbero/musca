package com.example.gotproject.controller;

import com.example.gotproject.Mapa;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapaController {
    final static Logger logger = Logger.getLogger(Mapa.class);

    /** El método procesar se ejecutará turno a turno, recorriendo el mapa desde la sala
     * de entrada hasta la de salida y los personajes almacenados en cada sala ejecutarán
     * sus acciones según orden de llegada
     *
     * La simulación terminará cuando: 1- algun personaje abra la puerta o 2- se alcance el máximo de turnos
     *
     */
    @PostMapping("/procesar")
    public void procesar(@RequestParam("turno") int t) {

        boolean puertaAbierta=false;
        logger.info("(turno:"+t+")");
        logger.info("(mapa:"+Mapa.getInstance().getSalaTrono()+")");
        for (int fila = 0; fila < Mapa.getInstance().getDimX(); fila++) {
            for (int col = 0; col < Mapa.getInstance().getDimY(); col++) {
                Mapa.getInstance().getMatrizSala()[fila][col].procesarTurno(t,Mapa.getInstance().getSalaTrono());
                //if(matrizSala[fila][col].getIdSala()==salaSalida && matrizSala[fila][col].getPuerta().estaAbierta()==true)
                //  puertaAbierta=true;
            }
        }
    }
}

