package edd.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que implmenta el comportamiento de una Cola.
 * @author Ugalde Ubaldo, Fernando.
 *
 * @param <T> El tipo de elementos que tiene la Cola.
 */
public class Cola<T> implements Iterable<T> {
    private Nodo<T> inicio;
    private Nodo<T> ultimo;
    private int size;

    /**
     * Metodo que devuelve el size de la Cola.
     * @return size, size de la Cola.
     */
    public int getTamanio() {
	return size;
    }

    /**
     * Regresa el valor almacenado en la posicion i.
     * @param index Posicion en la cola (0 inicio, n fin).
     * @return Valor almacenado en la posicion i.
     */
    public T getElementoByIndice(int index) {
	if (!estaVacia() && (index < size)) {
	    Nodo<T> temp = inicio;
	    for(int i = 0; i < index; i++) {
		temp = temp.anterior;
	    }
	    return temp.datos;
	}
	throw new NoSuchElementException();
    }

    /**
     * Metodo que imprime toda la Cola.
     */
    public void imprimeQueue() {
	if(estaVacia())
	    System.out.println("La cola esta vacia.");
	else {
	    System.out.print("[");
	    for(T e : this) {
		System.out.print(e + ",");
	    }
	    System.out.println("\b]");
	}
    }

    /**
     * Metodo que agrega un elemento a la Cola.
     * @param elemento Dato del nuevo elemento.
     */
    public void encolar(T elemento) {
	if (estaVacia()) {
	    Nodo<T> nuevo = new Nodo<>(elemento);
	    inicio = nuevo;
	    ultimo = nuevo;
	} else {
	    Nodo<T> nuevo = new Nodo<>(elemento, ultimo);
	    ultimo.anterior = nuevo;
	    ultimo = nuevo;
	}
	size++;
    }

    /**
     * Metodo que elimina y devuelve el primer elemento de la Cola.
     * @return elemento El primer elemento en la Cola.
     */
    public T desencolar() {
	if(estaVacia())
	    return null;
	if(inicio.datos == null && getTamanio() == 1) {
	    inicio = ultimo = null;
	    return null;
	}
	T elemento = inicio.datos;
	inicio = inicio.anterior;
	inicio.siguiente = null;
	size--;
	return elemento;
    }

    /**
     * Metodo que busca un elemento dentro de la Cola.
     * @param elemento Elemento buscado.
     * @return true si esta, false en otro caso.
     */
    public boolean busca(T elemento) {
	boolean b = false;
	if (!estaVacia()) {
	    if(ultimo.datos == elemento || inicio.datos == elemento) {
		b = true;
	    } else {
		Nodo<T> temp = ultimo.siguiente;
		while (temp != inicio) {
		    if (temp.datos.equals(elemento)) {
			b = true;
			break;
		    }
		    temp = temp.siguiente;
		}
	    }
	}
	return b;
    }

    /**
     * Metodo que regresa el indice del primer elemento que coincide con el elemento pedido.
     * @param elemento Elemento buscado.
     * @return int Indice del primer elemento encontrado.
     */
    public int getIndiceByElemento(T elemento) {
	if (!estaVacia() && busca(elemento)) {
	    Nodo<T> temp = inicio;
	    int i = 0;
	    while (temp.datos != elemento) {
		i++;
		temp = temp.anterior;
	    }
	    return i;
	}
	return -1;
    }

    /**
     * Metodo que comprueba si la cola esta vacia o no.
     * @return true si esta vacia, false en otro caso.
     */
    public boolean estaVacia() {
	return inicio == null && ultimo == null;
    }

    @Override
    public Iterator<T> iterator() {
	return new MiIterador<T>(this);
    }

    /**
     * Metodo que devuleve, pero no elimina, el primer nodo de la Cola.
     * @return Nodo<T> primer nodo en la Cola.
     */
    public Nodo<T> peek() {
	if(!estaVacia()) {
	    return inicio;
	}
	throw new NoSuchElementException();
    }

    /**
     * Clase Nodo
     * @author Ugalde Ubaldo, Fernando.
     *
     * @param <E> El tipo de elementos que tiene el Nodo.
     */
    private class Nodo<E> {
	E datos;
	Nodo<E> siguiente;
	Nodo<E> anterior;

	/**
	 * Metodo constructor que solo recibe los datos.
	 * @param datos Datos del nodo.
	 */
	Nodo(E datos) {
	    this(datos, null, null);
	}

	/**
	 * Metodo constructor que recibe datos y siguiente nodo.
	 * @param datos Datos del nodo.
	 * @param siguiente Siguiente nodo.
	 */
	Nodo(E datos, Nodo<E> siguiente) {
	    this(datos, siguiente, null);
	}

	/**
	 * Metodo constructor de nodo.
	 * @param datos Datos del nodo.
	 * @param siguiente Siguiente nodo.
	 * @param anterior Nodo anterior.
	 */
	Nodo(E datos, Nodo<E> siguiente, Nodo<E> anterior) {
	    this.datos = datos;
	    this.siguiente = siguiente;
	    this.anterior = anterior;
	}
    }

    /**
     * Clase Iterador para Cola
     * @author Ugalde Ubaldo, Fernando.
     *
     * @param <E> El tipo de elementos que recorre el Iterador.
     */
    private class MiIterador<E> implements Iterator<T> {
	Nodo<T> actual;

	/**
	 * Metodo constructor
	 * @param cola Cola que sera iterada.
	 */
	public MiIterador(Cola<T> cola) {
	    actual = cola.peek();
	}

	@Override
	public boolean hasNext() {
	    return actual != null;
	}

	@Override
	public T next() {
	    if (hasNext()) {
		T datos = actual.datos;
		actual = actual.anterior;
		return datos;
	    }
	    throw new NoSuchElementException();
	}
    }
}
