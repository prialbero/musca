package com.example.gotproject;

import java.util.ArrayList;
import java.util.List;

public class Puerta{

    private List<Llave> listaLlaves = new ArrayList<>();
    private List<Llave> llavesProbadas = new ArrayList<>();
    private boolean abierta = false;
    int [] idLlaves={1,2,3,5,6};

    public Puerta(){
       listaLlaves.add(new Llave(1));
       listaLlaves.add(new Llave(2));
       listaLlaves.add(new Llave(3));

       Llave llave1 = new Llave(1);
       Llave llave4 = new Llave(4);
        llave1.compareTo(llave4);

        System.out.println("llave "+llave4.getId()+" "+listaLlaves.contains(llave4));
    }


}
