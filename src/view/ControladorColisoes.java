package view;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Inimigo;
import model.Nave;
import model.Objeto;

public class ControladorColisoes {
	
	private List<Objeto> objetos;
	private List<Objeto> objetosItem;
	private List<Objeto> objetosMisselNave;
	
	public Objeto checarColisaoNave(Nave nave){
		if (objetos != null){
			for (Objeto objeto : objetos) {
				Rectangle forma = objeto.getBounds();
				if (forma.intersects(nave.getBounds())){
					return objeto;
				}
			}
		}
		if (objetosItem != null){
			for (Objeto objeto : objetosItem) {
				Rectangle forma = objeto.getBounds();
				if (forma.intersects(nave.getBounds())){
					objetosItem.remove(objeto);
					return objeto;
				}
			}
		}
		return null;
	}
	
	public List<HashMap<String, Objeto>> checarColisaoMissaoNave(){
		List<HashMap<String, Objeto>> retorno = null;
		if (objetosMisselNave != null && objetos != null){
			retorno = new ArrayList<>();
			Rectangle formaInimigo;
			Rectangle formaMissel;
			for (Objeto objetoMissel : objetosMisselNave) {
				
				formaMissel = objetoMissel.getBounds();
				
				for (Objeto objeto : objetos) {
					
					formaInimigo = objeto.getBounds();
					
					if (formaMissel.intersects(formaInimigo)) {
						
						HashMap<String, Objeto> map = new HashMap<>();
						map.put("missel", objetoMissel);
						map.put("inimigo", objeto);
						retorno.add(map);
					}
					
				}
				
			}
			objetosMisselNave.clear();
		}
		return retorno;
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
	
	public void addObjetoItem(Objeto objeto){
		if (this.objetosItem == null){
			this.objetosItem = new ArrayList<>();
		}
		this.objetosItem.add(objeto);
	}
	
	public void addObjetosMisselNave(List<Objeto> objetos){
		if (this.objetosMisselNave == null){
			this.objetosMisselNave = new ArrayList<>();
		}
		this.objetosMisselNave.addAll(objetos);
	}
	
	public void removerObjeto(Objeto objeto){
		if (this.objetos != null){
			this.objetos.remove(objeto);
		}
	}
	
	public void removerObjetoItem(Objeto objeto){
		if (this.objetosItem != null){
			this.objetosItem.remove(objeto);
		}
	}
	
	public void removerObjetoMisselNave(Objeto objeto){
		if (this.objetosMisselNave != null){
			this.objetosMisselNave.remove(objeto);
		}
	}
	
}
