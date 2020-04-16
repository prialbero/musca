package com.example.gotproject;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.nio.file.DirectoryIteratorException;
import java.util.ArrayList;
import java.util.List;


public class GotprojectApplication {
	/**
	 * Tipo enumerado que representa las 4 posibles direcciones en las que puede moverse un
	 * personaje.
	 */
	enum Dir {N,S,E,O}
	private static Dir[] direccionesE, direccionesD, direccionesR, direccionesT;
	//private static Mapa mapa;
	private static Stark starkE;
	private static Targaryen targaryenD;
	private static Baratheon baratheonR;
	private static Lannister lannisterT;
	private static int alturaArbol;
	private static int salaInicioLannister;
	private static int salaInicioStarks;
	private static int salaInicioTargaryen;
	private static int salaInicioBaratheon;
	private static Llave[] combinacion;
	private static int maxTurnos;

    /**
     * Log con Log4J
     */
    final static Logger logger = Logger.getLogger(GotprojectApplication.class);

	public static void main(String[] args) {

		maxTurnos = 50;
		salaInicioStarks=0;
		salaInicioTargaryen=0;
		salaInicioBaratheon=30;
		salaInicioLannister=35;

		List<List<String>> datosFichero = new ArrayList<>();

		LeerFicheroCSV leerFicheroCSV = new LeerFicheroCSV();

		datosFichero = LeerFicheroCSV.leer();
		//System.out.println("datos fichero "+datosFichero);

		for(List<String> datos : datosFichero) {
			if(!datos.isEmpty()){
				if(datos.get(0).equals("MAPA")){
					alturaArbol=Integer.parseInt(datos.get(4));
					crearMapa(Integer.parseInt(datos.get(1)), Integer.parseInt(datos.get(2)),Integer.parseInt(datos.get(3)));
					generarLlaves();
					crearPuerta();
				}
				else{
					if(datos.get(0).equals("LANNISTER")) {
						crearLannister(datos.get(1), datos.get(2).charAt(0), Integer.parseInt(datos.get(3)));
					}
					else if(datos.get(0).equals("STARK")){
						crearStark(datos.get(1),datos.get(2).charAt(0),Integer.parseInt(datos.get(3)));
					}
					else if(datos.get(0).equals("BARATHEON")){
						crearBaratheon(datos.get(1),datos.get(2).charAt(0),Integer.parseInt(datos.get(3)));
					}
					else if(datos.get(0).equals("TARGARYEN")){
						crearTargaryen(datos.get(1),datos.get(2).charAt(0),Integer.parseInt(datos.get(3)));
					}
				}
			}

		}
		Mapa.procesar(maxTurnos);
	}

	private static void crearMapa(int dimX, int dimY, int salaTrono){
		//Creación del Mapa
		//mapa = new Mapa(salaTrono, dimX, dimY);
		Mapa mapa= Mapa.crearMapa(salaTrono, dimX, dimY);
	}

    private static void generarLlaves(){
		int numLlaves = 15;
		int[] idLlaves = {17,25,19,29,13,7,1,11,5,21,3,23,9,27,15};
		combinacion = new Llave[numLlaves];
		for(int i=0; i<combinacion.length;i++){
			combinacion[i]=new Llave(idLlaves[i]);
		}

		//Generar las llaves en el mapa y distribuirlas
		int[] idSalasConLlave={3,4,6,8,9,10,11,12,13};
		Mapa.distribuirLlaves(idSalasConLlave);
	}
    private static void crearPuerta(){
		Puerta puerta = new Puerta();
		puerta.configurarPuerta(combinacion);
		//se cierra la puerta que inicialmente está abierta
		puerta.cerrarPuerta();

		//se añade la puerta al mapa (almacenada en la sala de salida del mapa)
		Mapa.insertarPuerta(puerta, alturaArbol);

		//se prueba la llave  en la puerta
		Llave llave =new Llave(3);
		puerta.probarLlave(llave);
		/**Cuando se abre la puerta se muestra un mensaje de apertura
		 * y acceso al Trono de Hierro
		 */
		if(puerta.estaAbierta()){
			puerta.mostrarCerradura();
		}
	}

    private static void crearLannister(String nombre, char marca, int turno){
		//Creación de Lannister
		lannisterT = new Lannister(nombre, marca, turno, salaInicioLannister);
		//ruta Tyrion
		direccionesT = new Dir[] {Dir.N, Dir.N, Dir.O, Dir.N, Dir.N, Dir.O, Dir.S, Dir.O,
				Dir.O, Dir.N, Dir.N, Dir.O, Dir.S, Dir.S, Dir.S, Dir.S, Dir.S,
				Dir.E, Dir.E, Dir.E, Dir.E, Dir.E};
		lannisterT.asignarRuta(direccionesT);
		lannisterT.llavesInicio(combinacion);
		Mapa.insertarPersonaje(lannisterT);
        if(logger.isInfoEnabled()){
            logger.info("Ruta: " + marca + ": " +direccionesT);
        }
	}

    private static void crearStark(String nombre, char marca, int turno){
		// Creación de Stark
// Parámetros: nombre, marca, turno en el que debe comenzar a moverse y sala inicial
		starkE = new Stark(nombre, marca, turno, salaInicioStarks);
// Creación de la ruta de Eddard:
// (ruta:E: S S E E N E N E S E S S O S E E)
		direccionesE = new Dir[] {Dir.S, Dir.S, Dir.E, Dir.E, Dir.N, Dir.E, Dir.N, Dir.E, Dir.S,
				Dir.E, Dir.S, Dir.S, Dir.O, Dir.S, Dir.E, Dir.E};
		starkE.asignarRuta(direccionesE);
		Mapa.insertarPersonaje(starkE);
        if(logger.isInfoEnabled()){
            logger.info("Ruta: " + marca + ": " +direccionesE);
        }
	}

    private static void crearTargaryen(String nombre, char marca, int turno){
		//Creación de Targaryen
		targaryenD = new Targaryen(nombre, marca, turno, salaInicioTargaryen);
		//ruta Daenerys
		direccionesD = new Dir[] {Dir.E, Dir.S, Dir.S, Dir.S, Dir.O, Dir.S, Dir.E, Dir.E, Dir.N,
				Dir.E, Dir.S, Dir.S, Dir.E, Dir.E};
		targaryenD.asignarRuta(direccionesD);
		Mapa.insertarPersonaje(targaryenD);
        if(logger.isInfoEnabled()){
            logger.info("Ruta: " + marca + ": " +direccionesD);
        }
	}

    private static void crearBaratheon(String nombre, char marca, int turno){
		//Creación de Baratheon
		baratheonR = new Baratheon(nombre, marca, turno, salaInicioBaratheon);
		//ruta Robert
		direccionesR = new Dir[] {Dir.N, Dir.N, Dir.N, Dir.E, Dir.S, Dir.E, Dir.N, Dir.N,
				Dir.E, Dir.N, Dir.E, Dir.E, Dir.S, Dir.S, Dir.S, Dir.S, Dir.S};
		baratheonR.asignarRuta(direccionesR);
		Mapa.insertarPersonaje(baratheonR);
        if(logger.isInfoEnabled()){
            logger.info("Ruta: " + marca + ": " +direccionesR);
        }
	}
}
