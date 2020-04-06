package com.example.gotproject;

import java.util.*;

public class Sala {
    private int idSala;
    private Puerta puerta;
    private Queue<Llave> llavesEnSala;
    private Llave llave;

    public Sala (){
        this.idSala=0;
        this.llavesEnSala = new LinkedList<>();
        this.llave=new Llave();
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

    public Llave getLlave() {
        return llave;
    }

    public void setLlave(Llave llave) {
        this.llave = llave;
    }

    public Queue<Llave> getLlavesEnSala() {
        return llavesEnSala;
    }

    public void setLlavesEnSala(Queue<Llave> llavesEnSala) {
        this.llavesEnSala = llavesEnSala;
    }



    //devolver la primera llave
    public Llave primeraLlave(){
        return llavesEnSala.peek();
    }
    //eliminar la primera llave
    public void eliminarLlave(){
        llavesEnSala.poll();
    }
}
