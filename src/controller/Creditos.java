package controller;

import java.awt.Component;

public class Creditos extends Estado {
	
	
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
