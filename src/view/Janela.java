package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Janela extends JPanel implements ActionListener{
	
	private static final int DELAY = 5;
	private Image fundo;
	private Timer timer;
	private Estado estado;

	public Janela() {
		estado = new Menu();

		setFocusable(true);
		setDoubleBuffered(true);

		new ContainerDeJanelas(this);
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				estado.keyReleased(e);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				estado.keyPressed(e);
			}
		});
		
		ImageIcon referencia = new ImageIcon("res\\fundo_2.jpg");
		fundo = referencia.getImage();
		
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		estado.paint(graficos);
		g.dispose();
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		estado.actionPerformed(e);
		if(!estado.isValido())
			estado = estado.getProximoEstado();
		repaint();		
	}

}
