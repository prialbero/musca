package com.example.gotproject;

import java.util.ArrayList;
import java.util.List;

public class Puerta {
    //lista de configuración de la puerta
    private List<Llave> conf;
    //arbol que guarda la combinación de la puerta
    private Arbol<Llave> listaLlaves;
    //arbol que guarda las llaves usadas
    private Arbol<Llave> llavesProbadas;

    private int alturaArbol;
    //tipo enumerado que define el estado de la puerta
    private enum estado {NoConfig, Abierta, Cerrada, Config};

    private estado esta;

    public int getAlturaArbol() {
        return alturaArbol;
    }

    public void setAlturaArbol(int alturaArbol) {
        this.alturaArbol = alturaArbol;
    }

    /**
     * Entero que marca el limite superior de la profundidad que se necesita para abrir la puerta
     */
    private int profundidad;

    public Puerta() {
        // cambiar los new array y new arbol aquí :D
        this.conf = new ArrayList<>();
        this.listaLlaves = new Arbol<>();
        this.llavesProbadas = new Arbol<>();
        this.esta = estado.NoConfig;
    }

    //configuración de la puerta con la combinación de llaves
    public void configurarPuerta(Llave[] c) {
        for (int i = 0; i < c.length; i++) {
            conf.add(c[i]);
        }
        //this.esta = estado.Config;
        cerrarPuerta();

    }

    //abrir puerta
    public void probarLlave(Llave k) {
        //System.out.println("estado "+esta);
        if (this.esta == estado.Cerrada) {
            //la puerta comprueba si la llave se ha probado
            if (llavesProbadas.pertenece(k)) {
                System.out.println("Ya se ha probado esta llave");
            }
            //si no se ha probado y la llave forma parte de la combinación, se elimina de la misma
            if (listaLlaves.pertenece(k)) {
                llavesProbadas.insertar(k);
                System.out.println("profundidad antes de borrar "+listaLlaves.profundidad());
                listaLlaves.borrar(k);
                System.out.println("profundidad despues de borrar "+listaLlaves.profundidad());
            }
            /**se abre la puerta si la profundidad de la combinacion es menor que el valor predeterminado
             * y si el numero de llaves internas es mayor o igual al numero de llaves finales
             */
            if(listaLlaves.profundidad() < getAlturaArbol() && conf.size() >= (listaLlaves.NodoPadre()+listaLlaves.NodoHijo())){
                System.out.println("condicion de apertura");
                System.out.println("profundidad "+listaLlaves.profundidad());
                abrirPuerta();
            }
        } else System.out.println("Puerta abierta");
    }

    public boolean estaAbierta() {
        return this.esta == estado.Abierta;
    }

    public void abrirPuerta(){
        this.esta = estado.Abierta;
    }

    //vaciar arboles
    public void vaciar() {
        //borrar dato del árbol
        while(!listaLlaves.vacio())
            listaLlaves.borrar(listaLlaves.getRaiz());

        while(!llavesProbadas.vacio())
            llavesProbadas.borrar(llavesProbadas.getRaiz());
    }

    //cerrar la puerta
    public void cerrarPuerta() {
        //se vacía la memoria  de llaves probadas y la puerta vuelve a su estado inicial
        vaciar();
        //recorrer array de configuración e insertar en árbol de combinación

        for(int i=0;i<conf.size();i++) {
            listaLlaves.insertar(conf.get(i));
        }
        this.esta = estado.Cerrada;
    }

    public void mostrarCerradura() {

    }
}
