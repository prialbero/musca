package com.example.gotproject;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Sala {
    private int idSala;
    private Puerta puerta;
    private List<Llave> llavesEnSala;

    public Sala (){
        this.idSala=0;
        this.llavesEnSala = new ArrayList<>();
    }
    public Sala(int id){
        this.idSala = id;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public Puerta getPuerta() {
        return puerta;
    }

    public void setPuerta(Puerta puerta) {
        this.puerta = puerta;
    }

    public List<Llave> getLlavesEnSala() {
        return llavesEnSala;
    }

    public void setLlavesEnSala(List<Llave> llavesEnSala) {
        this.llavesEnSala = llavesEnSala;
    }

    //devolver la primera llave de la sala
    /*public Llave primeraLlave(){
        return Llave;
    }*/
    //eliminar la primera llave de la sala
    public void eliminarLlaveSala(){

    }
}
