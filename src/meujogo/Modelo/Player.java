package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
	private int x, y;
	private int dx, dy;
	private int altura, largura;
	private int score;

	private boolean isVisible;

	private Image image;

	private List<Tiro> tiros;
	private List<Stars> stars;
	private List<Enemy> enemy;

	public Player() {
		this.x = 100;
		this.y = 100;

		this.score = 0;

		this.isVisible = true;

		this.tiros = new ArrayList<Tiro>();
		this.stars = new ArrayList<Stars>();
		this.enemy = new ArrayList<Enemy>();
	}

	public void load() {

		ImageIcon reference = new ImageIcon("res\\player.png");
		image = reference.getImage();

		altura = image.getHeight(null);
		largura = image.getWidth(null);
	}

	public void update() {
		x += dx;
		y += dy;
	}

	public void tiroSimples() {
		this.tiros.add(new Tiro(x, y));
	}

	public Rectangle getBounds() {
		return new Rectangle(x-(largura/2), y-(altura/2), largura, altura);
	}

	public void keyPressed(KeyEvent tecla) {

		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_A) {
			tiroSimples();
		}

		if (codigo == KeyEvent.VK_UP) {
			dy = -5;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 5;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = -5;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 5;
		}
	}

	public void keyReleased(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_A) {
			tiroSimples();
		}

		if (codigo == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public List<Stars> getStars() {
		return stars;
	}

	public List<Enemy> getEnemy() {
		return enemy;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setPosition(int posX, int posY){
		this.x = posX;
		this.y = posY;
	}
	
	public Image getImage() {
		return image;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int score) {
		this.score += score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
