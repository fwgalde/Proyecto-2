package edd.util;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 * Clase que implementa una base de datos de alumnos donde se almacenan numeros de cuenta y edades.
 *
 * @author Ugalde Ubaldo, Fernando.
 * @version 1.0
 */
public class BaseDeDatos {
    private ArbolAVL<Integer> aux;
    private HashMap<Integer, Integer> hashing;
    private int edades[];
    private int nuevosAlumnos;

    /**
     * Metodo constructor.
     */
    public BaseDeDatos() {
	aux = new ArbolAVL<>();
	hashing = new HashMap<>();
	edades = new int [8];
	nuevosAlumnos = 0;
    }

    /**
     * Metodo getter de aux.
     * @return Devuelve el arbol.
     */
    public ArbolAVL<Integer> getAux() {
	return aux;
    }

    /**
     * Metodo setter de aux.
     * @param aux Arbol que queremos poner.
     */
    public void setAux(ArbolAVL<Integer> aux) {
	this.aux = aux;
    }

    /**
     * Metodo getter de hashing.
     * @return Devuelve el HashMap de numeros de cuenta - edad.
     */
    public HashMap<Integer, Integer> getHashing() {
	return hashing;
    }

    /**
     * Metodo setter de hashing.
     * @param hashing HashMap que queremos poner.
     */
    public void setHashing(HashMap<Integer, Integer> hashing) {
	this.hashing = hashing;
    }

    /**
     * Metodo getter de edades.
     * @return Devuelve el arreglo de las edades.
     */
    public int[] getEdades() {
	return edades;
    }

    /**
     * Metodo setter de edades.
     * @param edades Arreglo que queremos poner.
     */
    public void setEdades(int[] edades) {
	this.edades = edades;
    }

    /**
     * Metodo getter de nuevosAlumnos.
     * @return Devuelve cuantos nuevos alumnos hay.
     */
    public int getNuevosAlumnos() {
	return nuevosAlumnos;
    }

    /**
     * Metodo setter de nuevosAlumnos.
     * @param nuevosAlumnos Alumnos nuevos para la grafica.
     */
    public void setNuevosAlumnos(int nuevosAlumnos) {
	this.nuevosAlumnos = nuevosAlumnos;
    }

    /**
     * Metodo que registra un nuevo alumno.
     * @param numCuenta Numero de cuenta del alumno.
     * @param edad Edad del alumno.
     */
    public void registraAlumno(Integer numCuenta, Integer edad) {
	hashing.put(numCuenta, edad);
	aux.agrega(numCuenta);
	setNuevosAlumnos(nuevosAlumnos + 1);
    }

    /**
     * Metodo que devuelve el metodo toString() del arbol en la base de datos.
     */
    public void regresaArbolAsociado() {
	ImageIcon arbol = new ImageIcon("tree.png");
	if(aux.estaVacia())
	    JOptionPane.showMessageDialog(null, "No hay alumnos registrados", "Arbol asociado", JOptionPane.INFORMATION_MESSAGE, arbol);
	else
	    JOptionPane.showMessageDialog(null, aux, "Arbol asociado", JOptionPane.INFORMATION_MESSAGE, arbol);
    }

    /**
     * Metodo que actualiza los valores de la grafica.
     */
    public void actualizaGrafica() {
	if(nuevosAlumnos == 0) {
	    return;
	}
	reset(edades);
	actualizaGrafica(getAux().getRaiz());
    }

    /**
     * Metodo auxiliar recursivo que actualiza los valores de la grafica, utilizando la hashmap y el arbol.
     * (Se podia hacer mas sencillo, pero decidi hacerlo asi para hacer un mayor uso del arbol y hashing)
     * @param nodoArbol Raiz del arbol
     */
    private void actualizaGrafica(ArbolAVL<Integer>.NodoArbol<Integer> nodoArbol) {
	if(nodoArbol == null)
	    return;
	int edad = hashing.get(nodoArbol.getData());
	if(edad == 18) {
	    edades[0]++;
	} else if(edad == 19) {
	    edades[1]++;
	} else if(edad == 20) {
	    edades[2]++;
	} else if(edad == 21) {
	    edades[3]++;
	} else if(edad == 22) {
	    edades[4]++;
	} else if(edad == 23) {
	    edades[5]++;
	} else if(edad == 24) {
	    edades[6]++;
	} else if(edad == 25) {
	    edades[7]++;
	}
	actualizaGrafica(nodoArbol.getIzqNodo());
	actualizaGrafica(nodoArbol.getDerNodo());
    }

    /**
     * Metodo que pone todos los valor de un arreglo en 0.
     * @param a Arreglo que se quiere resetear.
     */
    private static void reset(int[] a) {
	for(int i = 0; i < a.length; i++) {
	    a[i] = 0;
	}
    }

}
