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
	}
	
	public void getSprite(){
		
		try {
			
		atkLeft = ImageIO.read(getClass().getResourceAsStream("/sprites/atkLeft.png"));
		atkRight = ImageIO.read(getClass().getResourceAsStream("/sprites/atkRight.png"));
		
		}catch(Exception e) {
			
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		BufferedImage attack = null;
		if(mv.atkLeft ==true) {
			atkX = gp.playerX - gp.tileSize + (int)(gp.tileSize/2.9);
			atkY = gp.playerY;
			attack = atkLeft;
		}
		else if(mv.atkRight ==true) {
			atkX = gp.playerX +gp.tileSize -(int)(gp.tileSize/2.9);
			atkY = gp.playerY;
			attack = atkRight;
		}
		g.drawImage(attack,atkX , atkY, gp.tileSize, gp.tileSize, null);
		
		atkLife++;
		if(atkLife > 10) {
			mv.atkLeft = false;
			mv.atkRight = false;
			atkLife =0;
		}
		
	}
}
