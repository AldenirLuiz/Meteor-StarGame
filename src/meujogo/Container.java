package meujogo;

import javax.swing.JFrame;

import meujogo.Modelo.Fase;

public class Container extends JFrame{
	
	public Container() {
		add(new Fase());
		setTitle("MyGame");
		setSize(1024, 720);
		// setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Container();
	}
}
