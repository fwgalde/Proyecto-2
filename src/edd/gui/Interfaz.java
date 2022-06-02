package edd.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import edd.util.BaseDeDatos;

/**
 * Clase que principal que invoca la interfaz de usuario.
 * @author Ugalde Ubaldo, Fernando.
 * @version 2.0
 */
public class Interfaz{
    public static void main(String[] args) {
	BaseDeDatos alumnos = new BaseDeDatos(); // Base de datos de los alumnos registrados.

	// Boton que activa la opcion de registrar alumnos.
	JButton op1 = new JButton();
	op1.setBounds(500,100,100,50);
	op1.setPreferredSize(new Dimension(200, 40));
	op1.setText("1. Registra alumno");
	op1.addActionListener(e -> {
		try {
		    int cuenta = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el numero de cuenta del alumno", "Registro", JOptionPane.QUESTION_MESSAGE));

		    // Conidicional para limitar los numeros de cuenta.
		    if(cuenta < 300000000 || cuenta > 500000000) {
			JOptionPane.showMessageDialog(null,"El numero de cuenta debe estar en un intervalo de 300000000-500000000", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			throw new IllegalArgumentException();
		    }

		    // Condicional para evitar que se repitan los numeros de cuenta.
		    if(alumnos.getHashing().containsKey(cuenta)) {
			JOptionPane.showMessageDialog(null,"Ya ha ingresado este numero de cuenta previamente", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			throw new IllegalArgumentException();
		    }
		    int edad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la edad del alumno", "Registro", JOptionPane.QUESTION_MESSAGE));

		    // Condicional para limitar la edad.
		    if(edad < 18 || edad > 25) {
			JOptionPane.showMessageDialog(null,"La edad debe estar en un intervalo de 18-25", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			throw new IllegalArgumentException();
		    }
		    alumnos.registraAlumno(cuenta, edad);

		    JOptionPane.showMessageDialog(null, "Se ha registrado correctamente al alumno.", "Registro completado", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception exception) {
		    JOptionPane.showMessageDialog(null,"Ha ingresado un valor erroneo.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	    }
	    );

	// Boton que despliega la grafica de alumnos.
	//(Por favor cerrar la ventana de la grafica antes de volver a usar el menu.)
	JButton op2 = new JButton();
	op2.setBounds(500,100,100,50);
	op2.setPreferredSize(new Dimension(200, 40));
	op2.setText("2. Mostrar distribucion de alumnos por edades.");
	op2.addActionListener(e -> {
		alumnos.actualizaGrafica();
		new FrameGraficas(new Grafica(alumnos.getEdades()));
		alumnos.setNuevosAlumnos(0);
	    }
	    );

	// Boton que despliega la grafica de alumnos ordenada.
	//(Por favor cerrar la ventana de la grafica antes de volver a usar el menu.)
	JButton op3 = new JButton();
	op3.setBounds(500,100,100,50);
	op3.setPreferredSize(new Dimension(200, 40));
	op3.setText("3. Mostrar grafica ordenada por edades.");
	op3.addActionListener(e -> {
		alumnos.actualizaGrafica();
		new FrameGraficas(new GraficaOrd(alumnos.getEdades()));
		alumnos.setNuevosAlumnos(0);
	    }
	    );

	// Boton que despliega la representacion como lista del arbol que contiene los numeros de cuenta.
	JButton op4 = new JButton();
	op4.setBounds(500,100,100,50);
	op4.setPreferredSize(new Dimension(200, 40));
	op4.setText("4. Imprimir arbol de alumnos");
	op4.addActionListener(e -> alumnos.regresaArbolAsociado());

	// Boton que sale del programa.
	JButton op5 = new JButton();
	op5.setBounds(500,100,100,50);
	op5.setPreferredSize(new Dimension(200, 40));
	op5.setText("5. Salir.");
	op5.addActionListener(e -> System.exit(0));

	//Panel superior
	JLabel titulo = new JLabel();
	titulo.setText("Proyecto 2");
	titulo.setForeground(new Color(255,255,255));
	titulo.setHorizontalTextPosition(JLabel.CENTER);
	titulo.setVerticalTextPosition(JLabel.CENTER);
	titulo.setFont(new Font("Verdana", Font.BOLD, 48));
	titulo.setVerticalAlignment(JLabel.TOP);
	titulo.setHorizontalAlignment(JLabel.CENTER);
	JPanel encabezado = new JPanel();
	encabezado.setBackground(new Color(1,32,44));
	encabezado.setPreferredSize(new Dimension(75, 75));
	encabezado.add(titulo);


	// Panel central
	JLabel menu = new JLabel();
	menu.setText("Menu de opciones");
	menu.setForeground(new Color(255,255,255));
	menu.setHorizontalTextPosition(JLabel.CENTER);
	menu.setVerticalTextPosition(JLabel.TOP);
	menu.setFont(new Font("Verdana", Font.BOLD, 16));
	menu.setVerticalAlignment(JLabel.TOP);
	menu.setHorizontalAlignment(JLabel.CENTER);
	JPanel subtitulo = new JPanel();
	subtitulo.setBackground(new Color(0,65,92));
	subtitulo.setPreferredSize(new Dimension(400,25));
	subtitulo.add(menu);
	JPanel space = new JPanel();
	space.setBackground(new Color(0,65,92));
	space.setPreferredSize(new Dimension(400,15));
	JPanel buttons = new JPanel();
	buttons.setPreferredSize(new Dimension(400, 400));
	buttons.setBackground(new Color(0,65,92));
	buttons.setLayout(new GridLayout(5,1,10,50));
	buttons.add(op1);
	buttons.add(op2);
	buttons.add(op3);
	buttons.add(op4);
	buttons.add(op5);
	JPanel cuerpo = new JPanel();
	cuerpo.setPreferredSize(new Dimension(400, 500));
	cuerpo.setBackground(new Color(0,65,92));
	cuerpo.add(subtitulo, BorderLayout.NORTH);
	cuerpo.add(space,BorderLayout.CENTER);
	cuerpo.add(buttons, BorderLayout.SOUTH);

	//Panel inferior
	JLabel autor = new JLabel();
	autor.setText("Creado por: Fernando Ugalde Ubaldo.");
	autor.setForeground(new Color(255,255,255));
	autor.setVerticalTextPosition(JLabel.BOTTOM);
	autor.setFont(new Font("Verdana", Font.ITALIC, 12));
	autor.setHorizontalAlignment(JLabel.RIGHT);
	autor.setVerticalAlignment(JLabel.BOTTOM);
	autor.setHorizontalTextPosition(JLabel.RIGHT);
	JPanel pie = new JPanel();
	pie.setPreferredSize(new Dimension(75,75));
	pie.setBackground(new Color(1,32,44));
	pie.add(autor);

	//Frame que contiene a todo.
	FrameMenu frame = new FrameMenu();
	frame.setLayout(new BorderLayout(0,3));
	frame.add(encabezado,BorderLayout.NORTH);
	frame.add(cuerpo, BorderLayout.CENTER);
	frame.add(pie, BorderLayout.SOUTH);
	ImageIcon imagen = new ImageIcon("F7.jpg");
	frame.setIconImage(imagen.getImage());
    }
}
