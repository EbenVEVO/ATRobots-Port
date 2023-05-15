package Player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GameWindow;
import object.Bullet;

public class Entity {
	public int gx , gy, bx, by, bulletX, bulletY;
	public int tankX,tankY,speed;
	public String tank;
	public int gspeed, bspeed;
	public int bulletSpeed;
	public int maxLife, life;
	public int tankHealth;
	public boolean invincible = false;
	
	public Rectangle bullet;
	public Hitbox hitbox;
	public Rectangle healthbar;
	public boolean alive;
	int direction,bDirection;
	GameWindow gw;
	public Projectile projectile;
	public BufferedImage gTriRight, gTriUp, gTriDown, gTriLeft,gTriUpLeft, gTriUpRight, gTriDownLeft,gTriDownRight,bTriRight, bTriUp, bTriDown, bTriLeft,bTriUpLeft, bTriUpRight, bTriDownLeft,bTriDownRight,rTriRight, rTriUp, rTriDown, rTriLeft,rTriUpLeft, rTriUpRight, rTriDownLeft,rTriDownRight,cTriRight, cTriUp, cTriDown, cTriLeft,cTriUpLeft, cTriUpRight, cTriDownLeft,cTriDownRight,mTriRight, mTriUp, mTriDown, mTriLeft,mTriUpLeft, mTriUpRight, mTriDownLeft,mTriDownRight,yTriRight, yTriUp, yTriDown, yTriLeft,yTriUpLeft, yTriUpRight, yTriDownLeft,yTriDownRight;
	public BufferedImage bullet_right, bullet_NE, bullet_up, bullet_NW ;
		
	public Entity(GameWindow gw) {
		this.gw = gw;
	}

	public BufferedImage setup(String imagePath) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public void draw(Graphics2D g2) {
		
		
		switch (bDirection) {
		case 0: g2.drawImage(bullet_right, bulletX + 10, bulletY, gw.tileSize, gw.tileSize, null); break;
		case 1: g2.drawImage(bullet_right, bulletX - 10, bulletY, gw.tileSize, gw.tileSize, null); break;
		case 2: g2.drawImage(bullet_up, bulletX, bulletY + 10, gw.tileSize, gw.tileSize, null); break;
		case 3: g2.drawImage(bullet_up, bulletX, bulletY - 10, gw.tileSize, gw.tileSize, null); break;
		case 4: case 7: g2.drawImage(bullet_NE, bulletX, bulletY, gw.tileSize, gw.tileSize, null); break;
		case 5: case 6: g2.drawImage(bullet_NW, bulletX, bulletY, gw.tileSize, gw.tileSize, null); break;
		}
	}
	public void update() {
		projectile.update();
		
	}
}