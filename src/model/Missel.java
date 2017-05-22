package model;

import javax.swing.ImageIcon;

public class Missel extends Objeto{

	private static final int LARGURA_TELA = 500;
	private static final int VELOCIDADE = 2;

	public Missel(int x, int y) {

		this.x = x;
		this.y = y;

		ImageIcon referencia = new ImageIcon("res\\missel.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
		isVisivel = true;
	}

	public void mexer(){
		
		this.x += VELOCIDADE;
		if(this.x > LARGURA_TELA){
			isVisivel = false;
		}
		
	}
	
}
