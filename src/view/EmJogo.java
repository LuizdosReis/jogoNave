package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import model.Inimigo;
import model.Missel;
import model.Nave;
import model.Objeto;

public class EmJogo implements Estado{
	
	private Nave nave;
	private List<Inimigo> inimigos;
	private JPanel fase;

	public EmJogo(Nave nave, List<Inimigo> inimigos, JPanel fase) {
		this.nave = nave;
		this.inimigos = inimigos;
		this.fase = fase;
	
	}

	public Nave getNave() {
		return nave;
	}

	public void setNave(Nave nave) {
		this.nave = nave;
	}

	public List<Inimigo> getInimigos() {
		return inimigos;
	}

	public void setInimigos(List<Inimigo> inimigos) {
		this.inimigos = inimigos;
	}

	@Override
	public void desenha(Graphics2D graficos) {
		
		graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), fase);

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {

			Missel m = (Missel) misseis.get(i);
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), fase);

		}

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo in = inimigos.get(i);
			graficos.drawImage(in.getImagem(), in.getX(), in.getY(), fase);

		}

		graficos.setColor(Color.WHITE);
		graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 15);
		
		
	}

}
