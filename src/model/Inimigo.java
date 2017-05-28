package model;

import javax.swing.ImageIcon;

public class Inimigo extends Objeto {

	private static final int LARGURA_TELA = 500;
	private static final int VELOCIDADE = 1;

	private static int contador = 0;
	

	private boolean emDirecaoAEsquerda;
	private boolean naDirecaoParaCima;

	public Inimigo(int x, int y) {

		this.x = x;
		this.y = y;

		ImageIcon referencia;

		if (this.x > 0)
			emDirecaoAEsquerda = true;
		
		naDirecaoParaCima = true;

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
		if (this.x < -50)
			emDirecaoAEsquerda = false;

		if (this.x > 550)
			emDirecaoAEsquerda = true;

		if (emDirecaoAEsquerda) {
			this.x -= VELOCIDADE;
		} else {
			this.x += VELOCIDADE;
		}

		if(this.y < 1)
			naDirecaoParaCima = false;
		
		if(this.y > 340)
			naDirecaoParaCima = true;
		
		if (naDirecaoParaCima) {
			this.y -= VELOCIDADE;
		} else {
			this.y += VELOCIDADE;
		}	
	}

}
