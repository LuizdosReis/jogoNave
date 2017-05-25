package view;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class EmDerrota implements Estado{

	@Override
	public void desenha(Graphics2D graficos) {
		ImageIcon fimJogo = new ImageIcon("res\\game_over.jpg");

		graficos.drawImage(fimJogo.getImage(), 0, 0, null);	
	}

}
