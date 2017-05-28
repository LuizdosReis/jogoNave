package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Vitoria extends JPanel {

	private Image fundo;

	public Vitoria() {
		setFocusable(true);
		setDoubleBuffered(true);

		new ContainerDeJanelas(this);

		addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					new Menu();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});

		ImageIcon referencia = new ImageIcon("res\\fundo_2.jpg");
		fundo = referencia.getImage();
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		ImageIcon fimJogo = new ImageIcon("res\\vitoria.png");
		graficos.drawImage(fimJogo.getImage(), 0, 0, null);

	}

}
