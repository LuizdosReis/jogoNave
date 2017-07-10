package view;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;

import model.Boss;
import model.ItemForca;
import model.ItemVida;
import model.Missel;
import model.Nave;
import model.Objeto;
import model.ObjetoTexto;

public class Chefao implements Estado{

	
	private Nave nave;
	private Boss boss;
	private boolean valido;
	private Estado proximoEstado;
	private Objeto itemVida;
	private Objeto itemForca;
	private ControladorDesenho controladorDesenho;
	private ControladorColisoes controladorColisoes;
	
	public Chefao(Objeto nave) {
		this.nave = (Nave) nave;
		this.boss = new Boss();
		this.valido = true;
		
		controladorDesenho = new ControladorDesenho();
		controladorColisoes = new ControladorColisoes();
		controladorDesenho.addObjeto(nave);
		controladorDesenho.addObjeto(boss);
		controladorColisoes.addObjeto(boss);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (boss.getVida() <= 0) {
			proximoEstado = new Vitoria();
			valido = false;
		}

		List<Objeto> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);

			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}

		}
		
		this.controladorDesenho.limparObjetosDependentes();
		this.controladorDesenho.addObjetosDependentes(misseis);

		List<Objeto> misseisBoss = boss.getMisseis();

		for (int i = 0; i < misseisBoss.size(); i++) {

			Missel m = (Missel) misseisBoss.get(i);

			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseisBoss.remove(i);
				this.controladorDesenho.removerObjeto(m);
			}

		}
		
		if (nave.getVida() <= 25 && this.itemVida == null) {
			this.itemVida = new ItemVida();
			this.controladorDesenho.addObjeto(this.itemVida);
			this.controladorColisoes.addObjetoItem(this.itemVida);
		}

		if (boss.getVida() < 50 && this.itemForca == null && this.nave.getForca() < 100) {
			this.itemForca = new ItemForca();
			this.controladorDesenho.addObjeto(this.itemForca);
			this.controladorColisoes.addObjetoItem(this.itemForca);
		}

		nave.mexer();
		boss.mexer();
		
		if (nave.getY() <= (boss.getY()+(boss.getAltura()/2))
				&& nave.getY()+nave.getAltura() >= (boss.getY()+(boss.getAltura()/2))){
			if (boss.podeAtirar()){
				Objeto missel = boss.atirar();
				this.controladorColisoes.addObjeto(missel);
				this.controladorDesenho.addObjeto(missel);
			}
		}
		
		checarColisoes();
	}

	public void checarColisoes() {

		
		Objeto objeto = controladorColisoes.checarColisaoNave(nave);
		if (objeto != null){
			if (objeto instanceof Missel){
				nave.dano(25);
				int i = boss.getMisseis().indexOf(objeto);
				if (i >= 0)
					boss.getMisseis().get(i).setVisivel(false);
				if (nave.getVida() <= 0) {
					proximoEstado = new Derrota();
					valido = false;
				}
			}else if (objeto instanceof ItemVida){
				nave.setVida(100);
				itemVida = null;
				controladorDesenho.removerObjeto(objeto);
			}else if (objeto instanceof ItemForca){
				nave.dobrarForca();
				itemForca = null;
				controladorDesenho.removerObjeto(objeto);
			}else if (objeto instanceof Boss){
				nave.dano(25);
				if (nave.getVida() <= 0) {
					proximoEstado = new Derrota();
					valido = false;
				}
			}
		}
		
		this.controladorColisoes.addObjetosMisselNave(nave.getMisseis());
		List<HashMap<String, Objeto>> colisoes = controladorColisoes.checarColisaoMissaoNave();
		if (colisoes != null){
			for (HashMap<String, Objeto> map : colisoes) {
				Objeto missel = map.get("missel");
				boss.dano(nave.getForca());
				int i = nave.getMisseis().indexOf(missel);
				if (i >= 0)
					nave.getMisseis().get(i).setVisivel(false);
				
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		nave.keyReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		nave.keyPressed(e);
		
	}

	@Override
	public void paint(Graphics2D graficos) {
		ObjetoTexto texto = new ObjetoTexto("Força: "+nave.getForca()+"%", 5, 55);
		this.controladorDesenho.addObjetoTexto(texto);
		texto = new ObjetoTexto("Vida Boss: " + boss.getVida() + "%", 5, 15);
		this.controladorDesenho.addObjetoTexto(texto);
		texto = new ObjetoTexto("Vida: "+nave.getVida()+"%", 5, 35);
		this.controladorDesenho.addObjetoTexto(texto);
		this.controladorDesenho.desenharObjetos(graficos);
	}

	@Override
	public Estado getProximoEstado() {
		return proximoEstado;
	}

	@Override
	public boolean isValido() {
		return valido;
	}
}
