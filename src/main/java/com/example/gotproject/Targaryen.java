package com.example.gotproject;

public class Targaryen extends Personajes {
    public Targaryen() {
        super();
    }

    public Targaryen(String nombreS, char marcaS, int turnoS, int salaInicioS) {
        super(nombreS,marcaS,turnoS,salaInicioS);
       // System.out.println("clase Targaryen: "+getNombre()+" marca: "+getMarca()+" turno: "+getTurno()+" sala inicio: "+getSalaInicio());
    }

    @Override
    public void asignarRuta(Enum[] direcciones){
        //System.out.println("ruta "+direcciones.length);

    }

}
