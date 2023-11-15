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
		enemySkin = rn.nextInt(9);
		rnSpd = rn.nextInt(2)+1;
		
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
		snowEnemy = ImageIO.read(getClass().getResourceAsStream("/sprites/snowEnemy.png"));
		
		}catch(Exception e) {
			
		}
	}
	
	
	public void draw(Graphics2D g, GamePanel gp) {
		
		if(spawnStop ==false) {
		
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
		
		if(gp.movement.atkLeft == true && enemyX >= gp.plAtk.atkX-(int)(gp.tileSize/2) && enemyX <= gp.plAtk.atkX&&  enemyY >= gp.plAtk.atkY-gp.tileSize && enemyY <= gp.plAtk.atkY+gp.tileSize || 
				gp.movement.atkRight == true && enemyX >= gp.plAtk.atkX && enemyX <= gp.plAtk.atkX+(int)(gp.tileSize/2)&&  enemyY >= gp.plAtk.atkY-gp.tileSize && enemyY <= gp.plAtk.atkY+gp.tileSize) {
			enemyHealth =-1;
			spawnStop = true;
		}
		
		if(damaged == false) {
			damageCheck();
			} else {
				Iframes++;
				if(Iframes >= 50) {
					damaged = false;
					Iframes = 0;
				}
			}
		}
	}
	
	public void update() {
		
		if(spawnStop ==false) {
		if(enemySkin >2) {
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
			}else {
			monsterWalk = snowEnemy;
	  		} 
		}
	}
	
	public void damageCheck() {
		
		if(enemyX2 >= gp.pl.worldX-gp.pl.hitbox.width &&enemyX2 <= gp.pl.worldX+gp.pl.hitbox.width && enemyY2 >= gp.pl.worldY-gp.pl.hitbox.y&& enemyY2 <= gp.pl.worldY+gp.pl.hitbox.y) {
			System.out.println("player hit"+ gp.pl.playerHP);
			gp.pl.playerHP--;
			damaged = true;
			if(gp.pl.playerHP == 0) {
				System.out.println("YOU LOST");
				System.exit(0);
			}
		}	
		
	}
}
