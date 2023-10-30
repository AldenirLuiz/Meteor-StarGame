package meujogo.Modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
	private boolean inGame;
	private JButton restartButton;

	public Fase() {
		
		
		setFocusable(true);
		setDoubleBuffered(true);
		setLayout(new BorderLayout());
		
		inGame = true;

		ImageIcon reference = new ImageIcon("res\\background.png");
		fundo = reference.getImage();

		player = new Player();
		player.load();

		timer = new Timer(5, this);
		timer.start();
		
		//setLayout(new FlowLayout());
		restartButton = new JButton("Restart");
	    restartButton.addActionListener(e -> resetGame());
	    add(restartButton, BorderLayout.CENTER);
	    addKeyListener(new TecladoAdapter());
	    restartButton.setVisible(false);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graficos = (Graphics2D) g;
		if (inGame) {

			graficos.drawImage(fundo, 0, 0, null);

			loadGraphic(player.getTiros(), graficos);
			loadGraphic(player.getStars(), graficos);
			loadGraphic(player.getEnemy(), graficos);

			graficos.drawImage(player.getImage(), player.getX(), player.getY(), this);
			g.setColor(Color.red);
			
			String escore = String.format("Your Escore: %s", player.getScore());
			char[] valEscore = escore.toCharArray();
			
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			g.drawChars(valEscore, 0, valEscore.length, 100, 100);

		} else {
			
			g.setFont(new Font("TimesRoman", Font.BOLD, 64));
			g.setColor(Color.red);
			g.drawString("GAME OVER!", 100, 200);
			
			g.setColor(Color.green);
			g.setFont(new Font("TimesRoman", Font.ITALIC, 32));
			g.drawString("toque na tela para reiniciar.", 100, 250);
			
			restartButton.setVisible(true);
		}
		g.dispose();
	}

	private void resetGame() {
		restartButton.setVisible(false);
		// resetando tiros
		for (int i = 0; i < player.getTiros().size(); i++) {
			player.getTiros().remove(i);
		}

		// Remover inimigos
		clearEnemy();
		/*
		for (int i = 0; i < player.getEnemy().size(); i++) {
			player.getEnemy().remove(i);
		}*/
		
		addKeyListener(new TecladoAdapter());
		player.setScore(0);
		player.setPosition(100, 100);
		inGame = true;
	}

	private void clearEnemy() {
		Iterator<Enemy> iterator = player.getEnemy().iterator();
		while (iterator.hasNext()) {
		    iterator.next();
		    iterator.remove();
		}
	}
	
	private void loadGraphic(List source, Graphics2D graph) {

		for (int i = 0; i < source.size(); i++) {
			if (source.get(i).getClass().toString().endsWith("Tiro")) {
				Tiro tiro = (Tiro) source.get(i);
				// System.out.printf("%s \n", tiro.getImage());
				tiro.load(graph);
			} else if (source.get(i).getClass().toString().endsWith("Stars")) {
				Stars star = (Stars) source.get(i);
				star.load(graph);
			} else {
				Enemy enemy = (Enemy) source.get(i);
				enemy.load(graph);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();

		getStars(player.getStars());
		getEnemy(player.getEnemy());
		getShot(player.getTiros());
		checBounds();

		repaint();
	}

	private void getEnemy(List<Enemy> enemyList) {
		if (enemyList.size() < 10) {
			enemyList.add(new Enemy());
		}

		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			if (enemy.isAlive()) {
				enemy.update(player.getX(), player.getY());
			} else {
				enemyList.remove(i);
			}
		}
	}

	private void getStars(List<Stars> stars) {
		if (stars.size() < 1024) {
			stars.add(new Stars());
		}

		for (int i = 0; i < stars.size(); i++) {
			Stars m = stars.get(i);
			if (m.isAlive()) {
				m.update(player.getX(), player.getY());
			} else {
				stars.remove(i);
			}
		}
	}

	private void getShot(List<Tiro> tiros) {
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisible()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}
	}

	public void checBounds() {
		Rectangle formNave = player.getBounds();
		Rectangle formEnemy;

		for (int i = 0; i < player.getEnemy().size(); i++) {
			Enemy tempEnemy = player.getEnemy().get(i);
			formEnemy = tempEnemy.getBounds();

			if (formNave.intersects(formEnemy)) {

				player.setVisible(false);
				tempEnemy.setAlive(false);
				inGame = false;
			} else {
				for (int tr = 0; tr < player.getTiros().size(); tr++) {
					Tiro tiro = player.getTiros().get(tr);
					if (formEnemy.intersects(tiro.getBounds())) {
						int damage = tempEnemy.setLife(10);
						player.addScore(damage);
						player.getTiros().remove(tr);
					}
				}
			}
		}
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}
	}
}
