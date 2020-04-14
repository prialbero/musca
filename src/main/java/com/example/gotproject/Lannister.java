package com.example.gotproject;

public class Lannister extends Personajes {
    public Lannister() {
        super();
    }

    public Lannister(String nombreS, char marcaS, int turnoS, int salaInicioS) {
        super(nombreS,marcaS,turnoS,salaInicioS);

    }

    @Override
    public void ejecutarAcciones(Mapa map, Sala s,int salaTrono){
        if(salaActual==salaTrono)
            cambiarEstadoPuerta(s);
        mover(map);
        inspeccionarSala(s);

    }

    @Override
    public void cambiarEstadoPuerta(Sala sala){
            sala.getPuerta().cerrarPuerta();
           // System.out.println("puerta cerrada por "+this.nombre);
    }

    @Override
    public void inspeccionarSala(Sala sala){
        //suelta las llaves que tiene cada vez que entra en una sala con id par, las pierde en orden inverso al que las ha recibido
        if(!llaves.isEmpty()){
            if(sala.getIdSala()%2==0) {
                 System.out.println("Lannister suelta llave "+llaves.peek());
                sala.setLlavesEnSala(llaves.pop());
            }

        }

    }

    //juego de llaves idéntico al generado para la cerradura de la puerta al Trono
    public void llavesInicio(Llave[] c){
        for (int i = 0; i < c.length; i++) {
            llaves.push(c[i]);
        }
    }



}
