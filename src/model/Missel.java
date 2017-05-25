package model;

import javax.swing.ImageIcon;

public class Missel extends Objeto{

	private static final int LARGURA_TELA = 500;
	private static final int VELOCIDADE = 2;
	
	private Direcao direcao;

	public Missel(int x, int y, Direcao direcao) {

		this.x = x;
		this.y = y;
		this.direcao = direcao;

		ImageIcon referencia = new ImageIcon("res\\missel.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
		isVisivel = true;
	}

	public void mexer(){
		
		if(direcao == Direcao.direita)
			this.x += VELOCIDADE;
		else
			this.x -= VELOCIDADE;
		
		if(this.x > LARGURA_TELA || this.x < 0){
			isVisivel = false;
		}
		
	}
	
}
