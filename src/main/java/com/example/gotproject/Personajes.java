package com.example.gotproject;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public abstract class Personajes {
    private Mapa mapa;
    private Sala sala;
    private Queue<Enum> direccionesP;
    enum Dir{N,S,E,O }
    protected String nombre;
    protected char marca;
    protected int turno;
    protected int salaInicio;

    public Personajes(){
        nombre="";
        marca='\0';
        turno=0;
        salaInicio=0;

    }
    public Personajes(String nombreS, char marcaS, int turnoS, int salaInicioS) {
        this.nombre=nombreS;
        this.marca=marcaS;
        this.turno=turnoS;
        this.salaInicio=salaInicioS;
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

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getSalaInicio() {
        return salaInicio;
    }

    public void setSalaInicio(int salaInicio) {
        this.salaInicio = salaInicio;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
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
        sala=new Sala();
        //System.out.println(marca);
        for (int i = 0; i < direcciones.length; i++) {
           direccionesP.add(direcciones[i]);
        }
        //System.out.print(direccionesP);
        this.setDireccionesP(direccionesP);
    }

}
