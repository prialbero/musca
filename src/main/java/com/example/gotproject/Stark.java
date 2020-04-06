package com.example.gotproject;

import java.lang.reflect.Array;

//Hereda la clase Personajes
public class Stark extends Personajes{

    public Stark() {
        super();
    }

    public Stark(String nombreS, char marcaS, int turnoS, int salaInicioS) {
        super(nombreS,marcaS,turnoS,salaInicioS);
       //System.out.println("clase stark: "+getNombre()+" marca: "+getMarca()+" turno: "+getTurno()+" sala inicio: "+getSalaInicio());
    }

    @Override
    public void asignarRuta(Enum[] direcciones){
       // System.out.println("ruta "+direcciones.length);

    }

}
