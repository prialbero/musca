package com.example.gotproject;

import javax.swing.*;
import java.util.*;

public class Mapa {
    private Sala matrizSala[][];
    private int x;
    private int y;
    private int cont;
    private int salaSalida;

    public Mapa(){
    }

    //Creación de las diferentes salas
    //el mapa tiene una matriz de salas
    public Mapa(int salaTrono, int dimX, int dimY, int alturaArbol){
        this.cont=0;
        this.x = dimX;
        this.y = dimY;
        this.salaSalida = salaTrono;
        this.matrizSala = new Sala[dimX][dimY];
        for(int fila=0; fila<dimX;fila++){
            for(int col=0; col<dimY;col++){
               Sala sala = new Sala(cont);
                matrizSala[fila][col] = sala;
                cont++;
            }
        }
    }

    //El mapa hace la generación de las llaves y las distribuye de 5 en 5 en las salas asignadas al principio
    public void distribuirLlaves(int[] idSalasLlaves){
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
    public void insertarPuerta(Puerta puerta){
        for(int fila=0; fila<x;fila++){
            for(int col=0; col<y;col++) {
                if (matrizSala[fila][col].getIdSala() == salaSalida) {
                    matrizSala[fila][col].setPuerta(puerta);
                }
            }
        }
    }

    //insertar el personaje en la sala
    public void  insertarPersonaje(Personajes personaje){
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
      if(corx<x){
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
      if(cory<y){
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

    public void procesar(int maxTurnos){
        int t=1;
        do {
            System.out.println("Turno: "+t);
           /* System.out.println("Mapa: "+salaSalida);
            */
            for (int fila = 0; fila < x; fila++) {
                for (int col = 0; col < y; col++) {
                    matrizSala[fila][col].procesarTurno(this);
                }
            }
            t++;
        }while (t<=maxTurnos);
    }

}
