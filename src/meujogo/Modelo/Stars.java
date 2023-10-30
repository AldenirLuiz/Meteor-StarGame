package meujogo.Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Stars {
	private int posX, posY, size, speed;

	private ArrayList<Color> COLORS = new ArrayList(Arrays.asList(Color.orange, Color.cyan, Color.white, Color.yellow));

	private boolean isAlive;
	private Color color;
	private Random randInt = new Random();

	public void update(int x, int y) {
		this.posX -= this.speed;
		if (this.posX < 0) {
			this.isAlive = false;
		}

		if ((this.posX >= x && this.posX <= x + 100) && (this.posY == y || this.posY >= y && this.posY - 100 <= y)) {
			this.speed += 2;
		}

	}

	public Stars() {
		this.posX = randInt.nextInt(0, 1200);
		this.posY = randInt.nextInt(1024);
		this.speed = randInt.nextInt(1, 5);

		this.size = randInt.nextInt(1, 4);

		this.isAlive = true;
		this.color = COLORS.get(randInt.nextInt(COLORS.size()));
	}

	public void load(Graphics graph) {
		graph.setColor(this.color);
		graph.drawOval(this.posX, this.posY, this.size, this.size);
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
