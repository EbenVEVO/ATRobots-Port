package Player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GameWindow;
import object.Bullet;


public class Tanks extends Entity{
	BufferedImage image;
	GameWindow gw;
	long lastTurn = System.currentTimeMillis();
    Random change = new Random();
	int num =0;
	public Tanks(GameWindow gw) {
		super(gw);
		this.gw = gw;
		projectile = new Bullet(gw);
		hitbox = new Hitbox();
		tankHealth = 50;
		healthbar = new Rectangle(tankHealth , 5);
		
		
	}
	public void build(int x, int y, int speed, String color) {
		this.tankX = x;
		this.tankY = y;
		this.speed = speed;
		this.tank = color;
		
	}
	
	public void getImage() {
		bTriRight = setup("/playr/b_triangleright");
		bTriLeft = setup("/playr/b_triangleleft");
		bTriDown = setup("/playr/b_triangledown");
		bTriUp = setup("/playr/b_triangleup");
		bTriUpRight = setup("/playr/b_triNE");
		bTriUpLeft = setup("/playr/b_triNW");
		bTriDownRight = setup("/playr/b_triSE");
		bTriDownLeft = setup("/playr/b_triSW");
		
		gTriRight = setup("/playr/g_triangleright");
		gTriUp = setup("/playr/g_triangleup");
		gTriLeft = setup("/playr/g_triangleleft");
		gTriDown = setup("/playr/g_triangledown");
		gTriUpRight= setup("/playr/g_triNE");
		gTriUpLeft = setup("/playr/g_triNW");
		gTriDownRight = setup("/playr/g_triSE");
		gTriDownLeft = setup("/playr/g_triSW");
		
		rTriRight = setup("/playr/r_triangleright");
		rTriUp = setup("/playr/r_triangleup");
		rTriLeft = setup("/playr/r_triangleleft");
		rTriDown = setup("/playr/r_triangledown");
		rTriUpRight= setup("/playr/r_triNE");
		rTriUpLeft = setup("/playr/r_triNW");
		rTriDownRight = setup("/playr/r_triSE");
		rTriDownLeft = setup("/playr/r_triSW");
		
		cTriRight = setup("/playr/c_triangleright");
		cTriUp = setup("/playr/c_triangleup");
		cTriLeft = setup("/playr/c_triangleleft");
		cTriDown = setup("/playr/c_triangledown");
		cTriUpRight= setup("/playr/c_triNE");
		cTriUpLeft = setup("/playr/c_triNW");
		cTriDownRight = setup("/playr/c_triSE");
		cTriDownLeft = setup("/playr/c_triSW");
		
		mTriRight = setup("/playr/m_triangleright");
		mTriUp = setup("/playr/m_triangleup");
		mTriLeft = setup("/playr/m_triangleleft");
		mTriDown = setup("/playr/m_triangledown");
		mTriUpRight= setup("/playr/m_triNE");
		mTriUpLeft = setup("/playr/m_triNW");
		mTriDownRight = setup("/playr/m_triSE");
		mTriDownLeft = setup("/playr/m_triSW");
		
		yTriRight = setup("/playr/y_triangleright");
		yTriUp = setup("/playr/y_triangleup");
		yTriLeft = setup("/playr/y_triangleleft");
		yTriDown = setup("/playr/y_triangledown");
		yTriUpRight= setup("/playr/y_triNE");
		yTriUpLeft = setup("/playr/y_triNW");
		yTriDownRight = setup("/playr/y_triSE");
		yTriDownLeft = setup("/playr/y_triSW");
	}
	public void update() {
		
		if(tankX <= 0 ) {
			int[] choices = {0,2,3,4,5};
			num = change.nextInt(choices.length);
		}
		if(tankX >= (gw.panel.getWidth()-30)) {
			tankX = gw.panel.getWidth() - 30;
			int[] choices = {1,2,3,6,7};
			num = change.nextInt(choices.length);
		}
		if(tankY <= 0) {
			tankY=0;
			int[] choices = {0,1,2,5,7};
			num = change.nextInt(choices.length);
		}	
		if(tankY >= (gw.panel.getHeight()- 30)) {
			tankY = gw.panel.getHeight() - 30;
			int[] choices = {0,1,3,4,6};
			num = change.nextInt(choices.length);
		}
		
		
		if (System.currentTimeMillis() - lastTurn >= 3000) {
			num = change.nextInt(8); 
			lastTurn = System.currentTimeMillis();
		}
		switch (num) {
			case 0: tankX += speed; direction = 0; hitbox.set(tankX +22, tankY+27); healthbar.setLocation(tankX +10, tankY + 5);
				break;
			case 1: tankX -= speed; direction = 1; hitbox.set(tankX +24, tankY +22); healthbar.setLocation(tankX +10, tankY + 5);
				break;
			case 2:	tankY += speed; direction = 2; hitbox.set(tankX +25, tankY +24); healthbar.setLocation(tankX +10, tankY + 5);
				break;
			case 3: tankY -= speed; direction = 3; hitbox.set(tankX +25, tankY +24); healthbar.setLocation(tankX+10, tankY + 5);
				break;
			case 4: 
				tankX += speed; tankY -= speed; direction = 4; hitbox.set(tankX +24, tankY +22); healthbar.setLocation(tankX+10, tankY + 5);
				break;
			case 5: 
				tankX += speed;tankY += speed; direction = 5; hitbox.set(tankX +22, tankY +27); healthbar.setLocation(tankX+10, tankY + 5);
				break;
			case 6:
				tankX -= speed;tankY -= speed; direction = 6; hitbox.set(tankX +24, tankY +22); healthbar.setLocation(tankX+10, tankY + 5);
				break;
			case 7: 
				tankX -= speed;tankY += speed; direction = 7; hitbox.set(tankX +22, tankY +27); healthbar.setLocation(tankX+10, tankY + 5);
				break;
		}
		
		if (projectile.alive == false) {
			projectile.set(tankX, tankY, direction, true, this);
			gw.projectileList.add(projectile);
			gw.playSE(1);
			invincible = true;
			
		}
		if (System.currentTimeMillis() - lastTurn >= 500) {
				invincible = false;
				lastTurn = System.currentTimeMillis();
		}
		if(!invincible)
			if(projectile.bullet.intersects(hitbox.botHitbox)) {
				System.out.println("HITS" + this);
				invincible = true;
				gw.playSE(2);
				tankHealth-=10;
				healthbar.setSize(tankHealth, 5);
				
				
			}
		if (tankHealth == 0) {
			gw.playSE(0);
			gw.entityList.remove(this);
			
		}
	}
	
	public void draw(Graphics2D g2) {
		getImage();
		if(tank.equals("blue")) {
			if (direction == 0) {
				image = bTriRight;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				////g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);

			}
			else if(direction == 1) {
				image = bTriLeft;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 2) {
				image = bTriDown;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 3) {
				image = bTriUp;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 4) {
				image = bTriUpRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 5) {
				image = bTriDownRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);

			}
			else if(direction == 6) {
				image = bTriUpLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 7) {
				image = bTriDownLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
		}
		if (tank.equals("green")){
			if (direction == 0) {
				image = gTriRight;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 1) {
				image = gTriLeft;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 2) {
				image = gTriDown;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
				
			}
			else if(direction == 3) {
				image = gTriUp;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
				
			}
			else if(direction == 4) {
				image = gTriUpRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			
			}
			else if(direction == 5) {
				image = gTriDownRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			
			}
			else if(direction == 6) {
				image = gTriUpLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 7) {
				image = gTriDownLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
					
			}
	}
		if (tank.equals("red")){
			if (direction == 0) {
				image = rTriRight;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 1) {
				image = rTriLeft;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 2) {
				image = rTriDown;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
				
			}
			else if(direction == 3) {
				image = rTriUp;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
				
			}
			else if(direction == 4) {
				image = rTriUpRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			
			}
			else if(direction == 5) {
				image = rTriDownRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			
			}
			else if(direction == 6) {
				image = rTriUpLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 7) {
				image = rTriDownLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
					
			}
	}
		if (tank.equals("cyan")){
			if (direction == 0) {
				image = cTriRight;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 1) {
				image = cTriLeft;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 2) {
				image = cTriDown;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
				
			}
			else if(direction == 3) {
				image = cTriUp;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
				
			}
			else if(direction == 4) {
				image = cTriUpRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			
			}
			else if(direction == 5) {
				image = cTriDownRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			
			}
			else if(direction == 6) {
				image = cTriUpLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 7) {
				image = cTriDownLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
					
			}
	}
		if (tank.equals("magenta")){
			if (direction == 0) {
				image = mTriRight;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 1) {
				image = mTriLeft;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 2) {
				image = mTriDown;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
				
			}
			else if(direction == 3) {
				image = mTriUp;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
				
			}
			else if(direction == 4) {
				image = mTriUpRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			
			}
			else if(direction == 5) {
				image = mTriDownRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			
			}
			else if(direction == 6) {
				image = mTriUpLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 7) {
				image = mTriDownLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
					
			}
	}
		if (tank.equals("yellow")){
			if (direction == 0) {
				image = yTriRight;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 1) {
				image = yTriLeft;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 2) {
				image = yTriDown;
				g2.drawImage(image, tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
				
			}
			else if(direction == 3) {
				image = yTriUp;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
				
			}
			else if(direction == 4) {
				image = yTriUpRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			
			}
			else if(direction == 5) {
				image = yTriDownRight;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			
			}
			else if(direction == 6) {
				image = yTriUpLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
			}
			else if(direction == 7) {
				image = yTriDownLeft;
				g2.drawImage(image,tankX, tankY, gw.tileSize, gw.tileSize, null);
				//g2.draw(hitbox.botHitbox);
				g2.setColor(Color.red);
				g2.fill(healthbar);
				g2.draw(healthbar);
					
			}
	}
	}
}
