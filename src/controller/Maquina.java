package controller;

import view.ContainerDeJanelas;
import view.Estado;
import view.Fase;

public class Maquina {
	
	private Estado estadoAtual;
	private ContainerDeJanelas container;
	
	
	public void start(){	
		container = new ContainerDeJanelas(new Fase());
	};
	
	
	public Estado getEstadoAtual() {
		return estadoAtual;
	}

	public void setEstadoAtual(Estado estadoAtual) {
		this.estadoAtual = estadoAtual;
	}

}
