package com.example.gotproject;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public abstract class Personajes {

    protected Queue<Enum> direccionesP;
    protected String nombre;
    protected char marca;
    protected int turno;
    protected int salaActual;

    public Personajes(){

    }
    public Personajes(String nombreS, char marcaS, int turnoS, int salaInicioS) {
        this.nombre=nombreS;
        this.marca=marcaS;
        this.turno=turnoS;
        this.salaActual=salaInicioS;
        direccionesP=new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getMarca() {
        return marca;
    }

    public void setMarca(char marca) {
        this.marca = marca;
    }


    public int getSalaActual() {
        return salaActual;
    }

    public void setSalaActual(int salaActual) {
        this.salaActual = salaActual;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Queue<Enum> getDireccionesP() {
        return direccionesP;
    }

    public void setDireccionesP(Queue<Enum> direccionesP) {
        this.direccionesP = direccionesP;
    }

    public void asignarRuta(Enum[] direcciones){
        int fila=0;
        int col=0;
        for (int i = 0; i < direcciones.length; i++) {
           direccionesP.add(direcciones[i]);
        }
        this.setDireccionesP(direccionesP);
    }

    public void mover(Mapa map){
        System.out.println("donde estoy "+direccionesP.peek());
        salaActual=map.CalcularCoord(salaActual, direccionesP.peek().toString());
        direccionesP.poll();
        System.out.println("personaje: "+this+" nueva sala"+salaActual);
        this.turno=+1;
        //Insertar el personaje en la sala actual
        //map.insertarPersonaje(this);

    }


}
