package com.example.gotproject;

import java.util.Objects;

public class Llave implements Comparable<Llave>{
    private int id;

    public Llave(int id){

        this.id=id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//el método compareto indica si el objeto representado por this es menor, igual o mayor que el objeto que se pasa por parámetro
@Override
public int compareTo(Llave a){
    //System.out.println("compareto "+this.id+" "+a.id);
    return this.id - a.id;
}
    //se usa equals en las colecciones de objetos, para comprobar que un objeto ya está incluido en la colección
    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()) { return false;}
        Llave llave = (Llave) o;
        //System.out.println("equals "+llave.getId());

        return id == llave.id;
    }
    //se usa hashcode en los map para encontrar el objeto asociado a la clave
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
