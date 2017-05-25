package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
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

	private Estado estado;
	private HashMap<Integer, Estado> estados = new HashMap<Integer, Estado>();

	private List<Inimigo> inimigos;

	private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { -780, 109 }, { 580, 139 }, { 880, 239 },
			{ 790, 259 }, { -760, 50 }, { 790, 150 }, { -1980, 209 }, { 560, 45 }, { 510, 70 }, { 930, 159 }, { -590, 80 },
			{ -530, 60 }, { 940, 59 }, { -990, 30 }, { -920, 200 }, { 900, 259 }, { -660, 50 }, { -540, 90 }, { 810, 220 },
			{ -860, 20 }, { 740, 180 }, { 820, 128 }, { -490, 170 }, { 700, 30 }, { 920, 300 }, { -856, 328 },
			{ -456, 320 } };

	public Fase() {

		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter2());

		ImageIcon referencia = new ImageIcon("res\\fundo_2.jpg");
		fundo = referencia.getImage();

		estados.put(KeyEvent.VK_ENTER, new EmMenu());

		estados.put(KeyEvent.VK_NUMPAD1, new EmJogo(nave, inimigos, this));

		estado = estados.get(KeyEvent.VK_ENTER);

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

		estado.desenha(graficos);

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (estado.getClass() == EmJogo.class) {
			if (inimigos.size() == 0) {
				estado = new EmVitoria();
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
		}
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

				nave.setVisivel(false);
				tempInimigo.setVisivel(false);

				estado = new EmDerrota();
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

	private class TecladoAdapter2 extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (estado.getClass() == EmDerrota.class || estado.getClass() == EmVitoria.class) {
				if (estados.containsKey(e.getKeyCode()))
					estado = estados.get(e.getKeyCode());
			} else if (estado.getClass() == EmMenu.class) {
				if (estados.containsKey(e.getKeyCode())) {
					estado = estados.get(e.getKeyCode());
					EmJogo emJogo = (EmJogo) estado;
					nave = new Nave();
					emJogo.setNave(nave);
					inicializaInimigos();
					emJogo.setInimigos(inimigos);
				}

			} else if (estado.getClass() == EmJogo.class) {
				nave.keyPressed(e);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (estado.getClass() == EmJogo.class) {
				nave.keyReleased(e);
			}
		}

	}

}
