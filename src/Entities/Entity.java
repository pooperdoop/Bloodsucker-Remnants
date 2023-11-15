package Entities;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
		
		BufferedImage facing = null;
		public BufferedImage walkleft1, walkleft2, walkright1, walkright2, walkup1, walkup2, walkdown1, walkdown2, atkLeft, atkRight;
		public int worldX;
		public int worldY;
		public int playerHP = 10;
		public int spawnCap=20;
		public int spawned = spawnCap;
		public boolean cornerX = false;
		public boolean cornerY = false;
		public boolean colliding = false;
		int speed;
		int counter = 1, num =1;
		int direction = 2;
		public Rectangle hitbox;
		
		public int atkX;
		public int atkY;
		public int atkX2;
		public int atkY2;
		public int atkLife = 0;
		
		public int hitboxleftTop;
		public int hitboxrightTop;
		public int hitboxleftBottom;
		public int hitboxrightBottom;
		
		public int hitboxLeft;
		public int hitboxRight;
		public int hitboxTop;
		public int hitboxBottom ;
	
}
