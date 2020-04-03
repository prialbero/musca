package com.example.gotproject;

import javax.swing.*;
import java.util.*;

public class Mapa {
    private Sala matrizSala[][];
    private List<Sala> salasConLlaves;
    private Sala sala;
    private Sala salaLlave;
    private Llave llave;
    private Puerta puertaTrono;
    private int totalSalas;
    private int x;
    private int y;
    private int cont;
    private List<Llave> totalLlavesMapa;
    private int salaSalida;

    //Creación de las diferentes salas
    //el mapa tiene una matriz de salas
    public Mapa(int salaTrono, int dimX, int dimY, int alturaArbol){
        this.salasConLlaves=new ArrayList<>();
        this.totalLlavesMapa=new ArrayList<>();
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


    //El mapa hace la generación de las llaves y las distribuye de 5 en 5 en las salas asignadas al principio
    public void distribuirLlaves(int[] idSalasLlaves){
        /**generación de las 30 llaves cuyos identificadores irán de 0 a 29
         *las llaves con id impar serán duplicadas: en total serán 45 llaves
         * conjunto de llaves ordenado por identificador
         */
        int[] provisional = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
        int id=0;
        for(int i=0;i<45;i++) {
            if(provisional[id]%2==0){
                llave = new Llave(provisional[id]);
                totalLlavesMapa.add(llave);
                id++;
            }
            else{
                llave = new Llave(provisional[id]);
                totalLlavesMapa.add(llave);
                i++;
                llave = new Llave(provisional[id]);
                totalLlavesMapa.add(llave);
                id++;
            }
        }

            //recorrer las 9 salas asignadas en la simulación y pasarlas a un array de objeto sala
            for(int i=0;i<idSalasLlaves.length;i++) {
                salaLlave = new Sala(idSalasLlaves[i]);
                salasConLlaves.add(salaLlave);
                }
            int idl=0;
            int ids=0;
            /*while(!totalLlavesMapa.isEmpty()){
                //salasConLlaves.set(ids,totalLlavesMapa.get(idl));
                if(idl%5==0) ids++;
                idl++;
                //System.out.println("sala con llaves "+salasConLlaves.get(ids));
            }*/
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
