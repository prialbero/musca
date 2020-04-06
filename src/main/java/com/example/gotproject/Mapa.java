package com.example.gotproject;

import javax.swing.*;
import java.util.*;

public class Mapa {
    private Sala matrizSala[][];
    private Sala sala;
    private Queue<Llave> cincoLlaves;
    private Queue<Llave> llavesEnSala;
    private Llave llave;
    private int totalSalas;
    private int x;
    private int y;
    private int cont;
    private int salaSalida;

    //Creación de las diferentes salas
    //el mapa tiene una matriz de salas
    public Mapa(int salaTrono, int dimX, int dimY, int alturaArbol){
        this.cincoLlaves=new LinkedList<>();
        this.llavesEnSala=new LinkedList<>();
        this.llave=new Llave();
        this.sala=new Sala();
        this.x = dimX;
        this.y = dimY;
        this.salaSalida = salaTrono;
        this.totalSalas = dimX * dimY;
        this.cont=0;
        this.matrizSala = new Sala[dimX][dimY];
        for(int fila=0; fila<dimX;fila++){
            for(int col=0; col<dimY;col++){
               Sala sala = new Sala(cont);
                matrizSala[fila][col] = sala;
                cont++;
            }
        }

    }
    public Sala obtenerSala(int f, int c){
        for (int fila = 0; fila < x; fila++) {
            for (int col = 0; col < y; col++) {
                if(matrizSala[fila][col] == matrizSala[f][c])
                    sala = matrizSala[fila][col];
            }
        }
        return sala;
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
                       // System.out.println("sala "+matrizSala[fila][col].getIdSala());
                        //System.out.println("llaves: ");
                        for(int n=0;n<5;n++){
                            llave = sala.primeraLlave();
                            cincoLlaves.add(llave);
                            sala.eliminarLlave();
                        }
                        matrizSala[fila][col].setLlavesEnSala(cincoLlaves);
                    }
                }
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
    //insertar la puerta en la sala de Trono asignada al principio de la simulación
    public void insertarPuerta(Puerta puerta){
        for(int fila=0; fila<x;fila++){
            for(int col=0; col<y;col++) {
                if (matrizSala[fila][col].getIdSala() == salaSalida)
                 matrizSala[fila][col].setPuerta(puerta);


            }
        }
    }

    public void procesar(){

    }

}
