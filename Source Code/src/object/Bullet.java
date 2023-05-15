package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GameWindow;
import Player.Projectile;

public class Bullet extends Projectile{
	GameWindow gw;
	public Bullet(GameWindow gw) {
		super(gw);
		this.gw = gw;
		
		bulletSpeed = 5;
		maxLife = 120;
		life = maxLife;
		alive = false;
		getImage();
	}
	public void getImage(){
		
			bullet_right = setup("/playr/bullet_right");
			bullet_NE = setup("/playr/bullet_NE");
			bullet_NW = setup("/playr/bullet_NWSE");
			bullet_up = setup("/playr/bullet_up");
	}
}

