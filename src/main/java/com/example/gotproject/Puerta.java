package com.example.gotproject;
import  com.example.gotproject.Arbol;
import java.util.ArrayList;
import java.util.List;

public class Puerta{
    //lista de configuración de la puerta
    private List<Llave> conf;
    //arbol que guarda la combinación de la puerta
    private Arbol<Llave> listaLlaves = new Arbol<>();
    //arbol que guarda las llaves usadas
    private Arbol<Llave> llavesProbadas = new Arbol<>();
   //tipo enumerado que define el estado de la puerta
    private enum estado{NoConfig, Abierta, Cerrada, Config};
    private estado esta;
    /**Entero que marca el limite superior de la profundidad que se necesita para abrir la puerta*/
    private int profundidad;

    public Puerta(){

        esta = estado.NoConfig;
    }

    //configuración de la puerta con la combinación de llaves
    //una vez configurada debe cerrarse la puerta
    public void configurarPuerta(Llave[] c){
        for(int i=0;i<c.length;i++){
            listaLlaves.insertar(c[i]);
        }
        esta = estado.Cerrada;
    }

    //abrir puerta
    public void probarLlave(Llave k){
        if(esta == estado.Cerrada){
            //la puerta comprueba si la llave se ha probado
            if(llavesProbadas.pertenece(k)){
                System.out.println("Ya se ha probado esta llave");
            } //si no se ha probado y la llave forma parte de la combinación, se elimina de la misma
            else if(listaLlaves.pertenece(k)){
                    System.out.println("pertenece a la combinacion "+listaLlaves.pertenece(k));
                    llavesProbadas.insertar(k);
                    listaLlaves.borrar(k);
                    System.out.println("lista llaves "+listaLlaves.profundidad());
                    System.out.println("llaves probadas "+llavesProbadas.profundidad());
                }
            /**se abre la puerta si la profundidad de la combinacion es menor que el valor
             * y si el numero de llaves internas es mayor o igual al numero de llaves finales
             */


        }
        else System.out.println("puerta abierta");

    }
    /*public boolean estaAbierta(){
        return  true;
    }*/
    //vaciar arboles
    public void vaciar(){

    }

    //cerrar la puerta
    public void cerrarPuerta(){
       //cada vez que se cierra la puerta se vacía la memoria  de llaves probadas y la puerta vuelve a su estado inicial
        //vaciar();
        esta=estado.NoConfig;
    }
}
