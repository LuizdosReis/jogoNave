package view;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class ContainerDeJanelas extends JFrame {

	public ContainerDeJanelas(Component componente) {
		setTitle("Nave");
		getContentPane().add(componente);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 420);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void trocarTela(Component novo) {
		getContentPane().removeAll();
		getContentPane().add(novo);
		repaint();
		revalidate();
	}

}
