package Player;

import java.awt.Rectangle;

public class Hitbox { 
	Rectangle botHitbox;
	public Hitbox() {
		botHitbox = new Rectangle(100,100,30,30);
	}
	
	public void set(int x , int y) {
		botHitbox.setLocation(x, y);
	}

}
