package view;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class ContainerDeJanelas extends JFrame {
	
	public static final int ALTURA_TELA = 400;
	public static final int LARGURA_TELA = 500;


	public ContainerDeJanelas(Component componente) {
		setTitle("Nave");
		getContentPane().add(componente);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LARGURA_TELA,ALTURA_TELA);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	
	
	
}
