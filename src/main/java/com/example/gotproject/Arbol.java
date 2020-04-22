/**
 *Nombre del grupo: return ( eskarpi && danh) 
 *Nombre y apellidos de cada componente: 
 *@author Daniel Llanos Muñoz
 *@author Alberto Iglesias Gonzalo
 *Curso: 2º
 * */
package com.example.gotproject;
public class Arbol <T extends Comparable <T>> {

	/** Dato almacenado en cada nodo del Arbol. */
	private T datoRaiz;
	
	/** Atributo que indica si el Arbol esta vacio. */
	boolean esVacio;
	
	/** Hijo izquierdo del nodo actual */
	private Arbol <T> hIzq;
	
	/** Hijo derecho del nodo actual */
	private Arbol <T> hDer;
	
	/**
	 * Constructor por defecto de la clase. Inicializa un Arbol vaco.
	 */
	public Arbol (){
	    this.esVacio=true;
	    this.hIzq = null;
	    this.hDer = null;
	}

	/**
	 * Crea un nuevo Arbol a partir de los datos pasados por parmetro.
	 *
	 * @param hIzq El hijo izquierdo del rbol que se est creando 
	 * @param datoRaiz Raiz del rbol que se esta creando
	 * @param hDer El hijo derecho del Arbol que se esta creando
	 */
	public Arbol (Arbol<T> hIzq, T datoRaiz, Arbol <T> hDer){
		this.datoRaiz = datoRaiz;
		this.hIzq = hIzq;
		this.hDer=hDer;
	}
	
	/**
	 * Devuelve el hijo izquierdo del Arbol
	 *
	 * @return El hijo izquierdo
	 */
	public Arbol <T> getHijoIzq(){
		return hIzq;
	}
	
	/**
	 * Devuelve el hijo derecho del Arbol
	 *
	 * @return Hijo derecho del Arbol
	 */
	public Arbol <T> getHijoDer(){
		return hDer;
	}
	
	/**
	 * Devuelve la raiz del Arbol
	 *
	 * @return La raiz del Arbol
	 */
	public T getRaiz(){
		return datoRaiz;
	}

	/**
	 * Comprueba si el Arbol esta vacio.
	 * @return verdadero si el Arbol esta vacio, falso en caso contrario
	 */
	public boolean vacio(){
		return esVacio;
	}
	
	/**
	 * Inserta un nuevo dato en el Arbol.
	 *
	 * @param dato El dato a insertar
	 * @return verdadero si el dato se ha insertado correctamente, falso en caso contrario
	 */
	public boolean insertar(T dato){
	    boolean resultado=true;
	    if (vacio()) {
	        datoRaiz = dato;
			esVacio = false;
		}
	    else {
	        if (!(this.datoRaiz.equals(dato))) {
	            Arbol <T> aux;
	            if (dato.compareTo(this.datoRaiz)<0) { //dato < datoRaiz
	                if ((aux=getHijoIzq())==null)
	                    hIzq = aux = new Arbol <T> ();
	            }
	            else {									//dato > datoRaiz
	                if ((aux=getHijoDer())==null)
	                    hDer = aux = new Arbol <T> ();
	            }
	            resultado = aux.insertar(dato);
	        }
	        else
	            resultado=false;
	    }
	    return resultado;
	}
	
	/**
	 * Comprueba si un dato se encuentra almacenado en el Arbol
	 *
	 * @param dato El dato a buscar
	 * @return verdadero si el dato se encuentra en el Arbol, falso en caso contrario
	 */
	public boolean pertenece(T dato){
	    Arbol <T> aux=null;
	    boolean encontrado=false;
	    if (!vacio()) {
	    	
	        if (this.datoRaiz.equals(dato))
	            encontrado = true;
	        else {
	            if (dato.compareTo(this.datoRaiz)<0)	//dato < datoRaiz
	                aux=getHijoIzq();
	            else									//dato > datoRaiz
	                aux = getHijoDer();
	            if (aux!=null)
	                encontrado = aux.pertenece(dato);
	        }
	    }
	    return encontrado;
	}
	
	/**
	 * Borrar un dato del Arbol.
	 *
	 * @param dato El dato que se quiere borrar
	 */
	public void borrar(T dato){
	    if (!vacio() && pertenece(dato)) {
	        if (dato.compareTo(this.datoRaiz)<0){
	        	if(hIzq !=null)					//dato<datoRaiz
					hIzq = hIzq.borrarOrden(dato);
			}	
	        else
	            if (dato.compareTo(this.datoRaiz)>0) {
	            	if(hDer !=null)				//dato>datoRaiz 
	            		hDer = hDer.borrarOrden(dato);
				}
	            else //En este caso el dato es datoRaiz
	            {
	                if (hIzq==null && hDer==null)
	                {
	                    esVacio=true;
	                }
	                else
	                    borrarOrden(dato);
	            }
	    }
	}
	

	/**
	 * Borrar un dato. Este metodo es utilizado por el metodo borrar anterior.
	 *
	 * @param dato El dato a borrar
	 * @return Devuelve el Arbol resultante despues de haber realizado el borrado
	 */
	private Arbol <T> borrarOrden(T dato)
	{
	    T datoaux;
	    Arbol <T> retorno=this;
	    Arbol <T> aborrar, candidato, antecesor;

	    if (!vacio() && pertenece(dato)) {
	        if (dato.compareTo(this.datoRaiz)<0){
	        	if(hIzq !=null)			// dato<datoRaiz
		    	        hIzq = hIzq.borrarOrden(dato);
	        }
			else
	            if (dato.compareTo(this.datoRaiz)>0) {
	            	if(hDer !=null)// dato>datoRaiz
	    	           hDer = hDer.borrarOrden(dato);
	            }
				else {
	                aborrar=this;
	                if ((hDer==null)&&(hIzq==null)) { /*si es hoja*/
	                    retorno=null;
	                }
	                else {
	                    if (hDer==null) { /*Solo hijo izquierdo*/
	                        aborrar=hIzq;
	                        datoaux=this.datoRaiz;
	                        datoRaiz=hIzq.getRaiz();
	                        hIzq.datoRaiz = datoaux;
	                        hIzq=hIzq.getHijoIzq();
	                        hDer=aborrar.getHijoDer();

	                        retorno=this;
	                    }
	                    else
	                        if (hIzq==null) { /*Solo hijo derecho*/
	                            aborrar=hDer;
	                            datoaux=datoRaiz;
	                            datoRaiz=hDer.getRaiz();
	                            hDer.datoRaiz = datoaux;
	                            hDer=hDer.getHijoDer();
	                            hIzq=aborrar.getHijoIzq();

	                            retorno=this;
	                        }
	                        else { /* Tiene dos hijos */
	                            candidato = this.getHijoIzq();
	                            antecesor = this;
	                            while (candidato.getHijoDer()!=null) {
	                                antecesor = candidato;
	                                candidato = candidato.getHijoDer();
	                            }

	                            /*Intercambio de datos de candidato*/
	                            datoaux = datoRaiz;
	                            datoRaiz = candidato.getRaiz();
	                            candidato.datoRaiz=datoaux;
	                            aborrar = candidato;
	                            if (antecesor==this)
	                                hIzq=candidato.getHijoIzq();
	                            else
	                                antecesor.hDer=candidato.getHijoIzq();
	                        } //Eliminar solo ese nodo, no todo el subarbol
	                    aborrar.hIzq=null;
	                    aborrar.hDer=null;
	                }
	            }
	    }
	    return retorno;
	}
	
	
	/**
	 * Recorrido inOrden del Arbol.
	 */
	public void inOrden(){
	    Arbol <T> aux=null;
	    if (!vacio()) {
	    	   
	        if ((aux=getHijoIzq())!=null) {
	            aux.inOrden();
	        }    
	        System.out.print(this.datoRaiz);
	         if ((aux=getHijoDer())!=null){
	            aux.inOrden();
	        }    
	         
	    }
	}
	
	/** 
	 * Devuelve el numero de hojas de un arbol
	 * @return entero con el numero de hojas
	 */
	public int NodoHijo(){
		int cont=0;
		Arbol <T> aux=new Arbol <T> ();
		 if (!vacio()) {
	    	   if(hDer == null && hIzq == null )
	    		   cont= 1;
	    	 
		        if ((aux=getHijoIzq())!=null) {
		           cont=cont + aux.NodoHijo();
		        }    
		       
		         if ((aux=getHijoDer())!=null){
		        	 cont= cont + aux.NodoHijo();
		        }  
		 }
		   return cont;
	}
	
	/** 
	 * Devuelve el numero de no hojas
	 * @return entero con el numero de no hojas
	 */
	public int NodoPadre(){
		int cont=0;
		Arbol <T> aux=new Arbol <T>();
		 if (!vacio()) {
			 if(hDer != null || hIzq != null )
	    		   cont= 1;
	    	 
	    	   if ((aux=getHijoIzq())!=null) {
	    		   cont=cont + aux.NodoPadre();
		        }    
		       
		         if ((aux=getHijoDer())!=null){
		        	 cont= cont + aux.NodoPadre();
		        }  
		 }
		   return cont;
	}
	
	/** 
	 * Devuelve la profundidad de un arbol
	 * @return entero con la profundidad del arbol
	 */
	public int profundidad(){
		Arbol <T> izq=new Arbol <T> (),der=new Arbol <T> ();
		int prof;
		if(esVacio==true)
			prof=0;
		else{
			if(getHijoIzq()!=null)
				izq=getHijoIzq();
			if(getHijoDer()!=null)
				der=getHijoDer();
			prof=maximo(izq.profundidad(),der.profundidad())+1 ;
		}
		return prof;
			
	}
	
	/** 
	 * Devuelve el maximo de dos numeros (PROVISIONAL)
	 * @param n, m numeros a comparar
	 * @return entero con el maximo de dos numeros
	 */
	private int maximo(int n,int m){
		if(n>m)
			return n;
		else
			return m;
	}
	

}