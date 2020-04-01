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

    //tipo enumerado que define el estado de la puerta
    private enum estado {NoConfig, Abierta, Cerrada, Config}

    ;
    private estado esta;
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
    //una vez configurada debe cerrarse la puerta
    public void configurarPuerta(Llave[] c) {

        for (int i = 0; i < c.length; i++) {
            conf.add(c[i]);
        }
        this.esta = estado.Config;

    }

    //abrir puerta
    public void probarLlave(Llave k) {
        //System.out.println("estado "+esta);
        if (this.esta == estado.Cerrada) {
            //la puerta comprueba si la llave se ha probado
            if (llavesProbadas.pertenece(k)) {
                System.out.println("Ya se ha probado esta llave");
            } //si no se ha probado y la llave forma parte de la combinación, se elimina de la misma
            else
                if (listaLlaves.pertenece(k)) {
                //System.out.println("pertenece a la combinacion "+listaLlaves.pertenece(k));
                llavesProbadas.insertar(k);
                listaLlaves.borrar(k);
            }
            /**se abre la puerta si la profundidad de la combinacion es menor que el valor
             * y si el numero de llaves internas es mayor o igual al numero de llaves finales
             */
        } else System.out.println("Puerta abierta");

    }

    public boolean estaAbierta() {
            return this.esta == estado.Abierta;

    }

    //vaciar arboles
    public void vaciar() {
            //borrar dato del árbol
            listaLlaves.borrar(listaLlaves.getRaiz());


        //System.out.println("vaciado "+listaLlaves.profundidad());

        /*for (int i = 0; i <= llavesProbadas.profundidad(); i++) {
            //borrar dato que está en la posición i del array de llaves
            llavesProbadas.borrar(conf.get(i));
        }*/
    }

    //cerrar la puerta
    public void cerrarPuerta() {
        //se vacía la memoria  de llaves probadas y la puerta vuelve a su estado inicial
        vaciar();

        //recorrer array de configuración e insertar en árbol de combinación

        for(int i=0;i<conf.size();i++) {
            listaLlaves.insertar(conf.get(i));
        }
        //cierra la puerta
        this.esta = estado.Cerrada;
    }

    public void mostrarCerradura() {

    }
}
