package Player;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GameWindow;
import object.Bullet;

public class Projectile extends Entity{
	Entity user;
	public Projectile(GameWindow gw) {
		super(gw);
	}
	public void set(int x, int y, int bDirection, boolean alive, Entity user) {
		this.bulletX = x;
		this.bulletY = y;
		this.bDirection = bDirection;
		this.alive = alive;
		this.life = this.maxLife;
		this.user = user;
		bullet = new Rectangle();
	}
	public void update() {
	
		
		switch(bDirection) {
		case 0: bulletX += bulletSpeed; bullet.setBounds(bulletX+40, bulletY+35, 30, 10);break;
		case 1: bulletX -= bulletSpeed; bullet.setBounds(bulletX+10, bulletY+35, 30, 10);break;
		case 2:	bulletY += bulletSpeed; bullet.setBounds(bulletX +35, bulletY+35, 10, 30); break;
		case 3: bulletY -= bulletSpeed; bullet.setBounds(bulletX +35, bulletY+20, 10, 30); break;
		case 4: bulletX += bulletSpeed; bulletY -= bulletSpeed; break;
		case 5: bulletX += bulletSpeed; bulletY += bulletSpeed; break;
		case 6: bulletX -= bulletSpeed; bulletY -= bulletSpeed; break;
		case 7: bulletX -= bulletSpeed; bulletY += bulletSpeed; break;
		}
		
		life --;
		if(life <= 0) {
			alive = false;
		}
		
		
	}
	
}


	