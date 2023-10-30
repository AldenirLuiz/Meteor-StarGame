package meujogo.Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.Rectangle;
import java.awt.*;


public class Tiro {
	
	private Image image;
	private int x, y;
	private int largura = 8, altura = 30;
	private boolean isVisible;
	
	private static final int LARGURA = 938;
	private static  int VELOCIDADE = 6;
	
	public Tiro(int x, int y) {
		this.x = x;
		this.y = y;
		isVisible = true;
	}
	
	public void load(Graphics graph) {
		graph.setColor(Color.orange);
		graph.fillOval(this.x+50, this.y+45, this.altura, this.largura);
		//graph.fillOval(this.x+50, this.y+68, this.altura, this.largura);
	}
	
	public void update() {
		this.x += VELOCIDADE;
		if (this.x > LARGURA) {
			isVisible = false;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle (x, y, largura, altura);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}
	
	
}
