package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Menu implements Estado{
	
	private boolean valido;
	private Estado proximoEstado;
	
	public Menu() {
		valido = true;
	}

	public void paint(Graphics2D graficos) {
		graficos.setColor(Color.WHITE);
		graficos.setFont(new Font("castellar", Font.PLAIN, 20));
		graficos.drawString("1 - Iniciar", 250, 210);
		graficos.drawString("2 - Carregar", 250, 240);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_1 || keyCode == KeyEvent.VK_NUMPAD1){
			proximoEstado = new Fase();
			valido = false;
		}
		
		if(keyCode == KeyEvent.VK_2 || keyCode == KeyEvent.VK_NUMPAD2){
			proximoEstado = new Fase().deserializar();
			valido = false;
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean isValido() {
		return valido;
	}

	public Estado getProximoEstado() {
		return proximoEstado;
	}

	@Override
	public void checarColisoes() {
		// TODO Auto-generated method stub
		
	}
	
	

}
