package com.example.gotproject;

import javax.swing.*;
import java.util.*;

public class Mapa {
    private Sala matrizSala[][];
    private Sala sala;
    private Puerta puerta;
    private Queue<Llave> cincoLlaves;
    private Queue<Llave> llavesEnSala;
    private Llave llave;
    private Queue<Personajes> personajesEnSala;
    private int totalSalas;
    private int x;
    private int y;
    private int cont;
    private int salaSalida;

    public Mapa(){
    }

    //Creación de las diferentes salas
    //el mapa tiene una matriz de salas
    public Mapa(int salaTrono, int dimX, int dimY, int alturaArbol){
        this.personajesEnSala=new LinkedList<>();
        this.cincoLlaves=new LinkedList<>();
        this.llavesEnSala=new LinkedList<>();
        this.llave=new Llave();
        this.sala=new Sala();
        this.cont=0;
        this.x = dimX;
        this.y = dimY;
        this.salaSalida = salaTrono;
        this.totalSalas = dimX * dimY;
        this.matrizSala = new Sala[dimX][dimY];
        for(int fila=0; fila<dimX;fila++){
            for(int col=0; col<dimY;col++){
               Sala sala = new Sala(cont);
                matrizSala[fila][col] = sala;
                cont++;
            }
        }
    }

    public void generarLlaves(){
        for(int i=0;i<30;i++){
            llavesEnSala.add(new Llave(i));
            if(i % 2 != 0){
                llavesEnSala.add(new Llave(i));
            }
        }
    }

    //devolver la primera llave
    public Llave primeraLlave(){
        return llavesEnSala.peek();
    }
    //eliminar la primera llave
    public void eliminarLlave(){
        llavesEnSala.poll();
    }

    //El mapa hace la generación de las llaves y las distribuye de 5 en 5 en las salas asignadas al principio
    public void distribuirLlaves(int[] idSalasLlaves){
        /**generación de las 30 llaves cuyos identificadores irán de 0 a 29
         *las llaves con id impar serán duplicadas: en total serán 45 llaves
         * conjunto de llaves ordenado por identificador
         */
        this.generarLlaves();
        for(int id=0;id<idSalasLlaves.length;id++) {
            for (int fila = 0; fila < x; fila++) {
                for (int col = 0; col < y; col++) {
                    if (matrizSala[fila][col].getIdSala() == idSalasLlaves[id]){
                        for(int n=0;n<5;n++){
                            llave = this.primeraLlave();
                            cincoLlaves.add(llave);
                            this.eliminarLlave();
                        }
                        matrizSala[fila][col].setLlavesEnSala(cincoLlaves);
                    }
                }
            }
        }
    }
    Vector buscarSala(int id){
        Vector v = new Vector();
        for(int fila=0; fila<x;fila++) {
            for (int col = 0; col < y; col++) {
                if (matrizSala[fila][col].getIdSala() == id) {
                    v.add(fila);
                    v.add(col);
                    v.add(id);
                }
            }
        }
        return v;
    }

    public Sala obtenerSala(int f, int c){
        for (int fila = 0; fila < x; fila++) {
            for (int col = 0; col < y; col++) {
                if(matrizSala[fila][col] == matrizSala[f][c])
                    sala.setIdSala(matrizSala[fila][col].getIdSala());
            }
        }
        return sala;
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

    //insertar el personaje en el mapa
    public void  insertarPersonaje(Personajes personaje){
        for(int fila=0; fila<x;fila++){
            for(int col=0; col<y;col++) {
                if (matrizSala[fila][col].getIdSala() == personaje.getSalaInicio()) {
                    personajesEnSala.add(personaje);
                    matrizSala[fila][col].setPersonajesEnSala(personajesEnSala);
                    System.out.println("sala "+matrizSala[fila][col].getIdSala()+" personajes "+personajesEnSala);
                }
            }
        }
    }

    // El método procesar se ejecutará turno a turno, recorriendo el mapa desde la sala
    // de entrada hasta la de salida y los personajes almacenados en cada sala ejecutarán
    // sus acciones según orden de llegada
    public void procesar(int maxTurnos){
        puerta=new Puerta();
        int idSala=0;
        int t=0;
        do {
            /*System.out.println("Turno: "+t);
            System.out.println("Mapa: "+salaSalida);
            System.out.println("Puerta: "+puerta.estaAbierta());*/
            for (int fila = 0; fila < x; fila++) {
                for (int col = 0; col < y; col++) {
                    idSala = matrizSala[fila][col].getIdSala();
                    // System.out.println("personaje en sala "+idSala+" "+matrizSala[fila][col].getPersonajes());
                }
            }
            t++;
        }while (t<maxTurnos || puerta.estaAbierta()==true);
    }

}
