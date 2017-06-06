package view;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public interface Estado {

	public void keyReleased(KeyEvent e);
	public void keyPressed(KeyEvent e);
	public void actionPerformed(ActionEvent e);
	public void paint(Graphics2D graficos);
	public Estado getProximoEstado();
	public boolean isValido();
	public void checarColisoes();
	
	

}
