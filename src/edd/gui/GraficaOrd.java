package edd.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JPanel;

/**
 * Clase que dibuja la grafica ordenada de la distribucion de alumnos por edad.
 * @author Ugalde Ubaldo, Fernando.
 * @version 3.0
 */
@SuppressWarnings("serial")
public class GraficaOrd extends JPanel{
    private int edades[];
    private int edadesOrdenadas[];

    /**
     * Metodo constructor de la clase.
     * @param edades Arreglo con la cantidad de alumnos por edad.
     */
    GraficaOrd(int[] edades){
	this.setPreferredSize(new Dimension(600,600));
	this.edades = edades;
	edadesOrdenadas = new int[8];
    }

    @Override
    public void paint(Graphics g) {
	Graphics2D g2D = (Graphics2D) g;

	ordenaEdades();
	int maximo = FrameGraficas.maximo(edadesOrdenadas);
	g2D = FrameGraficas.dibujaCuadricula(g2D, maximo);

	// Rotulado eje X
	int space = 520;

	int[]aux = edades.clone();

	int val = oldIndex(aux,0);
	Integer valor = obtenEdad(val);
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(valor.toString(), 120,space);

	val = oldIndex(aux,1);
	valor = obtenEdad(val);
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(valor.toString(), 170,space);

	val = oldIndex(aux,2);
	valor = obtenEdad(val);
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(valor.toString(), 220,space);

	val = oldIndex(aux,3);
	valor = obtenEdad(val);
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(valor.toString(), 270,space);

	val = oldIndex(aux,4);
	valor = obtenEdad(val);
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(valor.toString(), 320,space);

	val = oldIndex(aux,5);
	valor = obtenEdad(val);
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(valor.toString(), 370,space);

	val = oldIndex(aux,6);
	valor = obtenEdad(val);
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(valor.toString(), 420,space);

	val = oldIndex(aux,7);
	valor = obtenEdad(val);
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(valor.toString(), 470,space);

	Random aleatorios = new Random();
	if(maximo != 0) {
	    int[] proporciones = new int[8];

	    for(int i = 0; i < edadesOrdenadas.length; i++)
		proporciones[i] = edadesOrdenadas[i]*400/maximo;

	    // Dibujamos las barras de acuerdo a su proporcion.
	    for(int i = 0; i < proporciones.length; i++) {
		int r = aleatorios.nextInt(256);
		int gr = aleatorios.nextInt(256);
		int b = aleatorios.nextInt(256);
		g2D.setColor(new Color(r,gr,b));
		if((500 - proporciones[i]) == 0) {
		    g2D.fillRect(100 + 50*i,100,50,proporciones[i]);
		    continue;
		}
		g2D.fillRect(100 + 50*i,500-proporciones[i],50,proporciones[i]);
	    }
	}
    }

    /**
     * Metodo que ordena el arreglo de edades y lo pone en edadesOrdenadas.
     */
    public void ordenaEdades() {
	edadesOrdenadas = edades.clone();
	quickSort(edadesOrdenadas);
    }

    /**
     * Metodo que intercambia lugares en un arreglo.
     * @param a El arreglo que queremos modificar.
     * @param i El indice que sera intercambiado por otro.
     * @param j El otro indice que sera intercambiado.
     */
    public static void intercambia(int[] a, int i, int j) {
	int k = a[i];
	int m = a[j];
	a[i] = m;
	a[j] = k;
    }

    /**
     * Metodo principal quickSort que ordena el arreglo.
     * @param arreglo Arreglo que sera ordenado.
     */
    public static void quickSort(int[] arreglo) {
	// Ordenamos el arreglo completo
	quickSort(arreglo, 0, arreglo.length-1);
    }

    /**
     * Metodo auxiliar recursivo para quickSort.
     * @param arreglo Arreglo que sera ordenado.
     * @param inicio Inicio del arreglo
     * @param fin Fin del arreglo
     */
    private static void quickSort(int[] arreglo, int inicio, int fin){
	int pivote = arreglo[inicio];
	int i = inicio + 1;
	int j = fin;

	while(i < j) {
	    if (arreglo[i] <= pivote) {
		i++;
	    } else if(arreglo[j] > pivote && arreglo[i] > pivote) {
		j--;
	    } else if(arreglo[i] > pivote && arreglo[j] <= pivote)
		intercambia(arreglo, i, j);
	}
	if (arreglo[i] > pivote)
	    i--;
	intercambia(arreglo, inicio, i);
	if(inicio < i-1)
	    quickSort(arreglo, inicio, i-1);
	if(i+1 < fin)
	    quickSort(arreglo, i+1, fin);
    }

    /**
     * Metodo que devuelve la edad de acuerdo a su posicion en el arreglo principal de edades.
     * @param n Posicion en el arreglo edades
     * @return La edad del alumno.
     */
    public int obtenEdad(int n) {
	if(n == 0) {
	    return 18;
	} else if(n == 1) {
	    return 19;
	} else if(n == 2) {
	    return 20;
	} else if(n == 3) {
	    return 21;
	} else if(n == 4) {
	    return 22;
	} else if(n == 5) {
	    return 23;
	} else if(n == 6) {
	    return 24;
	} else if(n == 7) {
	    return 25;
	}
	return -1;
    }

    /**
     * Metodo que devuelve que indice tenia el elemento dentro del arreglo sin ordenar.
     * @param edades Arreglo original sin ordenar.
     * @param index Indice del cual se quiere obtener su indice anterior.
     * @return Indice anterior de index.
     */
    public int oldIndex(int[] edades, int index) {
	int valor = 0;
	for(int i = 0; i < edades.length; i++) {
	    if(edades[i] == edadesOrdenadas[index]) {
		edades[i] = -1;
		return valor = i;
	    }
	}
	return valor;
    }
}
