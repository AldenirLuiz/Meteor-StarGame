package meujogo.Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Enemy {

	private int posX, posY, sizeY, sizeX, speed, life;

	private ArrayList<Color> COLORS = new ArrayList(Arrays.asList(Color.orange, Color.cyan, Color.white, Color.yellow));

	private boolean isAlive;
	private Color color;
	private Random randInt = new Random();

	public void update(int x, int y) {
		this.posX -= this.speed;
		if (this.posX < 0) {
			this.isAlive = false;
		}
	}

	public Enemy() {
		this.posX = randInt.nextInt(800, 2048);
		this.posY = randInt.nextInt(1024);
		this.speed = randInt.nextInt(1, 5);

		this.sizeX = randInt.nextInt(50, 150);
		this.sizeY = randInt.nextInt(20, 100);
		
		this.life = sizeX+sizeY;

		this.isAlive = true;
		this.color = COLORS.get(randInt.nextInt(COLORS.size()));
	}

	public void load(Graphics graph) {
		String num = "%s".formatted(life);
		char[] g2 = num.toCharArray();
		
		graph.setColor(this.color);
		graph.fillOval(posX, posY, sizeX, sizeY);

		graph.setColor(Color.black);
		graph.drawChars(g2, 0, g2.length, posX-(g2.length*2) + (sizeX / 2), posY +(sizeY / 2)+g2.length);
	}

	public int getLife() {
		return life;
	}

	public int getValueEscore() {
		return this.sizeX;
	}

	public int setLife(int damage) {
		if (this.life > damage) {
			this.life -= damage;

		} else {
			this.isAlive = false;
			return this.sizeY;
		}
		return 0;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX-(sizeX/2), posY-(sizeY/2), sizeX, sizeY);
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
