package Main;

import javax.swing.*;

import Player.Entity;
import Player.Projectile;
import Player.Tanks;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
public class GameWindow extends JPanel implements Runnable{
    JLabel text = new JLabel("C");
    Container container = new Container();
	final int orgtileSize = 16;
	final int scale = 5;
	public final int tileSize = orgtileSize * scale;
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Tanks> entityList = new ArrayList<>();
	final int FPS = 60;
	public Tanks tanks = new Tanks(this);
	Thread gameThread;
	Sound sound = new Sound();
	public boolean over = false;
	public JPanel panel = this;
	public JPanel health = new JPanel();
	private Graphics2D g2;
    public GameWindow(){
    	
    }
    public void setUpGame() {
    	JFrame window = new JFrame();
    	window.setSize(800,800);
        window.setVisible(true);
        window.setTitle("Robots");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
        window.add(panel);

        
       
        startThread();
        
    }

    public void startThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/ FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long now;

		while (gameThread != null) {
			now = System.nanoTime();
			delta += (now - lastTime) / drawInterval;
			lastTime = now;
			if (delta >= 1) {
			update();
			repaint();
			
			delta--;
			}
		}	
	}
	public void update () {
		for(int i = 0; i < entityList.size(); i++) {
			if(entityList.get(i) != null) {
				entityList.get(i).update();
			}
		}	
		
		for(int i = 0; i < projectileList.size(); i++) {
			if(projectileList.get(i) != null) {
				if(projectileList.get(i).alive == true) {
					projectileList.get(i).update();
				}
				if(projectileList.get(i).alive == false) {
					projectileList.remove(i);
				}
			}
		}
		
		if (entityList.size()==1) {
			gameThread = null;
			over = true;
			playSE(3);
		}
	}

	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		this.g2 = g2;
		
		if (!over) {
		for(int i = 0; i < entityList.size(); i++) {
			if(entityList.get(i) != null) {
				entityList.get(i).draw(g2);;
			}
		}	
		for(int i = 0; i < projectileList.size(); i++) {
			if(projectileList.get(i) != null) {
				projectileList.get(i).draw(g2);;
			}
		}	
		g2.dispose();
		}
		else {
			drawEndScreen();
		}
	}
	
	public void drawEndScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,80));
		String win = " WINS";
		String endText = entityList.get(0).tank.concat(win);
		
		int x = getXforCenteredText(endText);
		int y = panel.getHeight()/2;
		g2.drawString(endText, x, y);
	}
	public int getXforCenteredText(String endText) {
		int length = (int)g2.getFontMetrics().getStringBounds(endText, g2).getWidth();
		int x = panel.getWidth()/2 - length/2;
		
		return x;
	}
}
class MyMouseListener extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent e){
       
    	//keeps in frame
    	//Jlabel = triangle object
    	JLabel text = (JLabel)e.getSource();
        int x = (int)(Math.random()*(400-30));
        int y = (int)(Math.random()*(400-30));
        text.setLocation(x,y);
    }

}