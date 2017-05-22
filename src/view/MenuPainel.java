package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MenuPainel extends JPanel implements ActionListener{

	private Image fundo;
	private Timer timer;
	private int escolha;

	private KeyListener kl;
	
	private boolean mostrar;

	public MenuPainel() {

		setFocusable(true);
		setDoubleBuffered(true);
		kl = new TecladoAdapter();
		addKeyListener(kl);
		ImageIcon referencia = new ImageIcon("res\\fundo.png");
		fundo = referencia.getImage();

		mostrar = true;
		escolha = 0;

		timer = new Timer(5, this);
		timer.start();

	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);

		if (mostrar) {

			graficos.setColor(Color.WHITE);
			graficos.setFont(new Font("castellar", Font.PLAIN, 20));
			graficos.drawString("1 - Iniciar", 180, 150);
			graficos.drawString("2 - Créditos ( Em desenvolvimento )", 30, 180);
		}else{
			timer.stop();
		}
		
		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("menu");
		repaint();
	}
	
	public boolean isMostrar() {
		return mostrar;
	}
	
	public int getEscolha() {
		return escolha;
	}
	
	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	
	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			mostrar = false;
			escolha = e.getKeyCode();
			timer.stop();
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

}
