package com.example.gotproject;
import  com.example.gotproject.Arbol;
import java.util.ArrayList;
import java.util.List;

public class Puerta{
    //lista de configuración de la puerta
    private List<Llave> conf = new ArrayList<>();
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

            int idcombi = c[i].getId();

            conf.add(new Llave(idcombi));
            //System.out.println("conf "+idcombi);
            listaLlaves.insertar(c[i]);
            //las llaves que tengan identificador impar se utilizarán para configurar la puerta
            /*if(idcombi%2 != 0){
                 if(conf.isEmpty()){
                     System.out.println("array vacío");
                     //se inserta el primer elemento

                 }
                 else System.out.println("array con elementos");
                System.out.println("configurar "+idcombi);
            }*/
        }

    }

    //abrir puerta
    public void probarLlave(Llave k){
        //System.out.println("estado "+esta);
        if(esta == estado.Cerrada){
            //la puerta comprueba si la llave se ha probado
            if(llavesProbadas.pertenece(k)){
                System.out.println("Ya se ha probado esta llave");
            } //si no se ha probado y la llave forma parte de la combinación, se elimina de la misma
            else if(listaLlaves.pertenece(k)){
                   // System.out.println("pertenece a la combinacion "+listaLlaves.pertenece(k));
                    llavesProbadas.insertar(k);
                    listaLlaves.borrar(k);
                    System.out.println("lista llaves "+listaLlaves.profundidad());
                    System.out.println("llaves probadas "+llavesProbadas.profundidad());
                }
            /**se abre la puerta si la profundidad de la combinacion es menor que el valor
             * y si el numero de llaves internas es mayor o igual al numero de llaves finales
             */
        }
        else System.out.println("Puerta abierta");

    }
    public boolean estaAbierta(){
        if(esta == estado.Abierta)
            return  true;
        else return false;
    }
    //vaciar arboles
    public void vaciar(){

        for(int i=0;i<=listaLlaves.profundidad();i++){
            //borrar dato que está en la posición i del array de llaves
            listaLlaves.borrar(conf.get(i));
            System.out.println("vaciar "+listaLlaves.profundidad());
        }
        for(int i=0;i<=llavesProbadas.profundidad();i++){
            //borrar dato que está en la posición i del array de llaves
            llavesProbadas.borrar(conf.get(i));
            System.out.println("vaciar probadas "+llavesProbadas.profundidad());
        }
    }

    //cerrar la puerta
    public void cerrarPuerta(){
       //cada vez que se cierra la puerta se vacía la memoria  de llaves probadas y la puerta vuelve a su estado inicial
        vaciar();
        esta=estado.Cerrada;
    }
    public void mostrarCerradura(){

    }
}
