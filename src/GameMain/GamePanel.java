package GameMain;
import java.awt.*;
import Entities.Player;
import Entities.PlayerAtk;
import ZaWarudo.Build;
import ZaWarudo.Enemies;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
		
		public JLabel life = new JLabel();
		public JLabel Score = new JLabel();
		public Movement movement = new Movement();
		BufferedImage walter;
		
		int mainTileSize = 16;
		double scaleUp = 7.5;
		
		public int tileSize = (int) (mainTileSize * scaleUp);
		public int rows = 16;
		public int columns =9;
		public int FPS =60;
		public int SpawnCap =0;
		public int screenWidth = rows*tileSize;
		public int screenHeight = columns*tileSize;
		public int atkCD = 0;
		public int enemyCD = 30;
		
		public int playerX = screenWidth/2 - (tileSize/2);
		public int playerY = screenHeight/2 - (tileSize/2);
		public int playerX2 = screenWidth/2 - (tileSize/2);
		public int playerY2 = screenHeight/2 - (tileSize/2);
		public int playerSpeed = 6;
		public Build bl = new Build(this);
		public Player pl = new Player(this, movement);	
		public PlayerAtk plAtk = new PlayerAtk(this, movement);	
		public Enemies en[] = new Enemies[10000];		
		public int worldBorderX = bl.worldmaxX* tileSize;
		public int worldBorderY = bl.worldmaxY* tileSize;
		public int maxX = worldBorderX-screenWidth;
		public int maxY = worldBorderY-screenHeight;
		Thread thread;
		
		
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.addKeyListener(movement);
		this.setFocusable(true);
		this.add(life);
		this.add(Score);
		life.setFont(new Font("Comic Sans",Font.BOLD,tileSize));
		Score.setFont(new Font("Comic Sans",Font.BOLD,tileSize));
		life.setForeground(Color.red);
		
	}
	void RunStart() {
		thread = new Thread(this);
		thread.start();
		
	}
	
	 
	public void EnemyRespawner() {
	 
		if(SpawnCap < en.length) {
		if(enemyCD == 30) {
		
			en[SpawnCap] = new Enemies();	
			en[SpawnCap].Enemies(this, bl);
			//System.out.println("Spawning enemy "+""+SpawnCap);
			enemyCD =0;
			SpawnCap++;
		}
		enemyCD++;
		 }
	 }
	
	@Override
	public void run() {
		 double drawInterval = 1000000000/FPS;
	        double delta = 0;
	        long lastTime = System.nanoTime();
	        long currentTime;        
	        long timer = 0;
	        int drawCount = 0;
		
	        while(thread != null) {
	            currentTime = System.nanoTime();
	            delta += (currentTime - lastTime) / drawInterval;
	            timer += (currentTime - lastTime);
	            lastTime = currentTime;
	            if (delta >= 1) {
	            	EnemyRespawner();
	                update();
	                repaint();
	                delta--;
	                drawCount++;
	            }
	            if (timer >= 1000000000) {
	                drawCount = 0;
	                timer = 0;
	            }
	        }
	    }
	
	public void update() {
		Score.setBounds(1580, 10,tileSize*5,tileSize);
		life.setBounds(10,10,200,tileSize);
		life.setText(pl.playerHP.toString());
		Score.setText(bl.plScore.toString());
		pl.update();
		for(int i = 0; i <en.length;i++) {
			if(en[i] != null) {
				en[i].update();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		bl.draw(g2);
		pl.draw(g2);
		for(int i =0;i <en.length;i++) {
			if(en[i] != null) {
				en[i].draw(g2, this);
			}
		}
		atkCD++;
		if(atkCD >40) {
		if(movement.atkLeft == true || movement.atkRight == true) {
			
			plAtk.draw(g2);
		}
		
		}
	}


}
