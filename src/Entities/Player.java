package Entities;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import GameMain.GamePanel;
import GameMain.Movement;

public class Player extends Entity {
	
	GamePanel gp;
	Movement moving;
	
	public Player(GamePanel gp, Movement moving){
		this.gp = gp;
		this.moving = moving;
		
		getValues();
		getSprite();
	}
	
	public void getValues() {
		
		 worldX = gp.tileSize*29;
		 worldY = gp.tileSize*29;
		 speed = 5;
		 
		 hitbox = new Rectangle();
		 hitbox.x = 37;
		 hitbox.y = 90;
		 hitbox.width = 40;
		 hitbox.height = 20;
		 		 
		
	}

	public void getSprite() {
		try {
			
			walkleft1 = ImageIO.read(getClass().getResourceAsStream("/sprites/penguwalkleft1.png"));
			walkleft2 = ImageIO.read(getClass().getResourceAsStream("/sprites/penguwalkleft2.png"));
			walkright1 = ImageIO.read(getClass().getResourceAsStream("/sprites/penguwalkright1.png"));
			walkright2 = ImageIO.read(getClass().getResourceAsStream("/sprites/penguwalkright2.png"));
			walkdown1 = ImageIO.read(getClass().getResourceAsStream("/sprites/penguwalkdown1.png"));
			walkdown2 = ImageIO.read(getClass().getResourceAsStream("/sprites/penguwalkdown2.png"));
			walkup1 = ImageIO.read(getClass().getResourceAsStream("/sprites/penguwalkup1.png"));
			walkup2 = ImageIO.read(getClass().getResourceAsStream("/sprites/penguwalkup2.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void update() {
		
			
			if(moving.up == true) {

				direction = 1;
				
			}
			if(moving.down == true) {
				
					
				direction = 2;
				
			}
			if(moving.left == true) {

				direction = 3;
				
			}
			if(moving.right == true) {

				direction = 4;
				
			}
			
			
			colliding = false;
			collisionCheck();
			
			if(colliding ==false) {
				
				if(moving.up == true) {
					worldY -= speed;
				}
				if(moving.down == true) {
						worldY += speed;
				}
				if(moving.left == true) {
						worldX -= speed;
					
				}
				if(moving.right == true) {
						worldX += speed;													
				}
			}
		
		num++;
		if(num > 15) {
			if(counter == 1) {
				counter  = 2;
			}
			else if(counter == 2 ) {
				counter = 1;
			}
			num=0;
			}
		}
	
	public void draw(Graphics2D g2) {
		
		if(direction == 1) {
			if(counter ==1) {
				facing = walkup1;

			}
			if(counter == 2) {
				facing = walkup2;

			}
		}
		
		if(direction == 2) {
			if(counter ==1) {
				facing = walkdown1;

			}
			if(counter == 2) {
				facing = walkdown2;

			}
		}

		if(direction == 3) {
			if(counter ==1) {
				facing = walkleft1;

			}
			if(counter == 2) {
				facing = walkleft2;

			}
		}
		
		if(direction == 4) {
			if(counter ==1) {
				facing = walkright1;

			}
			if(counter == 2) {
				facing = walkright2;

			}
		}
			
			 
		g2.drawImage(facing, gp.playerX, gp.playerY, gp.tileSize, gp.tileSize, null);
	}
	
	public void collisionCheck(){
		
		int hitboxleftTop = worldX+hitbox.x;
		int hitboxrightTop = worldX+hitbox.x+hitbox.width;
		int hitboxleftBottom = worldY +hitbox.y;
		int hitboxrightBottom = worldY +hitbox.y + hitbox.height;
		
		int hitboxLeft = hitboxleftTop/gp.tileSize;
		int hitboxRight = hitboxrightTop/gp.tileSize;
		int hitboxTop = hitboxleftBottom/gp.tileSize;
		int hitboxBottom = hitboxrightBottom/gp.tileSize;

		
		
		if(direction == 1) {
			
			 hitboxTop = (hitboxleftBottom-speed)/gp.tileSize;
					
			if(gp.bl.worldGen[hitboxLeft][hitboxTop] == 0 ||gp.bl.worldGen[hitboxRight][hitboxTop] == 0 || gp.bl.worldGen[hitboxLeft][hitboxTop] == 10 ||gp.bl.worldGen[hitboxRight][hitboxTop] == 10 ) {
				colliding = true;
			}
		}
		
		if(direction == 2) {
			
			hitboxBottom = (hitboxrightBottom+speed)/gp.tileSize;
					
			if(gp.bl.worldGen[hitboxLeft][hitboxBottom] == 0 ||gp.bl.worldGen[hitboxRight][hitboxBottom] == 0 || gp.bl.worldGen[hitboxLeft][hitboxBottom] == 10 ||gp.bl.worldGen[hitboxRight][hitboxBottom] == 10 ) {
				colliding = true;
			}
		}
		
		if(direction == 3) {
			
			hitboxLeft =(hitboxleftTop-speed)/gp.tileSize;
					
			if(gp.bl.worldGen[hitboxLeft][hitboxTop] == 0 ||gp.bl.worldGen[hitboxLeft][hitboxBottom] == 0|| gp.bl.worldGen[hitboxLeft][hitboxTop] == 10 ||gp.bl.worldGen[hitboxLeft][hitboxBottom] == 10 ) {
				colliding = true;
			}
		}
		
		if(direction == 4) {
			
			hitboxRight = (hitboxrightTop+speed)/gp.tileSize;
					
			if(gp.bl.worldGen[hitboxRight][hitboxTop] == 0 ||gp.bl.worldGen[hitboxRight][hitboxBottom] == 0 || gp.bl.worldGen[hitboxRight][hitboxTop] == 10 ||gp.bl.worldGen[hitboxRight][hitboxBottom] == 10) {

				colliding = true;
			}
		}
		
	}
	
}
