package com.example.gotproject;

import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.util.*;

public abstract class Personajes {

    protected Queue<Enum> direccionesP;
    protected String nombre;
    protected char marca;
    protected int turno;
    protected int salaActual;
    protected Stack<Llave> llaves;
    final static Logger logger = Logger.getLogger(Personajes.class);

    public Personajes(){
    }

    public Personajes(String nombreS, char marcaS, int turnoS, int salaInicioS) {
        this.nombre=nombreS;
        this.marca=marcaS;
        this.turno=turnoS;
        this.salaActual=salaInicioS;
        this.direccionesP=new LinkedList<>();
        this.llaves=new Stack<>();
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

    public void ejecutarAcciones(Mapa map, Sala s,int salaTrono){
        inspeccionarSala(s);
        if(salaActual==salaTrono)
            cambiarEstadoPuerta(s);
        logger.info("personaje: " + this + " sala actual" + salaActual + "turno actual" + this.turno);
        mover(map);
    }

    public void cambiarEstadoPuerta(Sala sala){
        //probará la ultima llave que ha recogido en la sala
        if(sala.getPuerta()!=null){
            while(llaves.size()!=0)
                sala.getPuerta().probarLlave(llaves.pop());
        }
    }

    public void inspeccionarSala(Sala sala){
        if(!sala.getLlavesEnSala().isEmpty()){
            llaves.push(sala.primeraLlave());
            logger.info("llaves "+this.nombre+" "+llaves);
            sala.eliminarLlave();
        }
    }

    public void mover(Mapa map){
        if(direccionesP.size()!=0) {
            this.salaActual = map.CalcularCoord(salaActual, direccionesP.peek().toString());
            direccionesP.remove();
            this.turno += 1;
            //Insertar el personaje en la sala actual
            map.insertarPersonaje(this);
        }
    }
}
