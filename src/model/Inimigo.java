package model;

import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;

public class Inimigo extends Objeto {

	private static final int LARGURA_TELA = 500;
	private static final int ALTURA_TELA = 550;
	private static final int VELOCIDADE = 1;

	private static int contador = 0;

	private Direcao direcao;

	public Inimigo(Direcao direcao) {

		if (direcao == Direcao.BAIXO) {
			this.y = -100;
			this.x = new Random().nextInt(LARGURA_TELA - 50);
		} else if (direcao == Direcao.CIMA) {
			this.y = ALTURA_TELA + 100;
			this.x = new Random().nextInt(LARGURA_TELA - 50);
		} else if (direcao == Direcao.ESQUERDA) {
			this.x = LARGURA_TELA + 100;
			this.y = new Random().nextInt(ALTURA_TELA - 50);
		} else {
			this.x = -100;
			this.y = new Random().nextInt(ALTURA_TELA - 50);
		}

		this.direcao = direcao;

		ImageIcon referencia;

		if (contador++ % 3 == 0) {
			referencia = new ImageIcon("res\\inimigo_5.gif");

		} else {

			referencia = new ImageIcon("res\\inimigo_7.gif");
		}
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);

		isVisivel = true;
	}

	public void mexer() {
		if (direcao == Direcao.BAIXO) {
			this.y += VELOCIDADE;
			if (new Date().getTime() % 2 == 0) {
				this.x += VELOCIDADE + 2;
			} else {
				this.x -= VELOCIDADE + 2;
			}
		} else if (direcao == Direcao.CIMA) {
			this.y -= VELOCIDADE;
		} else if (direcao == Direcao.ESQUERDA) {
			this.x -= VELOCIDADE;
		} else {
			this.x += VELOCIDADE;
		}

		if (this.x > 1000 || this.y > 1000 || this.x < -200 || this.y < -200) {
			isVisivel = false;
		}
	}

}
