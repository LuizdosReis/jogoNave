package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Vitoria implements Estado{



	private boolean valido;
	private Estado proximoEstado;

	public Vitoria() {
		valido = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			proximoEstado = new Menu();
			valido = false;
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void paint(Graphics2D graficos) {
		ImageIcon fimJogo = new ImageIcon("res\\vitoria.png");
		graficos.drawImage(fimJogo.getImage(), 0, 0, null);		
	}

	@Override
	public Estado getProximoEstado() {
		return proximoEstado;
	}

	@Override
	public boolean isValido() {
		return valido;
	}

}
