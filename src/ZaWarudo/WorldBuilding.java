package ZaWarudo;
import java.awt.image.BufferedImage;
import java.util.Random;

import GameMain.GamePanel;

public class WorldBuilding {
	
	public BufferedImage tileGrass, tileRock, tileTree, tileSpawn, tileStars, grassMonster1, grassMonster2, grassMonster3, grassMonster4;
	public int worldmaxX = 57;
	public int worldmaxY = 57;
	
	public int worldGen[][] = new int[worldmaxX][worldmaxY];
	
	public int enemyX;
	public int enemyY;
	public int enemyX2;
	public int enemyY2;
	public int enemySpeed;
	public int enemyHealth;
	public int rnSpd;
	public boolean spawnStop = false;
	
	public Random rnd = new Random();
	public int random = 0;
	public int spawnX;
	public int spawnY;
	
	public int worldX;
	public int worldY;
	public int screenX;
	public int screenY;
	
	public int tileValue = 0;;
	
	public int enemyWalkAnimation;
	public int animationCount = 1;
	public BufferedImage monsterWalk = grassMonster1;
}
