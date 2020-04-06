package com.example.gotproject;

public class Stark {
    private String nombre;
    private char marca;
    private int turno;
    private int salaInicio;

    public Stark() {

    }

    public Stark(String nombreS, char marcaS, int turnoS, int salaInicioS) {
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


}