package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import view.ContainerDeJanelas;

public class Boss extends Objeto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int VELOCIDADE = 1;

	private List<Objeto> misseis;
	private int vida;
	private long momentoDano;
	
	private long tempoTiro = 0;

	public Boss() {

		this.x = ContainerDeJanelas.LARGURA_TELA - 230;
		this.y = new Random().nextInt(ContainerDeJanelas.ALTURA_TELA - 50);

		ImageIcon referencia = new ImageIcon("res\\boss.gif");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		this.misseis = new ArrayList<Objeto>();
		isVisivel = true;
		vida = 100;
		direcao = Direcao.BAIXO;
	}

	public int getVida() {
		return vida;
	}
	
	public void dano(Integer dano) {
		if (momentoDano <  new Date().getTime() - 600){
			this.vida -= dano;
			momentoDano = new Date().getTime();
		}
	}

	public void mexer() {

		if (this.direcao == Direcao.BAIXO) {
			this.y += VELOCIDADE;
			if (this.y > ContainerDeJanelas.ALTURA_TELA - 120) {
				this.direcao = Direcao.CIMA;
			}
		} else {
			this.y -= VELOCIDADE;
			if (this.y < 0) {
				this.direcao = Direcao.BAIXO;
			}
		}

	}

	public void atirar() {
		this.misseis.add(new Missel(x, y + altura / 2, Direcao.ESQUERDA));
		this.tempoTiro = new Date().getTime();
	}
	
	public Boolean podeAtirar(){
		long espera = new Date().getTime() - this.tempoTiro;
		if (espera >= 50){
			return true;
		}
		return false;
	}

	public List<Objeto> getMisseis() {
		return misseis;
	}

}
