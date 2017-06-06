package model;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Objeto {
	protected Image imagem;
	protected int x, y;
	protected int largura, altura;
	protected boolean isVisivel;
	protected Direcao direcao;
	
	public int getX() {
		return x;
	}
	
	public int getY() {	
		return y;
	}
	
	public Image getImagem() {
		return imagem;
	}

	public boolean isVisivel() {
		return isVisivel;
	}
	
	public int getAltura() {
		return altura;
	}
	
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	
	public abstract void mexer();
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
}
