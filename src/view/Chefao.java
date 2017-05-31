package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import model.Boss;
import model.Missel;
import model.Nave;

public class Chefao implements Estado{

	
	private Nave nave;
	private Boss boss;
	private boolean valido;
	private Estado proximoEstado;
	
	public Chefao(Nave nave) {
		this.nave = nave;
		this.boss = new Boss();
		this.valido = true;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (boss.getVida() == 0) {
			proximoEstado = new Vitoria();
			valido = false;
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

		List<Missel> misseisBoss = boss.getMisseis();

		for (int i = 0; i < misseisBoss.size(); i++) {

			Missel m = (Missel) misseisBoss.get(i);

			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseisBoss.remove(i);
			}

		}

		if (nave.getY() == boss.getY() + boss.getAltura() / 2) {
			boss.atirar();
		}

		nave.mexer();
		boss.mexer();
		checarColisoes();
	}

	public void checarColisoes() {

		Rectangle formaNave = nave.getBounds();
		Rectangle formaBoss = boss.getBounds();
		Rectangle formaMissel;

		if (formaNave.intersects(formaBoss)) {
			nave.dano(25);
			if (nave.getVida() <= 0) {
				proximoEstado = new Derrota();
				valido = false;
			}
		}

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			if (formaMissel.intersects(formaBoss)) {
				Missel missel = misseis.get(i);
				missel.setVisivel(false);
				boss.dano(nave.getForca());
			}

		}

		List<Missel> misseisBoss = boss.getMisseis();

		for (int i = 0; i < misseisBoss.size(); i++) {

			Missel tempMissel = misseisBoss.get(i);
			formaMissel = tempMissel.getBounds();

			if (formaMissel.intersects(formaNave)) {
				nave.dano(25);
				misseisBoss.get(i).setVisivel(false);
				if (nave.getVida() <= 0) {
					proximoEstado = new Derrota();
					valido = false;
				}			
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		nave.keyReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		nave.keyPressed(e);
		
	}

	@Override
	public void paint(Graphics2D graficos) {
		graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), null);

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), null);

		}

		List<Missel> misseisBoss = boss.getMisseis();

		for (int i = 0; i < misseisBoss.size(); i++) {

			Missel m = (Missel) misseisBoss.get(i);
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), null);

		}

		graficos.drawImage(boss.getImagem(), boss.getX(), boss.getY(), null);

		graficos.setColor(Color.WHITE);
		graficos.drawString("Vida Boss: " + boss.getVida() + "%", 5, 15);
		graficos.drawString("Vida: "+nave.getVida()+"%", 5, 35);
		graficos.drawString("Força: "+nave.getForca()+"%", 5, 55);
		
	}

	@Override
	public Estado getProximoEstado() {
		return proximoEstado;
	}

	@Override
	public boolean isValido() {
		return valido;
	}
}
