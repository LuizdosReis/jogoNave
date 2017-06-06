package model;

import javax.swing.ImageIcon;

import view.ContainerDeJanelas;

public class Missel extends Objeto{

	private static final int VELOCIDADE = 3;
	

	public Missel(int x, int y, Direcao direcao) {

		this.x = x;
		this.y = y;
		this.direcao = direcao;
		ImageIcon referencia = null;
		if (direcao == Direcao.DIREITA || direcao == Direcao.ESQUERDA)
			referencia = new ImageIcon("res\\missel.png");
		else
			referencia = new ImageIcon("res\\missel_vertical.png");
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
		
		if(this.x > ContainerDeJanelas.LARGURA_TELA || this.x < 0 || this.y > ContainerDeJanelas.ALTURA_TELA || this.y < 0){
			isVisivel = false;
		}
		
	}
	
}
