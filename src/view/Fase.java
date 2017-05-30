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
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Direcao;
import model.Inimigo;
import model.Missel;
import model.Nave;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Nave nave;
	private Timer timer;
	private List<Inimigo> inimigos;

	private ContainerDeJanelas containerDeJanelas;
	private HashMap<Integer, Direcao> direcoesInimigo;
	private int countInimigo = 10;
	private int inimigosRestantes = 10;

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

			}
		});

		ImageIcon referencia = new ImageIcon("res\\fundo_2.jpg");
		fundo = referencia.getImage();

		nave = new Nave();
		
		direcoesInimigo = new HashMap<Integer, Direcao>();
		direcoesInimigo.put(0, Direcao.BAIXO);
		direcoesInimigo.put(1, Direcao.CIMA);
		direcoesInimigo.put(2, Direcao.DIREITA);
		direcoesInimigo.put(3, Direcao.ESQUERDA);
		
		inicializaInimigos();

		timer = new Timer(5, this);
		timer.start();

	}

	public void inicializaInimigos() {

		inimigos = new ArrayList<Inimigo>();

		for (int i = 0; i < countInimigo; i++) {
			int j = new Random().nextInt(3);
			inimigos.add(new Inimigo(direcoesInimigo.get(j)));
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
			new Chefao(this.nave);
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