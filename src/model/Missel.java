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
		
		if(this.direcao == Direcao.DIREITA){
			this.x += VELOCIDADE;
		}else if(this.direcao == Direcao.ESQUERDA){
			this.x -= VELOCIDADE;
		}else if(this.direcao == Direcao.CIMA){
			this.y -= VELOCIDADE;
		}else{
			this.y += VELOCIDADE;
		}
	}
	
}
