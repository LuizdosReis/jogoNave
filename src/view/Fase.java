package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Inimigo;
import model.Missel;
import model.Nave;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Nave nave;
	private Timer timer;
	private List<Inimigo> inimigos;

	private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { -780, 109 }, { 580, 139 }, { 880, 239 },
			{ 790, 259 }, { -760, 50 }, { 790, 150 }, { -1980, 209 }, { 560, 45 }, { 510, 70 }, { 930, 159 },
			{ -590, 80 }, { -530, 60 }, { 940, 59 }, { -990, 30 }, { -920, 200 }, { 900, 259 }, { -660, 50 },
			{ -540, 90 }, { 810, 220 }, { -860, 20 }, { 740, 180 }, { 820, 128 }, { -490, 170 }, { 700, 30 },
			{ 920, 300 }, { -856, 328 }, { -456, 320 } };

	private ContainerDeJanelas containerDeJanelas;

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);

		new ContainerDeJanelas(this);

		addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				nave.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				nave.keyPressed(e);

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		ImageIcon referencia = new ImageIcon("res\\fundo_2.jpg");
		fundo = referencia.getImage();

		nave = new Nave();
		inicializaInimigos();

		timer = new Timer(5, this);
		timer.start();

	}

	public void inicializaInimigos() {

		inimigos = new ArrayList<Inimigo>();

		for (int i = 0; i < coordenadas.length; i++) {
			inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));

		}

	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);

		graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), null);

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), null);

		}

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo in = inimigos.get(i);
			graficos.drawImage(in.getImagem(), in.getX(), in.getY(), null);

		}

		graficos.setColor(Color.WHITE);
		graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 15);

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (inimigos.size() == 0) {
			new Vitoria();
			timer.stop();
		}

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);

			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}

		}

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo in = inimigos.get(i);

			if (in.isVisivel()) {
				in.mexer();
			} else {
				inimigos.remove(i);
			}

		}

		nave.mexer();
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {

		Rectangle formaNave = nave.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMissel;

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo tempInimigo = inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();

			if (formaNave.intersects(formaInimigo)) {
				new Derrota();
				timer.stop();
			}

		}

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {

				Inimigo tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();

				if (formaMissel.intersects(formaInimigo)) {

					tempInimigo.setVisivel(false);
					tempMissel.setVisivel(false);

				}

			}

		}

	}

}