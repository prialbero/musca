package com.example.gotproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.nio.file.DirectoryIteratorException;


public class GotprojectApplication {
	/**
	 * Tipo enumerado que representa las 4 posibles direcciones en las que puede moverse un
	 * personaje.
	 */
	enum Dir {N,S,E,O}
	private static Dir[] direccionesE, direccionesD, direccionesR, direccionesT;



	public static void main(String[] args) {

		int dimX = 6;
		int dimY = 6;
		int salaTrono = (dimX * dimY)-1;
		int numLlaves = 5;
		//simulación del proyecto
		int[] idLlaves = {3, 2, 5, 11, 9};
		int alturaArbol = 3;
		int maxTurnos = 50;
		int salaInicioStarks=0;
		int salaInicioTargaryen=0;
		int salaInicioBaratheon=30;
		int salaInicioLannister=35;

		//Creación del Mapa
		Mapa mapa = new Mapa(salaTrono, dimX, dimY, alturaArbol);

		Llave[] combinacion = new Llave[numLlaves];
		for(int i=0; i<combinacion.length;i++){
			combinacion[i]=new Llave(idLlaves[i]);
			//System.out.println("combinacion "+combinacion[i].getId());
		}

		//Generar las llaves en el mapa y distribuirlas
		int[] idSalasConLlave={3,4,6,8,9,10,11,12,13};
		mapa.distribuirLlaves(idSalasConLlave);

		Puerta puerta = new Puerta();
		//System.out.println("combinacion "+combinacion);
		puerta.configurarPuerta(combinacion);
		//se cierra la puerta que inicialmente está abierta
		puerta.cerrarPuerta();

		//se añade la puerta al mapa (almacenada en la sala de salida del mapa)
		mapa.insertarPuerta(puerta);

		//se prueba la llave  en la puerta
		Llave llave =new Llave(3);
		puerta.probarLlave(llave);
		/*Cuando se abre la puerta se muestra un mensaje de apertura
		 * y acceso al Trono de Hierro
		 */
		if(puerta.estaAbierta()){
			puerta.mostrarCerradura();
		}
// Creación de personajes
// Creación de Stark
// Parámetros: nombre, marca, turno en el que debe comenzar a moverse y sala inicial
        Stark starkE = new Stark("Eddard", 'E', 1, salaInicioStarks);
// Creación de la ruta de Eddard:
// (ruta:E: S S E E N E N E S E S S O S E E)
       direccionesE = new Dir[] {Dir.S, Dir.S, Dir.E, Dir.E, Dir.N, Dir.E, Dir.N, Dir.E, Dir.S,
                Dir.E, Dir.S, Dir.S, Dir.O, Dir.S, Dir.E, Dir.E};
        starkE.asignarRuta(direccionesE);
      mapa.insertarPersonaje(starkE);

       //Creación de Targaryen
		Targaryen targaryenD = new Targaryen("Daenerys", 'D', 1, salaInicioTargaryen);
		//ruta Daenerys
		direccionesD = new Dir[] {Dir.E, Dir.S, Dir.S, Dir.S, Dir.O, Dir.S, Dir.E, Dir.E, Dir.N,
				Dir.E, Dir.S, Dir.S, Dir.E, Dir.E};
		targaryenD.asignarRuta(direccionesD);
		mapa.insertarPersonaje(targaryenD);

		//Creación de Baratheon
		Baratheon baratheonR = new Baratheon("Robert", 'R', 1, salaInicioBaratheon);
		//ruta Robert
		direccionesR = new Dir[] {Dir.N, Dir.N, Dir.N, Dir.E, Dir.S, Dir.E, Dir.N, Dir.N,
				Dir.E, Dir.N, Dir.E, Dir.E, Dir.S, Dir.S, Dir.S, Dir.S, Dir.S};
		baratheonR.asignarRuta(direccionesR);
		mapa.insertarPersonaje(baratheonR);

		//Creación de Lannister
		Lannister lannisterT = new Lannister("Tyrion", 'T', 1, salaInicioLannister);
		//ruta Tyrion
		direccionesT = new Dir[] {Dir.N, Dir.N, Dir.O, Dir.N, Dir.N, Dir.O, Dir.S, Dir.O,
				Dir.O, Dir.N, Dir.N, Dir.O, Dir.S, Dir.S, Dir.S, Dir.S, Dir.S,
				Dir.E, Dir.E, Dir.E, Dir.E, Dir.E};
		lannisterT.asignarRuta(direccionesT);
		mapa.insertarPersonaje(lannisterT);

			mapa.procesar(maxTurnos);

	}
}
