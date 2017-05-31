package model;

import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;

import view.ContainerDeJanelas;

public class Inimigo extends Objeto {

	private static final int VELOCIDADE = 1;

	private static int contador = 0;

	private Direcao direcao;

	public Inimigo(Direcao direcao) {

		if (direcao == Direcao.BAIXO) {
			this.y = 1;
			this.x = new Random().nextInt(ContainerDeJanelas.LARGURA_TELA - 50);
		} else if (direcao == Direcao.CIMA) {
			this.y = ContainerDeJanelas.ALTURA_TELA;
			this.x = new Random().nextInt(ContainerDeJanelas.LARGURA_TELA - 50);
		} else if (direcao == Direcao.ESQUERDA) {
			this.x = ContainerDeJanelas.LARGURA_TELA;
			this.y = new Random().nextInt(ContainerDeJanelas.ALTURA_TELA - 50);
		} else {
			this.x = 1;
			this.y = new Random().nextInt(ContainerDeJanelas.ALTURA_TELA - 50);
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

		if (this.x > ContainerDeJanelas.LARGURA_TELA - 20)
			direcao = Direcao.ESQUERDA;
		
		if (this.x <= 1)
			direcao = Direcao.DIREITA;

		if (this.y > ContainerDeJanelas.ALTURA_TELA - 20)
			direcao = Direcao.CIMA;

		if (this.y < 1)
			direcao = Direcao.BAIXO;
	}
}
