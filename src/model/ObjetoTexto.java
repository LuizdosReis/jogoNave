package model;

import java.io.Serializable;

public class ObjetoTexto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int x, y;
	protected String texto;
	
	public ObjetoTexto(String texto, int x, int y) {
		this.texto = texto;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {	
		return y;
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}
