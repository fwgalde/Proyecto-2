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
 * Clase que dibuja la grafica sin ordernar de la distribucion de alumnos por edad.
 * @author Ugalde Ubaldo, Fernando.
 * @version 3.0
 */
@SuppressWarnings("serial")
public class Grafica extends JPanel{
    private int edades[];

    /**
     * Metodo constructor de la clase.
     * @param edades Arreglo con la cantidad de alumnos por edad.
     */
    Grafica(int[] edades){
	this.setPreferredSize(new Dimension(600,600));
	this.edades = edades;
    }

    @Override
    public void paint(Graphics g) {
	Graphics2D g2D = (Graphics2D) g;

	int maximo = FrameGraficas.maximo(edades);
	g2D = FrameGraficas.dibujaCuadricula(g2D, maximo);

	int space = 520;

	// Rotulado eje X
	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString("18", 120,space);

	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString("19", 170,space);

	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString("20", 220,space);

	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString("21", 270,space);

	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString("22", 320,space);

	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString("23", 370,space);

	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString("24", 420,space);

	g2D.setColor(Color.blue);
	g2D.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
	g2D.drawString("25", 470,space);

	Random aleatorios = new Random();
	if(maximo != 0) {
	    int[] proporciones = new int[8];

	    for(int i = 0; i < edades.length; i++)
		proporciones[i] = edades[i]*400/maximo;

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
}
