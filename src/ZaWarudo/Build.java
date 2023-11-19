package ZaWarudo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Entities.Entity;
import Entities.Player;
import GameMain.GamePanel;
import java.util.*;

public class Build extends WorldBuilding{
	
	GamePanel gp;
	
	public Build(GamePanel gp) {
		this.gp = gp;	
		
		getTileSprites();
		randomizeGen();
	}
	
	public void getTileSprites(){
		
		try {
			
			tileTree = ImageIO.read(getClass().getResourceAsStream("/sprites/tree.png"));
			tileGrass = ImageIO.read(getClass().getResourceAsStream("/sprites/grass.png"));
			tileRock = ImageIO.read(getClass().getResourceAsStream("/sprites/spawnTile.png"));
			tileSpawn = ImageIO.read(getClass().getResourceAsStream("/sprites/rock.png"));
			tileStars = ImageIO.read(getClass().getResourceAsStream("/sprites/stars.png"));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void randomizeGen() {
		
		for(int y = 0; y<worldmaxY; y++) {
			for(int x = 0; x<worldmaxX; x++) {
				
				random = rnd.nextInt(10);
				worldGen[x][y]= random;
			}
		}
	}
	
	public void draw(Graphics2D g) {
		BufferedImage randomTile = null;
		
		for(int y = 0; y<worldmaxY; y++) {
			
			for(int x = 0; x<worldmaxX; x++) {
				
				worldGen[29][29] = 11;
				
				if(x == 7 || x == worldmaxX-8 || y == 7|| y == worldmaxY-8 ) {
				worldGen[x][y] = 10;
				}
				
				if(x <7 || x > worldmaxX-8 || y < 7|| y > worldmaxY-8 ) {
					worldGen[x][y] = 12;
					}
					
				
					
				if(worldGen[x][y] < 1) {
						randomTile = tileTree;
				}
					
				else if(worldGen[x][y] >= 1 && worldGen[x][y] <= 9 ) {
					randomTile = tileGrass;
					}
				else if(worldGen[x][y] == 10) {
					randomTile = tileRock;
					}
				else if(worldGen[x][y] == 12) {
					randomTile = tileStars;
					}
				else if(worldGen[29][29] == 11) {
					randomTile = tileSpawn;
					}
				
			
				
				 worldX = x*gp.tileSize;
				 worldY = y*gp.tileSize;
				 screenX = worldX - gp.pl.worldX +gp.playerX;
				 screenY = worldY - gp.pl.worldY+gp.playerY;

				if(screenX >= 0 -gp.tileSize&& screenX <= gp.screenWidth&& screenY >=0-gp.tileSize && screenY <=gp.screenHeight) {
					gp.pl.collisionCheck();
				g.drawImage(randomTile, screenX, screenY, gp.tileSize, gp.tileSize,null);
				plScore = plScore;
				System.out.println(plScore);
				
				}							
			}
		}		
	}
	
}
