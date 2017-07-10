package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import model.Objeto;
import model.ObjetoTexto;

public class ControladorDesenho {
	
	private List<Objeto> objetos;
	private List<Objeto> objetosDependentes;
	private List<ObjetoTexto> objetosTexto;
	
	public void desenharObjetos(Graphics2D graficos){
		if (objetos != null){
			for (Objeto objeto : objetos) {
				graficos.drawImage(objeto.getImagem(), objeto.getX(), objeto.getY(), null);
			}
		}
		if (objetosDependentes != null){
			for (Objeto objeto : objetosDependentes) {
				graficos.drawImage(objeto.getImagem(), objeto.getX(), objeto.getY(), null);
			}
		}
		if (objetosTexto != null){
			graficos.setColor(Color.WHITE);
			for (ObjetoTexto objeto : objetosTexto) {
				graficos.drawString(objeto.getTexto(), objeto.getX(), objeto.getY());
			}
			objetosTexto.clear();
		}
	}
	
	public void addObjeto(Objeto objeto) {
		if (this.objetos == null){
			this.objetos = new ArrayList<>();
		}
		this.objetos.add(objeto);
	}
	
	public void addObjetos(List<Objeto> objetos) {
		if (this.objetos == null){
			this.objetos = new ArrayList<>();
		}
		this.objetos.addAll(objetos);
	}
	
	public void addObjetoDependente(Objeto objeto) {
		if (this.objetosDependentes == null){
			this.objetosDependentes = new ArrayList<>();
		}
		this.objetosDependentes.add(objeto);
	}
	
	public void addObjetosDependentes(List<Objeto> objetos) {
		if (this.objetosDependentes == null){
			this.objetosDependentes = new ArrayList<>();
		}
		this.objetosDependentes.addAll(objetos);
	}
	
	public void addObjetoTexto(ObjetoTexto objeto) {
		if (this.objetosTexto == null){
			this.objetosTexto = new ArrayList<>();
		}
		this.objetosTexto.add(objeto);
	}
	
	public void removerObjeto(Objeto objeto){
		if (this.objetos != null){
			this.objetos.remove(objeto);
		}
	}
	
	public void limparObjetosDependentes(){
		if (this.objetosDependentes != null){
			this.objetosDependentes.clear();
		}
	}
	
}
