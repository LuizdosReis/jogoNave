package controller;

import java.awt.Component;

import view.Estado;
import view.Fase;
import view.MenuPainel;

public class Game extends Estado {

	private Fase fase;
	
	public Game(){
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
