package com.example.gotproject;

public class Mapa {
    private Sala matrizSala[][];
    private Sala sala;
    private int totalSalas;
    private int cont;
    //Creación de las diferentes salas
    //el mapa tiene una matriz de salas
    public Mapa(int salaTrono, int dimX, int dimY, int alturaArbol){
        this.totalSalas = dimX * dimY;
        this.cont=0;
        this.matrizSala = new Sala[dimX][dimY];
        for(int fila=0; fila<dimX;fila++){
            for(int col=0; col<dimY;col++){
               Sala sala = new Sala(cont);
                matrizSala[fila][col] = sala;
                cont++; 
                //System.out.print(matrizSala[fila][col].getIdSala()+"\t");
            }
        }
    }



    public void distribuirLlaves(){

    }

    public void insertarPuerta(){

    }

    public void procesar(){

    }

}
