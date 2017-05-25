package controller;

import java.awt.Component;

import view.Estado;

public class Vitoria extends Estado {
	
	public Vitoria() {
	}

	@Override
	public void atualiza() {
		setProximoEstado(new Menu());
		setTerminou(true);
	}
	
	@Override
	public Component getComponent() {
		return null;
	}
}
