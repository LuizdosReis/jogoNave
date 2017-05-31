package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave extends Objeto {

	private int dx, dy;

	private Direcao direcao;

	private List<Missel> misseis;

	private int vida;
	private int forca;
	private long momentoDano;

	public Nave() {

		ImageIcon referencia = new ImageIcon("res\\nave_3.gif");
		imagem = referencia.getImage();

		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);

		misseis = new ArrayList<Missel>();

		this.vida = 100;
		this.forca = 5;

		direcao = Direcao.DIREITA;

		this.x = 100;
		this.y = 100;

	}

	public void mexer() {

		x += dx; // 1 e 462
		y += dy; // 1 e 340

		if (this.x < 1) {
			x = 1;
		}

		if (this.x > 462) {
			x = 462;
		}

		if (this.y < 1) {
			y = 1;
		}

		if (this.y > 340) {
			y = 340;
		}

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

	public int getForca() {
		return forca;
	}

	public void setForca(int forca) {
		this.forca = forca;
	}

	public List<Missel> getMisseis() {
		return misseis;
	}

	public void atiraParaDireita() {
		this.misseis.add(new Missel(x + largura, y + altura / 2, Direcao.DIREITA));
	}

	public void atiraParaEsquerda() {
		this.misseis.add(new Missel(x - largura, y + altura / 2, Direcao.ESQUERDA));
	}

	public void atiraParaCima() {
		this.misseis.add(new Missel(x + largura / 2, y - altura, Direcao.CIMA));
	}

	public void atiraParaBaixo() {
		this.misseis.add(new Missel(x + largura / 2, y + altura, Direcao.BAIXO));
	}

	public void keyPressed(KeyEvent tecla) {

		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_SPACE) {
			if (this.direcao == Direcao.DIREITA) {
				this.atiraParaDireita();
			} else if (this.direcao == Direcao.BAIXO) {
				atiraParaBaixo();
			} else if (this.direcao == Direcao.ESQUERDA) {
				atiraParaEsquerda();
			} else {
				atiraParaCima();
			}
		}

		if (codigo == KeyEvent.VK_UP) {
			dy = -1;
			this.direcao = Direcao.CIMA;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 1;
			this.direcao = Direcao.BAIXO;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = -1;
			this.direcao = Direcao.ESQUERDA;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 1;
			this.direcao = Direcao.DIREITA;
		}

	}

	public void keyReleased(KeyEvent tecla) {

		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public void dobrarForca() {
		this.forca = this.forca * 2 <= 100 ? this.forca *= 2 : 100;
	}

}
