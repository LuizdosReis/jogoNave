package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;

import model.Boss;
import model.ItemForca;
import model.ItemVida;
import model.Missel;
import model.Nave;
import model.Objeto;

public class Chefao implements Estado{

	
	private Nave nave;
	private Boss boss;
	private boolean valido;
	private Estado proximoEstado;
	private Objeto itemVida;
	private Objeto itemForca;
	
	public Chefao(Objeto nave) {
		this.nave = (Nave) nave;
		this.boss = new Boss();
		this.valido = true;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (boss.getVida() <= 0) {
			proximoEstado = new Vitoria();
			valido = false;
		}

		List<Objeto> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);

			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}

		}

		List<Objeto> misseisBoss = boss.getMisseis();

		for (int i = 0; i < misseisBoss.size(); i++) {

			Missel m = (Missel) misseisBoss.get(i);

			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseisBoss.remove(i);
			}

		}
		
		if (nave.getVida() <= 25 && this.itemVida == null) {
			this.itemVida = new ItemVida();
		}

		if (boss.getVida() < 50 && this.itemForca == null && this.nave.getForca() < 100) {
			this.itemForca = new ItemForca();
		}

		nave.mexer();
		boss.mexer();
		
		if (nave.getY() <= (boss.getY()+(boss.getAltura()/2))
				&& nave.getY()+nave.getAltura() >= (boss.getY()+(boss.getAltura()/2))){
			if (boss.podeAtirar()){
				boss.atirar();
			}
		}
		
		checarColisoes();
	}

	public void checarColisoes() {

		Rectangle formaNave = nave.getBounds();
		Rectangle formaBoss = boss.getBounds();
		Rectangle formaItemVida;
		Rectangle formaItemForca;
		Rectangle formaMissel;

		if (formaNave.intersects(formaBoss)) {
			nave.dano(25);
			if (nave.getVida() <= 0) {
				proximoEstado = new Derrota();
				valido = false;
			}
		}

		List<Objeto> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Objeto tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			if (formaMissel.intersects(formaBoss)) {
				Missel missel = (Missel)misseis.get(i);
				missel.setVisivel(false);
				boss.dano(nave.getForca());
			}

		}

		List<Objeto> misseisBoss = boss.getMisseis();

		for (int i = 0; i < misseisBoss.size(); i++) {

			Missel tempMissel = (Missel)misseisBoss.get(i);
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
		
		if (itemVida != null) {
			formaItemVida = itemVida.getBounds();
			if (formaNave.intersects(formaItemVida)) {
				nave.setVida(100);
				itemVida = null;
			}
		}

		if (itemForca != null) {
			formaItemForca = itemForca.getBounds();
			if (formaNave.intersects(formaItemForca)) {
				nave.dobrarForca();
				itemForca = null;
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

		List<Objeto> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), null);

		}

		List<Objeto> misseisBoss = boss.getMisseis();

		for (int i = 0; i < misseisBoss.size(); i++) {

			Missel m = (Missel) misseisBoss.get(i);
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), null);

		}

		if (this.itemVida != null){
			graficos.drawImage(itemVida.getImagem(), itemVida.getX(), itemVida.getY(), null);
		}
		
		if (this.itemForca != null){
			graficos.drawImage(itemForca.getImagem(), itemForca.getX(), itemForca.getY(), null);
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
