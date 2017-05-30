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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Boss;
import model.Missel;
import model.Nave;

public class Chefao extends JPanel implements ActionListener {

	private Image fundo;
	private Nave naveBoss;
	private Boss boss;
	private int vidaBoss = 100;
	private Timer timer;

	public Chefao(Nave nave) {
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon referencia = new ImageIcon("res\\fundo_2.jpg");
		fundo = referencia.getImage();

		new ContainerDeJanelas(this);
		
		this.naveBoss = new Nave();

		addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				naveBoss.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				naveBoss.keyPressed(e);

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});

		
		this.boss = new Boss();

		timer = new Timer(5, this);
		timer.start();

	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);

		graficos.drawImage(naveBoss.getImagem(), naveBoss.getX(), naveBoss.getY(), this);

		List<Missel> misseis = naveBoss.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

		}

		List<Missel> misseisBoss = boss.getMisseis();

		for (int i = 0; i < misseisBoss.size(); i++) {

			Missel m = (Missel) misseisBoss.get(i);
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

		}

		graficos.drawImage(boss.getImagem(), boss.getX(), boss.getY(), this);

		graficos.setColor(Color.WHITE);
		graficos.drawString("Vida Boss: " + vidaBoss + "%", 5, 15);

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (vidaBoss == 0) {
			new Vitoria();
			timer.stop();
		}

		List<Missel> misseis = naveBoss.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);

			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}

		}

		List<Missel> misseisBoss = boss.getMisseis();

		for (int i = 0; i < misseisBoss.size(); i++) {

			Missel m = (Missel) misseisBoss.get(i);

			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseisBoss.remove(i);
			}

		}

		if (naveBoss.getY() == boss.getY() + boss.getAltura() / 2) {
			boss.atirar();
		}

		naveBoss.mexer();
		boss.mexer();
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {

		Rectangle formaNave = naveBoss.getBounds();
		Rectangle formaBoss = boss.getBounds();
		Rectangle formaMissel;

		if (formaNave.intersects(formaBoss)) {
			new Derrota();
			timer.stop();
		}

		List<Missel> misseis = naveBoss.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			if (formaMissel.intersects(formaBoss)) {
				Missel missel = misseis.get(i);
				missel.setVisivel(false);
				vidaBoss -= 5;

			}

		}

		List<Missel> misseisBoss = boss.getMisseis();

		for (int i = 0; i < misseisBoss.size(); i++) {

			Missel tempMissel = misseisBoss.get(i);
			formaMissel = tempMissel.getBounds();

			if (formaMissel.intersects(formaNave)) {

				new Derrota();
				timer.stop();
			}

		}

	}
}
