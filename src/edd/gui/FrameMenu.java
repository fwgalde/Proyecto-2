package edd.gui;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * Clase que implementa el frame que despliega el menu principal.
 * @author Ugalde Ubaldo, Fernando.
 * @version 1.0
 */
@SuppressWarnings("serial")
public class FrameMenu extends JFrame {
    public FrameMenu() {
	this.setVisible(true);
	this.setTitle("Proyecto 2");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setSize(700,700);
	this.setLocationRelativeTo(null);
	this.getContentPane().setBackground(new Color(0,0,0));
    }
}
