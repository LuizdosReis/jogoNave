package controller;

import java.awt.Component;

import view.Fase;
import view.MenuPainel;

public class Game extends Estado {

	private Fase fase;
	
	public Game(){
//		getComandos().put(3, new GameOver());
//		getComandos().put(4, new Vitoria());
//		super.setEstado(97, new Game());
//		super.setEstado(49, new Game());
//		super.setEstado(98, new Creditos());
//		super.setEstado(50, new Creditos());
		fase = new Fase();
	};

	@Override
	public void atualiza() {
		
	}
	
	@Override
	public Component getComponent() {
		return fase;
	}
}
