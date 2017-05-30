package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Menu extends JPanel {

	private Image fundo;

	public Menu() {
		setFocusable(true);
		setDoubleBuffered(true);
		ImageIcon referencia = new ImageIcon("res\\fundo_2.jpg");

		fundo = referencia.getImage();

		new ContainerDeJanelas(this);
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					new Fase();
					
				}

			}
		});

	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		graficos.setColor(Color.WHITE);
		graficos.setFont(new Font("castellar", Font.PLAIN, 20));
		graficos.drawString("1 - Iniciar", 180, 150);
		graficos.drawString("2 - Créditos ( Em desenvolvimento )", 30, 180);
		g.dispose();
	}

}
