package controller;

import java.awt.Component;
import java.util.HashMap;

public abstract class Estado {
	private boolean terminou;
	private Estado proximoEstado;
	private HashMap<Integer, Estado> estados = new HashMap<Integer, Estado>();	
	
	public boolean isTerminou() {
		return terminou;
	}

	public void setTerminou(boolean terminou) {
		this.terminou = terminou;
	}

	public Estado getProximoEstado() {
		return this.proximoEstado;
	}
	
	public void setEstado(Integer comando, Estado estado){
		this.estados.put(comando, estado);
	}

	public void setProximoEstado(Estado proximoEstado) {
		this.proximoEstado = proximoEstado;
	}

	public abstract void atualiza();

	public HashMap<Integer, Estado> getComandos() {
		return estados;
	}
	
	public abstract Component getComponent();

	public void removeKeyListener(){
		
	};
}
