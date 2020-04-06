package com.example.gotproject;

import java.lang.reflect.Array;

public abstract class Personajes {
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

    public void asignarRuta(Enum[] direcciones){

    }

}
