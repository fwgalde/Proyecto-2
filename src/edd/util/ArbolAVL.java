package edd.util;

/**
 * Clase que implementa un arbol AVL.
 * @author Ugalde Ubaldo, Fernando.
 * @version 3.0
 *
 * @param <T> El tipo de elementos que tiene el arbol.
 */
public class ArbolAVL<T extends Comparable<T>> {

    private NodoArbol<T> raiz;
    private int size;

    /**
     * Metodo constructor del arbol AVL.
     */
    public ArbolAVL() {
		raiz = null;
    }

    /**
     * Metodo getter de raiz.
     * @return Devuelve el nodo raiz del arbol AVL.
     */
    public NodoArbol<T> getRaiz() {
	return raiz;
    }

    /**
     * Metodo getter de size.
     * @return Devuelve el size del arbol AVL.
     */
    public int getSize() {
	return size;
    }

    /**
     * Metodo que comprueba si el arbol esta vacio.
     * @return true si esta vacio, false en otro caso.
     */
    public boolean estaVacia() {
	return raiz == null;
    }

    /**
     * Metodo que comprueba si el arbol contiene un dato.
     * @param dato Valor a comprobar si se encuentra.
     * @return true si lo contiene, false en otro caso.
     */
    public boolean contiene(T dato){
	return contiene(raiz, dato);
    }

    /**
     * Metodo auxiliar del metodo contiene que revisa recursivamente si se encuentra el dato.
     * @param n Nodo en el que se empieza a checar.
     * @param dato Valor a comprobar si se encuentra.
     * @return true si lo contiene, false en otro caso.
     */
    private boolean contiene(NodoArbol<T> n, T dato) {
	boolean b = false;
	if (n == null)
	    return b;
	if(n.getData().equals(dato))
	    b = true;
	else if(dato.compareTo(n.getData()) < 0)
	    b = contiene(n.getIzqNodo(), dato);
	else
	    b = contiene(n.getDerNodo(), dato);
	return b;
    }

    /**
     * Metodo que devuelve el nodo que contiene un dato.
     * @param dato Valor del nodo buscado.
     * @return Nodo buscado, null en caso de no ser encontrado.
     */
    public NodoArbol<T> busca(T dato){
	return busca(raiz, dato);
    }

    /**
     * Metodo auxiliar recursivo que busca el nodo que contiene un dato.
     * @param n Nodo en el que empezamos la recursion.
     * @param dato Valor del nodo que buscamos.
     * @return Nodo buscado, null en otro caso.
     */
    private NodoArbol<T> busca(NodoArbol<T> n, T dato) {
	if(!contiene(dato))
	    return null;
	else if(n.getData().equals(dato))
	    return n;
	else if(dato.compareTo(n.getData()) < 0)
	    return busca(n.getIzqNodo(), dato);
	else
	    return busca(n.getDerNodo(), dato);
    }

    /**
     * Metodo que agrega un nuevo nodo al arbol.
     * @param elemento Valor del nuevo nodo.
     */
    public void agrega(T elemento) {
	NodoArbol<T> nuevo = new NodoArbol<>(elemento);

	if(raiz == null)
	    raiz = nuevo;
	else {
	    raiz = agrega(raiz, elemento);
	}
	size++;
    }

    /**
     * Metodo auxiliar que agrega un nuevo nodo al arbol de manera recursiva.
     * @param n Nodo en el que empieza el recorrido.
     * @param elemento Valor del nuevo nodo.
     * @return Nuevo nodo raiz que contendra el nodo nuevo.
     */
    private NodoArbol<T> agrega(NodoArbol<T> n, T elemento) {
	NodoArbol<T> nuevo = new NodoArbol<>(elemento);
	if(elemento.compareTo(n.getData()) < 0) {
	    if(!n.hayNodoIzq()) {
		n.setIzqNodo(nuevo);
		nuevo.setPadre(n);
	    } else
		agrega(n.getIzqNodo(), elemento);
	} else {
	    if(!n.hayNodoDer()) {
		n.setDerNodo(nuevo);
		nuevo.setPadre(n);
	    } else
		agrega(n.getDerNodo(), elemento);
	}
	return resivaBF(n);
    }

    /**
     * Metodo que elimina el nodo que contiene el valor de dato.
     * @param dato Valor del nodo que queremos eliminar.
     */
    public void elimina(T dato) {
	if(!contiene(dato))
	    return;
	raiz = elimina(raiz, dato);
	size--;
    }

    /**
     * Metodo auxiliar recursivo que elimina el nodo que contiene el valor dato.
     * @param n Nodo del que se empieza a revisar.
     * @param dato Valor del nodo que queremos eliminar.
     * @return Nuevo nodo raiz que ya no contiene el nodo a eliminar.
     */
    private NodoArbol<T> elimina(NodoArbol<T> n, T dato) {
	if(n == null) {
	    return null;
	}
	if(dato.compareTo(n.getData()) < 0)
	    return elimina(n.getIzqNodo(), dato);
	else if(dato.compareTo(n.getData()) > 0)
	    return elimina(n.getDerNodo(), dato);

	if(esHoja(n)) {
	    if(n.esHijoIzq()) {
		n.getPadre().setIzqNodo(null);
	    }
	    else {
		n.getPadre().setDerNodo(null);
	    }
	} else if(n.hayNodoIzq() && !n.hayNodoDer()) {
	    NodoArbol<T> subtree = n.getIzqNodo();
	    n.setIzqNodo(null);
	    if(n.esHijoIzq()) {
		n.getPadre().setIzqNodo(subtree);
	    }
	    else {
		n.getPadre().setDerNodo(subtree);
	    }
	    subtree.setPadre(n.getPadre());
	} else if(!n.hayNodoIzq() && n.hayNodoDer()) {
	    NodoArbol<T> subtree = n.getDerNodo();
	    n.setDerNodo(null);
	    n.setIzqNodo(null);
	    if(n.esHijoIzq()) {
		n.getPadre().setIzqNodo(subtree);
	    }
	    else {
		n.getPadre().setDerNodo(subtree);
	    }
	    subtree.setPadre(n.getPadre());
	} else {
	    T valorMinimo = minVal(n.getDerNodo());
	    elimina(n, valorMinimo);
	    n.setData(valorMinimo);
 		}
	return resivaBF(n);
    }

    /**
     * Metodo que actualiza el factor de balance del nodo n
     * @param n Nodo al cual le vamos a actualizar su factor de balance.
     */
    private void actualizaBF(NodoArbol<T> n) {
	if(n == null)
	    return;
	n.setBF(altura(n.getDerNodo()) - altura(n.getIzqNodo()));
    }

    /**
     * Metodo que revisa si los factores de balance de todos.
     * @param n Nodo al cual revisamos su factor de balance.
     * @return n si su factor de balance es bueno, sino rotamos y devolvemos el nodo nuevo.
     */
    private NodoArbol<T> resivaBF(NodoArbol<T> n) {
	actualizaBF(n);
	if(n.getBF() > 1 || n.getBF() < -1) {
	    NodoArbol<T> nuevo = balance(n);
	    return nuevo;
	}
	if (n.getPadre() != null)
	    return resivaBF(n.getPadre());
	return n;
    }

    /**
     * Metodo que revisa que tipo de rotacion necesita el nodo.
     * @param n Nodo el cual vamos a rotar.
     * @return Nodo rotado.
     */
    private NodoArbol<T> balance(NodoArbol<T> n) {
	if(n.getBF() == -2 && n.getIzqNodo().getBF() <= 0)
	    return rotacionDer(n);
	else if(n.getBF() == -2)
	    return rotacionLR(n);
	else if (n.getBF() == 2 && n.getDerNodo().getBF() >= 0)
	    return rotacionIzq(n);
	else if(n.getBF() == 2)
	    return rotacionRL(n);
	return n;
    }

    /**
     * Metodo que rota a la izquierda.
     * @param n Nodo que sera rotado.
     * @return Nodo resulante.
     */
    private NodoArbol<T> rotacionIzq(NodoArbol<T> n){
	NodoArbol<T> temp = n.getDerNodo();
	n.setDerNodo(temp.getIzqNodo());
	if(temp.getIzqNodo() != null)
	    temp.getIzqNodo().setPadre(n);
	temp.setIzqNodo(n);
	if(n.getPadre() == null)
	    temp.setPadre(null);
	else {
	    temp.setPadre(n.getPadre());
	    if(n.esHijoIzq())
		n.getPadre().setIzqNodo(temp);
	    else
		n.getPadre().setDerNodo(temp);
	}
	n.setPadre(temp);
	actualizaBF(n);
	actualizaBF(temp);
	return temp;
    }

    /**
     * Metodo que rota a la derecha.
     * @param n Nodo que sera rotado.
     * @return Nodo resulante.
     */
    private NodoArbol<T> rotacionDer(NodoArbol<T> n){
	NodoArbol<T> temp = n.getIzqNodo();
	n.setIzqNodo(temp.getDerNodo());
	if(temp.getDerNodo() != null)
	    temp.getDerNodo().setPadre(n);
	temp.setDerNodo(n);
	if(n.getPadre() == null)
	    temp.setPadre(null);
	else {
	    temp.setPadre(n.getPadre());
	    if(n.esHijoIzq())
		n.getPadre().setIzqNodo(temp);
	    else
		n.getPadre().setDerNodo(temp);
	}
	n.setPadre(temp);
	actualizaBF(n);
	actualizaBF(temp);
	return temp;
    }

    /**
     * Metodo de doble rotacion izq-der.
     * @param n Nodo que sera rotado.
     * @return Nodo resulante.
     */
    private NodoArbol<T> rotacionLR(NodoArbol<T> n) {
	n.setIzqNodo(rotacionIzq(n.getIzqNodo()));
	return rotacionDer(n);
    }

    /**
     * Metodo de doble rotacion der-izq.
     * @param n Nodo que sera rotado.
     * @return Nodo resulante.
     */
    private NodoArbol<T> rotacionRL(NodoArbol<T> n) {
	n.setDerNodo(rotacionDer(n.getDerNodo()));
	return rotacionIzq(n);
    }

    /**
     * Metodo que devuelve el nodo con el valor minimo.
     * @param raiz Nodo desde el que se empieza a revisar.
     * @return El valor minimo del arbol.
     */
    private T minVal(NodoArbol<T> raiz) {
	if(raiz == null)
	    return null;
	if(raiz.getIzqNodo() == null)
	    return raiz.getData();
	else
	    return minVal(raiz.getIzqNodo());
    }

    /**
     * Metodo que comprueba si el nodo es una hoja.
     * @param n Nodo que se revisa.
     * @return true si no tiene hijos, false en otro caso.
     */
    private boolean esHoja(NodoArbol<T> n) {
    	if(n == null)
	    return false;
    	boolean b = false;
    	if(n.getIzqNodo() == null && n.getDerNodo() == null)
	    b = true;
    	return b;
    }

    /**
     * Metodo que devuelve la altura del nodo raiz.
     * @return Altura del nodo raiz.
     */
    public int altura() {
	return altura(raiz);
    }

    /**
     * Metodo que devuelve la altura de cierto nodo.
     * @param raiz Nodo del que se quiere saber la altura.
     * @return Altura del nodo.
     */
    private int altura(NodoArbol<T> raiz) {
    	if(raiz == null)
	    return 0;
    	return 1 + Math.max(altura(raiz.getIzqNodo()), altura(raiz.getDerNodo()));
    }

    @Override
    public String toString() {
	String s = "";
	if(raiz == null)
	    return s;

	Cola<NodoArbol<T>> cola = new Cola<>();
	cola.encolar(raiz);
	cola.encolar(null);

	while(!cola.estaVacia()) {
	    NodoArbol<T> actual = cola.desencolar();
	    if(actual == null) {
		s += "-> ";
		if(cola.estaVacia())
		    break;
		else
		    cola.encolar(null);
	    } else {
		s += "[" + actual.getData() + "] ";
		if(actual.getIzqNodo() != null)
		    cola.encolar(actual.getIzqNodo());

		if(actual.getDerNodo() != null)
		    cola.encolar(actual.getDerNodo());
	    }
	}
	return s.substring(0, s.length()-4);
    }

    /**
     * Clase interna NodoArbol que implementa el nodo de un arbol AVL.
     * @author Ugalde Ubaldo, Fernando.
     * @version 3.0
     *
     * @param <E> El tipo de elementos que tiene el Nodo.
     */
    public class NodoArbol<E> {
	private E data;
	private int bf; //Factor de balance del nodo.
	private NodoArbol<E> padre;
	private NodoArbol<E> izqNodo;
	private NodoArbol<E> derNodo;

	/**
	 * Metodo constructor de NodoArbol
	 * @param data Valor que tendrá el nodo.
	 */
	public NodoArbol(E data){
	    this.data = data;
	    bf = 0;
	    padre = null;
	    izqNodo = null;
	    derNodo = null;
	}

	/**
	 * Metodo getter del valor del nodo.
	 * @return Valor del nodo.
	 */
	public E getData() {
	    return data;
	}

	/**
	 * Metodo setter del valor del nodo.
	 * @param data Valor del nodo.
	 */
	public void setData(E data) {
	    this.data = data;
	}

	/**
	 * Metodo getter del padre del nodo.
	 * @return padre del nodo.
	 */
	public NodoArbol<E> getPadre() {
	    return padre;
	}

	/**
	 * Metodo setter del padre del nodo.
	 * @param padre Padre del nodo.
	 */
	public void setPadre(NodoArbol<E> padre) {
	    this.padre = padre;
	}

	/**
	 * Metodo getter del hijo izquierdo del nodo.
	 * @return Hijo izquierdo del nodo.
	 */
	public NodoArbol<E> getIzqNodo() {
	    return izqNodo;
	}

	/**
	 * Metodo setter del hijo izquierdo del nodo.
	 * @param izqNodo Hijo izquierdo del nodo.
	 */
	public void setIzqNodo(NodoArbol<E> izqNodo) {
	    this.izqNodo = izqNodo;
	}

	/**
	 * Metodo getter del hijo derecho del nodo.
	 * @return Hijo derecho del nodo.
	 */
	public NodoArbol<E> getDerNodo() {
	    return derNodo;
	}

	/**
	 * Metodo setter del hijo derecho del nodo.
	 * @param derNodo Hijo derecho del nodo.
	 */
	public void setDerNodo(NodoArbol<E> derNodo) {
	    this.derNodo = derNodo;
	}

	/**
	 * Metodo getter del factor de balance del nodo.
	 * @return Factor de balance del nodo.
	 */
	public int getBF() {
	    return bf;
	}

	/**
	 * Metodo setter del factor de balance del nodo.
	 * @param bf Factor de balance del nodo.
	 */
	public void setBF(int bf) {
	    this.bf = bf;
	}

	/**
	 * Metodo que comprueba si el nodo tiene hijo izquierdo.
	 * @return true Si tiene hijo izquierdo, false en otro caso.
	 */
	public boolean hayNodoIzq() {
	    if(izqNodo == null)
		return false;
	    return true;
	}

	/**
	 * Metodo que comprueba si el nodo tiene hijo derecho.
	 * @return true Si tiene hijo derecho, false en otro caso.
	 */
	public boolean hayNodoDer() {
	    if(derNodo == null)
		return false;
	    return true;
	}

	/**
	 * Metodo que comprueba si el nodo es hijo izquierdo de su padre.
	 * @return true si sí es su hijo derecho, false en otro caso.
	 */
	public boolean esHijoIzq() {
	    if(padre.izqNodo == null)
		return false;
	    return padre.izqNodo.equals(this);
	}
    }
}
