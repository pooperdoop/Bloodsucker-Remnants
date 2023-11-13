package Entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameMain.GamePanel;
import GameMain.Movement;

public class PlayerAtk extends Entity {
	
	GamePanel gp;
	Movement mv;
	
	public PlayerAtk(GamePanel gp, Movement mv) {
		this.gp = gp;
		this.mv = mv;
		getSprite();
		getValues();

	}
	
	void getValues() {
		atkX2 = gp.pl.worldX; 
		atkY2 = gp.pl.worldY; 
	}
	
	public void getSprite(){
		
		try {
			
		atkLeft = ImageIO.read(getClass().getResourceAsStream("/sprites/atkLeft.png"));
		atkRight = ImageIO.read(getClass().getResourceAsStream("/sprites/atkRight.png"));
		
		}catch(Exception e) {
			
		}
		
	}
	
	public void draw(Graphics2D gAtk) {
		
		atkX2 = gp.pl.worldX; 
		atkY2 = gp.pl.worldY; 
		
		BufferedImage attack = null;
		if(mv.atkLeft ==true) {
			atkX = atkX2 - gp.pl.worldX+gp.playerX- (int)(gp.tileSize/2.5);
			atkY = atkY2 -gp.pl.worldY+gp.playerY;
			attack = atkLeft;
		}
		else if(mv.atkRight ==true) {
			atkX = atkX2 - gp.pl.worldX+gp.playerX+ (int)(gp.tileSize/2.5);
			atkY = gp.playerY;
			attack = atkRight;
		}
		gAtk.drawImage(attack,atkX , atkY, gp.tileSize, gp.tileSize, null);
		
		atkLife++;
		if(atkLife > 10) {
			mv.atkLeft = false;
			mv.atkRight = false;
			atkLife =0;
			gp.atkCD =0;
			atkX = 1000000000;
			atkY = 1000000000;
		}
		
	}
}
