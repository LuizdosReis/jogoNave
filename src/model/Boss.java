package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

public class Boss extends Objeto {

	private static final int LARGURA_TELA = 460;
	private static final int ALTURA_TELA = 340;
	private static final int VELOCIDADE = 1;

	private Direcao direcao = Direcao.BAIXO;

	private List<Missel> misseis;
	private int vida;
	private long momentoDano;

	public Boss() {

		this.x = LARGURA_TELA - 230;
		this.y = new Random().nextInt(ALTURA_TELA - 50);

		ImageIcon referencia = new ImageIcon("res\\boss.gif");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		this.misseis = new ArrayList<Missel>();
		isVisivel = true;
		vida = 100;
	}

	public int getVida() {
		return vida;
	}
	
	public void dano(Integer dano) {
		if (momentoDano <  new Date().getTime() - 600){
			this.vida -= dano;
			momentoDano = new Date().getTime();
		}
	}

	public void mexer() {

		if (this.direcao == Direcao.BAIXO) {
			this.y += VELOCIDADE;
			if (this.y > ALTURA_TELA - 120) {
				this.direcao = Direcao.CIMA;
			}
		} else {
			this.y -= VELOCIDADE;
			if (this.y < 0) {
				this.direcao = Direcao.BAIXO;
			}
		}

	}

	public void atirar() {
		this.misseis.add(new Missel(x, y + altura / 2, Direcao.ESQUERDA));
	}

	public List<Missel> getMisseis() {
		return misseis;
	}

}
