package com.example.gotproject;

public class Mapa {
    private Sala matrizSala[][];
    private Sala sala;
    private Puerta puertaTrono;
    private int totalSalas;
    private int x;
    private int y;
    private int cont;
    private int salaSalida;
    //Creación de las diferentes salas
    //el mapa tiene una matriz de salas
    public Mapa(int salaTrono, int dimX, int dimY, int alturaArbol){
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
                //System.out.print(matrizSala[fila][col].getIdSala()+"\t");
            }
        }
    }



    public void distribuirLlaves(){

    }

    //insertar la puerta en la sala de Trono
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
