package model;

import java.io.Serializable;
import java.util.Random;

import javax.swing.ImageIcon;

import view.ContainerDeJanelas;

public class ItemVida extends Objeto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemVida() {

		this.x = new Random().nextInt(ContainerDeJanelas.LARGURA_TELA-60);
		this.y = new Random().nextInt(ContainerDeJanelas.ALTURA_TELA-80);
		
		ImageIcon referencia = new ImageIcon("res\\vida.gif");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
	}

	public void mexer(){
		
	}
	
}
