package ZaWarudo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.imageio.ImageIO;

import Entities.Entity;
import GameMain.GamePanel;

public class Enemies extends WorldBuilding{

	GamePanel gp;
	
	public void Enemies(GamePanel gp) {
		this.gp = gp;
		
		
		getSprite();
		getValues();
	}
	
	void getValues() {
		Random rn = new Random();
		int rng = rn.nextInt(2);
		int rng2 = rn.nextInt(2);
		int rng3 = rn.nextInt(2);
		int rngX = rn.nextInt(16);
		int rngY = rn.nextInt(9);
		rnSpd = rn.nextInt(3)+1;
		
		//X Wins
		if(rng == 0) {
			if(rng2 == 0) {
			enemyY2 =  gp.pl.worldY-gp.playerY-gp.tileSize;
			}else {
				enemyY2 =  gp.pl.worldY+gp.playerY+gp.tileSize;
			}
			
			if(rng3 == 0) {
				enemyX2 = gp.pl.worldX+(rngX*gp.tileSize) -gp.playerX;
			}else {
				enemyX2 = gp.pl.worldX+-(rngX*gp.tileSize) +gp.playerX;
			}
			
		} else {
			if(rng2 == 0) {
			enemyX2 =  gp.pl.worldX-gp.playerX-gp.tileSize;
			}else {
				enemyX2 =  gp.pl.worldX+gp.playerX+gp.tileSize;
			}
			
			if(rng3 == 0) {
				enemyY2 = gp.pl.worldY+(rngY*gp.tileSize) -gp.playerY;
			}else {
				enemyY2 = gp.pl.worldY+-(rngY*gp.tileSize) +gp.playerY;
			}
		}
		
		
		enemySpeed = rnSpd;
		enemyHealth =1;
		
	}
	
	
	void getSprite(){
		
		try {
		grassMonster1 = ImageIO.read(getClass().getResourceAsStream("/sprites/grassMonster1.png"));
		grassMonster2 = ImageIO.read(getClass().getResourceAsStream("/sprites/grassMonster2.png"));
		grassMonster3 = ImageIO.read(getClass().getResourceAsStream("/sprites/grassMonster3.png"));
		grassMonster4 = ImageIO.read(getClass().getResourceAsStream("/sprites/grassMonster4.png"));
		
		}catch(Exception e) {
			
		}
	}
	
	
	public void draw(Graphics2D g, GamePanel gp) {
		
		if(spawnStop ==false) {
		if(enemyX2>gp.pl.worldX+10 ||enemyY2 >gp.pl.worldY+10|| enemyX2<gp.pl.worldX-10|| enemyY2 < gp.pl.worldY-10) {
			enemySpeed =rnSpd;
		}else {
			enemySpeed =1;
		}
		
		if(enemyHealth > 0) {
		enemyX = enemyX2- gp.pl.worldX+gp.playerX;
		enemyY = enemyY2- gp.pl.worldY+gp.playerY;
		
		if(enemyX2 <gp.pl.worldX) {
			enemyX2+=enemySpeed;
		}
		
		if(enemyX2 >gp.pl.worldX) {
			enemyX2-=enemySpeed;
		}
		
		if(enemyY2 <gp.pl.worldY) {
			enemyY2+=enemySpeed;
		}
		
		if(enemyY2>gp.pl.worldY) {
			enemyY2-=enemySpeed;
		}
		
		g.drawImage(monsterWalk,enemyX,enemyY, gp.tileSize,gp.tileSize ,null);
		}
		
		if(enemyX2 == gp.pl.worldX &&  enemyY2 == gp.pl.worldY ) {
			enemyHealth =-1;
			spawnStop = true;
		}
		
		if(spawnStop ==true) {
			gp.EnemyRespawner();
		//	System.out.println("Respawning" +" "+ gp.SpawnCap );
			gp.SpawnCap++;
		}

	}
	}
	
	public void update() {
		
		if(spawnStop ==false) {
		if(enemyX2 <gp.pl.worldX) {
			if(animationCount ==1) {
			monsterWalk = grassMonster2;
			} else {
				monsterWalk = grassMonster1;
			}
		}
		
		if(enemyX2 >gp.pl.worldX) {
			if(animationCount ==1) {
			monsterWalk = grassMonster4;
			} else {
				monsterWalk = grassMonster3;
			}
		}
		
		enemyWalkAnimation++;
		if(enemyWalkAnimation >25) {
			if(animationCount ==1) {
				animationCount = 2;
			} else {
				animationCount =1;
			}
			enemyWalkAnimation = 0;
		}
	  }
	}
	
}
