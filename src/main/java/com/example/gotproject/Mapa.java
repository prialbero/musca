package com.example.gotproject;

import com.example.gotproject.exception.ExceptionMapa;
import org.apache.log4j.Logger;


import java.util.*;


public class Mapa{
    private static Sala matrizSala[][];
    private static int dimX;
    private static int dimY;
    private static int cont;
    private static int salaTrono;
    private static Mapa mapa = null;

    final static Logger logger = Logger.getLogger(Mapa.class);

    public int getDimX() {
        return dimX;
    }

    public void setDimX(int dimX) {
        Mapa.dimX = dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public void setDimY(int dimY) {
        Mapa.dimY = dimY;
    }

    public int getSalaTrono() {
        return salaTrono;
    }

    public void setSalaTrono(int salaTrono) {
        Mapa.salaTrono = salaTrono;
    }

    /** Patrón singleton
     * la clase Mapa solo se instancia una vez
     * al ser estático se puede acceder desde cualquier punto del proyecto
     * desde las otras clases solo se debe hacer la llamada a Mapa.crearMapa();
     */
    public synchronized static Mapa crearMapa(int dimX, int dimY,int salaTrono) {
        if (mapa == null) {
            mapa = new Mapa(dimX, dimY,salaTrono);
        }
        return mapa;
    }
    public static Mapa crearMapa(){return mapa;}

    /**Creación de las diferentes salas
    * el mapa tiene una matriz de salas
     */
    public Mapa(int x, int y, int salaT){
        this.dimX=x;
        this.dimY=y;
        this.salaTrono=salaT;

        cont=0;
        try {
            if(dimX<0 || dimY<0){
                throw new ExceptionMapa("El array no puede ser negativo");
            }
            matrizSala = new Sala[dimX][dimY];
            for (int fila = 0; fila < dimX; fila++) {
                for (int col = 0; col < dimY; col++) {
                    Sala sala = new Sala(cont);
                    matrizSala[fila][col] = sala;
                    cont++;
                }
            }
        }
        catch (ExceptionMapa e){
            e.printStackTrace();
        }
    }
    public void Mapa(){
    }


    //El mapa hace la generación de las llaves y las distribuye de 5 en 5 en las salas asignadas al principio
    public static void distribuirLlaves(int[] idSalasLlaves){
        /**generación de las 30 llaves cuyos identificadores irán de 0 a 29
         *las llaves con id impar serán duplicadas: en total serán 45 llaves
         * conjunto de llaves ordenado por identificador
         */
        Queue<Llave> colaLlaves = new LinkedList<>();
        int tamIdSalasLlaves=idSalasLlaves.length;
        for(int i=0;i<30;i++){
            colaLlaves.add(new Llave(i));
            if(i % 2 != 0){
                colaLlaves.add(new Llave(i));
            }
        }

        for(int id=0;id<tamIdSalasLlaves;id++) {
            for (int fila = 0; fila < dimX; fila++) {
                for (int col = 0; col < dimY; col++) {
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
        for(int fila=0; fila<dimX;fila++){
            for(int col=0; col<dimY;col++) {
                if (matrizSala[fila][col].getIdSala() == salaTrono) {
                    matrizSala[fila][col].setPuerta(puerta);
                    matrizSala[fila][col].getPuerta().setAlturaArbol(alturaArbol);
                }
            }
        }
    }

    //insertar el personaje en la sala
    public static void  insertarPersonaje(Personajes personaje){
        for(int fila=0; fila<dimX;fila++){
            for(int col=0; col<dimY;col++) {
                if (matrizSala[fila][col].getIdSala() == personaje.getSalaActual()) {
                    matrizSala[fila][col].setPersonajesEnSala(personaje);
                }
            }
        }
    }

    public int CalcularCoord (int id,String movi){
    int i=0, j=0,corx=0,cory=0;
    boolean enc = false;
    for ( i= 0; i<dimX && !enc; i++) {
      for ( j= 0;j<dimY && !enc; j++) {
        if (matrizSala[i][j].getIdSala() == id){
          corx=i;
          cory=j;
          enc = true;
        }
      }
    }

    if(movi=="S"){
      if(corx<dimX-1){
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
      if(cory<dimY-1){
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
    else if(movi=="") {
        return id;
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

    public static void procesar(int maxTurnos) {
        int t=0;
        boolean puertaAbierta=false;
        while (t<=maxTurnos || puertaAbierta==true) {
            logger.info("(turno:"+t+")");
            logger.info("(mapa:"+salaTrono+")");
            for (int fila = 0; fila < dimX; fila++) {
                for (int col = 0; col < dimY; col++) {
                    matrizSala[fila][col].procesarTurno(t,salaTrono);
                    //if(matrizSala[fila][col].getIdSala()==salaSalida && matrizSala[fila][col].getPuerta().estaAbierta()==true)
                      //  puertaAbierta=true;
                }
            }
            t++;
        }
    }

}
