package controller;

import java.awt.Component;
import java.awt.event.KeyListener;

import view.Estado;
import view.MenuPainel;

public class Menu extends Estado{
	
	private MenuPainel menuPainel;
	
	public Menu(){
		super.setEstado(97, new Game());
		super.setEstado(49, new Game());
		super.setEstado(98, new Creditos());
		super.setEstado(50, new Creditos());
		menuPainel = new MenuPainel();
	};

	@Override
	public void atualiza() {
		Estado est = getComandos().get(menuPainel.getEscolha());
		if (est != null){
			setTerminou(true);
			setProximoEstado(est);
		}
	}
	
	@Override
	public Component getComponent() {
		return menuPainel;
	}
	
	@Override
	public void removeKeyListener() {
		if (menuPainel.getKeyListeners()[0] == null){
			System.out.println("null");
		}else{
			System.out.println("nao nulo");
		}
		menuPainel.removeKeyListener(menuPainel.getKeyListeners()[0]);
	}
	
}
