package com.example.gotproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class GotprojectApplication {

	public static void main(String[] args) {

		int numLlaves = 5;
		//simulación del proyecto
		int[] idLlaves = {1, 2, 3, 5, 6};

		Llave[] combinacion = new Llave[numLlaves];
		for(int i=0; i<combinacion.length;i++){
			combinacion[i]=new Llave(idLlaves[i]);
			//System.out.println("combinacion "+combinacion[i].getId());
		}

		Puerta puerta = new Puerta();
		//System.out.println("combinacion "+combinacion);
		puerta.ConfigurarPuerta(combinacion);
	}
}
