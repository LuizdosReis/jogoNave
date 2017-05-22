package controller;

import view.ContainerDeJanelas;
import view.Fase;

public class Maquina {
	
	private Estado estadoAtual;
	private ContainerDeJanelas container;
	
	
	public void start(){	
//		this.estadoAtual = new Menu();
//		container = new ContainerDeJanelas(this.estadoAtual.getComponent());
		container = new ContainerDeJanelas(new Fase());
//		container.trocarTela(new Fase());
//		do{
//			this.estadoAtual.atualiza();
//			if (this.estadoAtual.isTerminou()){
//				this.estadoAtual.removeKeyListener();
//				this.estadoAtual = this.estadoAtual.getProximoEstado();
//				container.trocarTela(this.estadoAtual.getComponent());
//			}
//		}while(true);
	};
	
	
	public Estado getEstadoAtual() {
		return estadoAtual;
	}

	public void setEstadoAtual(Estado estadoAtual) {
		this.estadoAtual = estadoAtual;
	}

}
