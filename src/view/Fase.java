package view;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import model.Direcao;
import model.Inimigo;
import model.ItemForca;
import model.ItemVida;
import model.Missel;
import model.Nave;
import model.Objeto;
import model.ObjetoTexto;
import serializador.Deserializador;
import serializador.Serializador;

public class Fase implements Estado {

	private Nave nave;
	private List<Objeto> inimigos;

	private int QUANTIDADE_INIMIGOS = 10;

	private HashMap<Integer, Direcao> direcoesInimigo; // static
	private boolean valido;
	private Estado proximoEstado;
	private Objeto itemVida;
	private Objeto itemForca;
	private Integer inimigosFaltantes = 10;
	private ControladorDesenho controladorDesenho;
	private ControladorColisoes controladorColisoes;
	
	public Fase() {
		controladorDesenho = new ControladorDesenho();
		controladorColisoes = new ControladorColisoes();
		nave = new Nave();

		direcoesInimigo = new HashMap<Integer, Direcao>();
		direcoesInimigo.put(0, Direcao.BAIXO);
		direcoesInimigo.put(1, Direcao.CIMA);
		direcoesInimigo.put(2, Direcao.DIREITA);
		direcoesInimigo.put(3, Direcao.ESQUERDA);

		inicializaInimigos();

		valido = true;
		inimigosFaltantes = 10;
		
		controladorDesenho.addObjeto(nave);
	}

	public void inicializaInimigos() {

		inimigos = new ArrayList<Objeto>();

		for (int i = 0; i < QUANTIDADE_INIMIGOS; i++) {
			int j = new Random().nextInt(3);
			inimigos.add(new Inimigo(direcoesInimigo.get(j)));
		}
		System.out.println("inimigos: "+inimigos.size());
		this.controladorDesenho.addObjetos(inimigos);
		this.controladorColisoes.addObjetos(inimigos);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {		
		//verifica fim do estado
		if (inimigosFaltantes <= 0) {
			proximoEstado = new Chefao(nave);
			valido = false;
		}
		//fim verifica fim do estado

		//atualiza misseis
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
		//fim atualiza misseis

		atualizaInimigos();

		if (nave.getVida() <= 25 && this.itemVida == null) {
			this.itemVida = new ItemVida();
			this.controladorDesenho.addObjeto(this.itemVida);
			this.controladorColisoes.addObjetoItem(this.itemVida);
		}

		if (inimigosFaltantes <= 5 && this.itemForca == null && this.nave.getForca() < 100) {
			this.itemForca = new ItemForca();
			this.controladorDesenho.addObjeto(this.itemForca);
			this.controladorColisoes.addObjetoItem(this.itemForca);
		}
		
		if (inimigos.size() < 10){
			if (new Date().getTime() % 2 == 0){
				int j = new Random().nextInt(3);
				Inimigo ini = new Inimigo(direcoesInimigo.get(j));
				inimigos.add(ini);
				this.controladorDesenho.addObjeto(ini);
				this.controladorColisoes.addObjeto(ini);
			}
		}

		nave.mexer();
		checarColisoes();
	}

	private void atualizaInimigos() {
		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo in = (Inimigo) inimigos.get(i);

			if (in.isVisivel()) {
				in.mexer();
			} else {
				inimigos.remove(i);
				this.controladorDesenho.removerObjeto(in);
			}

		}
	}

	public void checarColisoes() {

		Objeto objeto = controladorColisoes.checarColisaoNave(nave);
		if (objeto != null){
			if (objeto instanceof Inimigo){
				nave.dano(25);
				int i = inimigos.indexOf(objeto);
				if (i >= 0)
					inimigos.get(i).setVisivel(false);
				if (nave.getVida() <= 0) {
					proximoEstado = new Derrota();
					valido = false;
				}
				controladorColisoes.removerObjeto(objeto);
			}else if (objeto instanceof ItemVida){
				nave.setVida(100);
				itemVida = null;
				this.controladorDesenho.removerObjeto(objeto);
			}else if (objeto instanceof ItemForca){
				nave.dobrarForca();
				itemForca = null;
				this.controladorDesenho.removerObjeto(objeto);
			}
		}
		this.controladorColisoes.addObjetosMisselNave(nave.getMisseis());
		List<HashMap<String, Objeto>> colisoes = controladorColisoes.checarColisaoMissaoNave();
		if (colisoes != null){
			for (HashMap<String, Objeto> map : colisoes) {
				Objeto missel = map.get("missel");
				Objeto inimigo = map.get("inimigo");
				controladorColisoes.removerObjeto(inimigo);
				controladorColisoes.removerObjetoMisselNave(missel);
				int i = inimigos.indexOf(inimigo);
				if (i >= 0)
					inimigos.get(i).setVisivel(false);
				
				i = nave.getMisseis().indexOf(missel);
				if (i >= 0)
					nave.getMisseis().get(i).setVisivel(false);
				
				inimigosFaltantes--;
			}
		}
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		nave.keyReleased(e);
		if(e.getKeyCode() == KeyEvent.VK_F5){
			Serializador serializador = new Serializador();
			try {
				serializador.serializar("saved\\nave",nave);
				serializador.serializar("saved\\inimigos", inimigos);
				serializador.serializar("saved\\inimigosFaltantes", inimigosFaltantes);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F6){
			Deserializador deserializador = new Deserializador();
			try {
				 nave = (Nave) deserializador.deserializar("saved\\nave");
				 ImageIcon referencia = new ImageIcon("res\\nave_3.gif");
				 nave.setImagem(referencia.getImage());
				 inimigos = (List<Objeto>) deserializador.deserializar("saved\\inimigos");
				 inimigos.forEach(inimigo ->{
					 int contador = 0;
					 ImageIcon ref;
					 if (contador++ % 3 == 0) {
							ref = new ImageIcon("res\\inimigo_5.gif");

						} else {

							ref = new ImageIcon("res\\inimigo_7.gif");
						}
						inimigo.setImagem(referencia.getImage());
				 });
				 inimigosFaltantes = (Integer) deserializador.deserializar("saved\\inimigosFaltantes");
				 controladorDesenho = new ControladorDesenho();
				 controladorDesenho.addObjeto(nave);
				 controladorDesenho.addObjetos(inimigos);
				 controladorDesenho.addObjetosDependentes(nave.getMisseis());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		nave.keyPressed(e);
	}

	@Override
	public void paint(Graphics2D graficos) {
		ObjetoTexto texto = new ObjetoTexto("INIMIGOS: " + inimigosFaltantes, 5, 15);
		this.controladorDesenho.addObjetoTexto(texto);
		texto = new ObjetoTexto("Vida: " + nave.getVida() + "%", 5, 35);
		this.controladorDesenho.addObjetoTexto(texto);
		texto = new ObjetoTexto("Força: "+nave.getForca()+"%", 5, 55);
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