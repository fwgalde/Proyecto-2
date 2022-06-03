package edd.gui;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 * Clase que implementa el frame que tendrán las gráficas.
 * @author Ugalde Ubaldo, Fernando.
 * @version 2.0.
 */
@SuppressWarnings("serial")
public class FrameGraficas extends JFrame{
    Border borde = BorderFactory.createLineBorder(Color.white,3);

    /**
     * Metodo constructor que devuelve el frame de una grafica sin ordernar.
     * @param graph Grafica desordenada.
     */
   FrameGraficas(Grafica graph){
	this.setTitle("Grafica desordeanda");
	this.setResizable(false);
	this.add(graph);
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

    /**
     * Metodo constructor que devuelve el frame de una grafica ordenada.
     * @param graph Grafica ordenada.
     */
    FrameGraficas(GraficaOrd graph){
	this.setTitle("Grafica ordeanda");
	this.setResizable(false);
	this.add(graph);
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

    /**
     * Calcula el valor maximo de un arreglo
     * @param a Arreglo a evaluar.
     * @return El valor maximo del arreglo.
     */
    public static int maximo(int[] a) {
	int max = 0;
	for(int i = 0; i < a.length; i++) {
	    if(max < a[i])
		max = a[i];
	}
	return max;
    }

    /**
     * Metodo que dibuja la cuadricula de una grafica.
     * @param g2D Grafica a la cual le queremos agregar la cuadricula.
     * @param maximo Valor maximo del arreglo de edades.
     * @return La cuadricula dibujada.
     */
    public static Graphics2D dibujaCuadricula(Graphics2D g2D, int maximo){

	// Titulo
	g2D.setColor(Color.BLACK);
	g2D.setFont(new Font("Verdana", Font.BOLD, 24));
	g2D.drawString("Distribucion de alumnos", 135,70);

	//Titulo eje horizontal
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Verdana", Font.BOLD, 14));
	g2D.drawString("Edad", 285,560);

	// Titulo eje vertical
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Verdana", Font.BOLD, 14));
	g2D.drawString("Cantidad", 15,520);

	// Lineas verticales
	g2D.setColor(Color.BLACK);
	g2D.drawLine(150,100,150,500);
	g2D.drawLine(200,100,200,500);
	g2D.drawLine(250,100,250,500);
	g2D.drawLine(300,100,300,500);
	g2D.drawLine(350,100,350,500);
	g2D.drawLine(400,100,400,500);
	g2D.drawLine(450,100,450,500);

	// Lineas horizontales
	g2D.drawLine(100,150,500,150);
	g2D.drawLine(100,200,500,200);
	g2D.drawLine(100,250,500,250);
	g2D.drawLine(100,300,500,300);
	g2D.drawLine(100,350,500,350);
	g2D.drawLine(100,400,500,400);
	g2D.drawLine(100,450,500,450);

	// Lineas extremas de la cuadricula.
	g2D.drawLine(100,100,500,100); //H
	g2D.drawLine(100,500,500,500);
	g2D.drawLine(100,100,100,500); //V
	g2D.drawLine(500,100,500,500);

	// Lineas que marcan mitades.
	g2D.setStroke(new BasicStroke(2));
	g2D.drawLine(93,100,100,100);
	g2D.setStroke(new BasicStroke(1));
	g2D.drawLine(97,200,100,200);
	g2D.setStroke(new BasicStroke(2));
	g2D.drawLine(95,300,100,300);
	g2D.setStroke(new BasicStroke(1));
	g2D.drawLine(97,400,100,400);
	g2D.setStroke(new BasicStroke(2));
	g2D.drawLine(93,500,100,500);
	g2D.setStroke(new BasicStroke(1));

	// Rotulo eje Y
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(String.valueOf(maximo), 75, 100);

	double maximoD = maximo*100;
	double intermedios = maximoD*3/400;
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(String.valueOf(intermedios), 75, 200);

	intermedios = maximoD/200;
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(String.valueOf(intermedios), 75, 300);

	intermedios = maximoD/400;
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString(String.valueOf(intermedios), 75, 400);

	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString("0", 75, 500);

	return g2D;
    }
}
