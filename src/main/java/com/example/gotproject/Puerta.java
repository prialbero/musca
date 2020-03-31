package com.example.gotproject;
import  com.example.gotproject.Arbol;
import java.util.ArrayList;
import java.util.List;

public class Puerta{

    private Arbol<Llave> listaLlaves = new Arbol<>();
    private Arbol<Llave> llavesProbadas = new Arbol<>();
    private boolean abierta;
    private enum Estado{NoConfig, Abierta, Cerrada, Config};

    public Puerta(){
        Estado valorEstado = Estado.NoConfig;
    }
    public void ConfigurarPuerta(Llave[] c){
        for(int i=0;i<c.length;i++){
            listaLlaves.insertar(c[i]);
            System.out.println("arbol "+listaLlaves.profundidad());
        }
        Estado valorEstado = Estado.Config;
    }

}
