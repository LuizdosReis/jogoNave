package model;

import java.util.Random;

import javax.swing.ImageIcon;

import view.ContainerDeJanelas;

public class ItemForca extends Objeto{

	public ItemForca() {

		this.x = new Random().nextInt(ContainerDeJanelas.LARGURA_TELA-60);
		this.y = new Random().nextInt(ContainerDeJanelas.ALTURA_TELA-80);
		
		ImageIcon referencia = new ImageIcon("res\\forca.gif");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
	}

	public void mexer(){
		
	}
	
}
