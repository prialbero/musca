package com.example.gotproject;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class Mapa {
    private static Sala matrizSala[][];
    private static int x;
    private static int y;
    private static int cont;
    private static int salaSalida;
    private static Mapa mapa;
    final static Logger logger = Logger.getLogger(Mapa.class);
    private Mapa(){
    }

    //Creación de las diferentes salas
    //el mapa tiene una matriz de salas
    public synchronized static Mapa crearMapa(int salaTrono, int dimX, int dimY){
        if(mapa==null){
            mapa = new Mapa();
        }
        cont=0;
        x = dimX;
        y = dimY;
        salaSalida = salaTrono;
        matrizSala = new Sala[dimX][dimY];
        for(int fila=0; fila<dimX;fila++){
            for(int col=0; col<dimY;col++){
               Sala sala = new Sala(cont);
                matrizSala[fila][col] = sala;
                cont++;
            }
        }
        return mapa;
    }

    //El mapa hace la generación de las llaves y las distribuye de 5 en 5 en las salas asignadas al principio
    public static void distribuirLlaves(int[] idSalasLlaves){
        /**generación de las 30 llaves cuyos identificadores irán de 0 a 29
         *las llaves con id impar serán duplicadas: en total serán 45 llaves
         * conjunto de llaves ordenado por identificador
         */
        Queue<Llave> colaLlaves = new LinkedList<>();
        for(int i=0;i<30;i++){
            colaLlaves.add(new Llave(i));
            if(i % 2 != 0){
                colaLlaves.add(new Llave(i));
            }
        }

        for(int id=0;id<idSalasLlaves.length;id++) {
            for (int fila = 0; fila < x; fila++) {
                for (int col = 0; col < y; col++) {
                    if (matrizSala[fila][col].getIdSala() == idSalasLlaves[id]){
                        for(int n=0;n<5;n++){
                            matrizSala[fila][col].setLlavesEnSala(colaLlaves.peek());
                            colaLlaves.poll();
                        }
                    }
                }
            }
        }
    }

    //insertar la puerta en la sala de Trono asignada al principio de la simulación
    public static void insertarPuerta(Puerta puerta, int alturaArbol){
        for(int fila=0; fila<x;fila++){
            for(int col=0; col<y;col++) {
                if (matrizSala[fila][col].getIdSala() == salaSalida) {
                    matrizSala[fila][col].setPuerta(puerta);
                    matrizSala[fila][col].getPuerta().setAlturaArbol(alturaArbol);
                }
            }
        }
    }

    //insertar el personaje en la sala
    public static void  insertarPersonaje(Personajes personaje){
        for(int fila=0; fila<x;fila++){
            for(int col=0; col<y;col++) {
                if (matrizSala[fila][col].getIdSala() == personaje.getSalaActual()) {
                    matrizSala[fila][col].setPersonajesEnSala(personaje);
                }
            }
        }
    }

    public int CalcularCoord (int id,String movi){
    int i=0, j=0,corx=0,cory=0;
    boolean enc = false;
    for ( i= 0; i<x && !enc; i++) {
      for ( j= 0;j<y && !enc; j++) {
        if (matrizSala[i][j].getIdSala() == id){
          corx=i;
          cory=j;
          enc = true;
        }
      }
    }

    if(movi=="S"){
      if(corx<x-1){
        corx++;
        return (matrizSala[corx][cory].getIdSala());
      }
    }
    else if(movi=="N"){
      if(corx>0){
        corx--;
        return (matrizSala[corx][cory].getIdSala());
      }
    }
    else if(movi=="E"){
      if(cory<y-1){
        cory++;
        return (matrizSala[corx][cory].getIdSala());
      }
    }
    else if(movi=="O"){
      if(cory>0){
        cory--;
        return (matrizSala[corx][cory].getIdSala());
      }
    }
    return id;
  }



    /** El método procesar se ejecutará turno a turno, recorriendo el mapa desde la sala
    * de entrada hasta la de salida y los personajes almacenados en cada sala ejecutarán
    * sus acciones según orden de llegada
    *
     * La simulación terminará cuando: 1- algun personaje abra la puerta o 2- se alcance el máximo de turnos
     *
     */

    public static void procesar(int maxTurnos){
        int t=1;
        boolean puertaAbierta=false;
        do {
            logger.info("(turno:"+t+")");
            logger.info("(mapa:"+salaSalida+")");
            for (int fila = 0; fila < x; fila++) {
                for (int col = 0; col < y; col++) {
                    matrizSala[fila][col].procesarTurno(mapa,t,salaSalida);
                    //if(matrizSala[fila][col].getIdSala()==salaSalida && matrizSala[fila][col].getPuerta().estaAbierta()==true)
                      //  puertaAbierta=true;
                }
            }
            t++;
        }while (t<=maxTurnos || puertaAbierta==true);
    }

}
