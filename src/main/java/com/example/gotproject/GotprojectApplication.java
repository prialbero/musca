package com.example.gotproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class GotprojectApplication {

	public static void main(String[] args) {

		int dimX = 6;
		int dimY = 6;
		int salaTrono = (dimX * dimY)-1;
		int numLlaves = 5;
		//simulación del proyecto
		int[] idLlaves = {3, 2, 5, 11, 9};
		int alturaArbol = 3;
		int maxTurnos = 50;

		//Creación del Mapa
		Mapa mapa = new Mapa(salaTrono, dimX, dimY, alturaArbol);

		Llave[] combinacion = new Llave[numLlaves];
		for(int i=0; i<combinacion.length;i++){
			combinacion[i]=new Llave(idLlaves[i]);
			//System.out.println("combinacion "+combinacion[i].getId());
		}

		Puerta puerta = new Puerta();
		//System.out.println("combinacion "+combinacion);
		puerta.configurarPuerta(combinacion);
		//se cierra la puerta que inicialmente está abierta
		puerta.cerrarPuerta();
		//se prueba la llave  en la puerta
		Llave llave =new Llave(3);
		puerta.probarLlave(llave);
		/*Cuando se abre la puerta se muestra un mensaje de apertura
		 * y acceso al Trono de Hierro
		 */
		if(puerta.estaAbierta()){
			puerta.mostrarCerradura();
		}


	}
}
