package com.example.gotproject;

import java.util.*;

public class Sala {
    private int idSala;
    private Puerta puerta;
    private Queue<Personajes> personajesEnSala;
    private Queue<Llave> llavesEnSala;


    public Sala (){
    }
    public Sala(int id){
        this.idSala = id;
        this.llavesEnSala = new LinkedList<>();
        this.personajesEnSala=new LinkedList<>();
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

    public Queue<Llave> getLlavesEnSala() {
        return llavesEnSala;
    }

    public void setLlavesEnSala(Llave llave) {

        llavesEnSala.add(llave);
    }

    public Queue<Personajes> getPersonajesEnSala() {
        return personajesEnSala;  
    }

    public void setPersonajesEnSala(Personajes personajes) {
        personajesEnSala.add(personajes);
    }

    public void procesarTurno(Mapa map){
        while(!personajesEnSala.isEmpty()) {
            personajesEnSala.poll().mover(map);
            //personajesEnSala.poll();
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
}
