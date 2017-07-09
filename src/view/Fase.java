package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import model.Direcao;
import model.Inimigo;
import model.ItemForca;
import model.ItemVida;
import model.Missel;
import model.Nave;
import model.Objeto;
import serializador.Deserializador;
import serializador.Serializador;

public class Fase implements Estado {

	private Nave nave;
	private List<Objeto> inimigos;

	private int QUANTIDADE_INIMIGOS = 10;

	private HashMap<Integer, Direcao> direcoesInimigo; // static
	private boolean valido;
	private Estado proximoEstado;
	private Objeto itemVida;
	private Objeto itemForca;
	private int inimigosFaltantes = 10;
	
	public Fase() {

		nave = new Nave();

		direcoesInimigo = new HashMap<Integer, Direcao>();
		direcoesInimigo.put(0, Direcao.BAIXO);
		direcoesInimigo.put(1, Direcao.CIMA);
		direcoesInimigo.put(2, Direcao.DIREITA);
		direcoesInimigo.put(3, Direcao.ESQUERDA);

		inicializaInimigos();

		valido = true;
		inimigosFaltantes = 10;
	}

	public void inicializaInimigos() {

		inimigos = new ArrayList<Objeto>();

		for (int i = 0; i < QUANTIDADE_INIMIGOS; i++) {
			int j = new Random().nextInt(3);
			inimigos.add(new Inimigo(direcoesInimigo.get(j)));
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//verifica fim do estado
		if (inimigosFaltantes <= 0) {
			proximoEstado = new Chefao(nave);
			valido = false;
		}
		//fim verifica fim do estado

		//atualiza misseis
		List<Objeto> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);

			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}

		}
		
		//fim atualiza misseis

		atualizaInimigos();

		if (nave.getVida() <= 25 && this.itemVida == null) {
			this.itemVida = new ItemVida();
		}

		if (inimigosFaltantes <= 5 && this.itemForca == null && this.nave.getForca() < 100) {
			this.itemForca = new ItemForca();
		}
		
		if (inimigos.size() < 10){
			if (new Date().getTime() % 2 == 0){
				int j = new Random().nextInt(3);
				inimigos.add(new Inimigo(direcoesInimigo.get(j)));
			}
		}

		nave.mexer();
		checarColisoes();
	}

	private void atualizaInimigos() {
		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo in = (Inimigo) inimigos.get(i);

			if (in.isVisivel()) {
				in.mexer();
			} else {
				inimigos.remove(i);
			}

		}
	}

	public void checarColisoes() {

		Rectangle formaNave = nave.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMissel;
		Rectangle formaItemVida;
		Rectangle formaItemForca;

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo tempInimigo = (Inimigo) inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();

			if (formaNave.intersects(formaInimigo)) {
				nave.dano(25);
				inimigos.get(i).setVisivel(false);
				if (nave.getVida() <= 0) {
					proximoEstado = new Derrota();
					valido = false;
				}
			}

		}

		List<Objeto> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Objeto tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {

				Inimigo tempInimigo = (Inimigo) inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();

				if (formaMissel.intersects(formaInimigo)) {

					tempInimigo.setVisivel(false);
					tempMissel.setVisivel(false);
					inimigosFaltantes--;

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
		if(e.getKeyCode() == KeyEvent.VK_F5){
			Serializador serializador = new Serializador();
			try {
				serializador.serializar("C:/Users/Luiz Henrique/Documents/nave/nave",nave);
				serializador.serializar("C:/Users/Luiz Henrique/Documents/inimigos/inimigos", inimigos);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F6){
			Deserializador deserializador = new Deserializador();
			try {
				 nave = (Nave) deserializador.deserializar("C:/Users/Luiz Henrique/Documents/nave/nave");
				 ImageIcon referencia = new ImageIcon("res\\nave_3.gif");
				 nave.setImagem(referencia.getImage());
				 inimigos = (List<Objeto>) deserializador.deserializar("C:/Users/Luiz Henrique/Documents/inimigos/inimigos");
				 inimigos.forEach(inimigo ->{
					 int contador = 0;
					 ImageIcon ref;
					 if (contador++ % 3 == 0) {
							ref = new ImageIcon("res\\inimigo_5.gif");

						} else {

							ref = new ImageIcon("res\\inimigo_7.gif");
						}
						inimigo.setImagem(referencia.getImage());
				 });
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
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

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo in = (Inimigo) inimigos.get(i);
			graficos.drawImage(in.getImagem(), in.getX(), in.getY(), null);

		}
		
		if (this.itemVida != null){
			graficos.drawImage(itemVida.getImagem(), itemVida.getX(), itemVida.getY(), null);
		}
		
		if (this.itemForca != null){
			graficos.drawImage(itemForca.getImagem(), itemForca.getX(), itemForca.getY(), null);
		}

		graficos.setColor(Color.WHITE);
		graficos.drawString("INIMIGOS: " + inimigosFaltantes, 5, 15);
		graficos.drawString("Vida: " + nave.getVida() + "%", 5, 35);
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