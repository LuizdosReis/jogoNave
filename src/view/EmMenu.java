package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class EmMenu implements Estado{

	@Override
	public void desenha(Graphics2D graficos) {
		graficos.setColor(Color.WHITE);
		graficos.setFont(new Font("castellar", Font.PLAIN, 20));
		graficos.drawString("1 - Iniciar", 180, 150);
		graficos.drawString("2 - Créditos ( Em desenvolvimento )", 30, 180);	
	}

}
