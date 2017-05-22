package view;


import java.awt.Component;

import javax.swing.JFrame;

public class ContainerDeJanelas extends JFrame{
	
	public ContainerDeJanelas(Component inicial){
		setTitle("Nave");
		getContentPane().add(inicial);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,420);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void trocarTela(Component novo){
		getContentPane().removeAll();
        getContentPane().add( novo );
		revalidate();
		repaint();
	}
	
}
