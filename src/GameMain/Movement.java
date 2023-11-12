package GameMain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener{

	 public boolean up;
	 public boolean down;
	 public boolean left;
	 public boolean right;
	 public boolean atkLeft;
	 public boolean atkRight;
	 
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int press = e.getKeyCode();
		
		
		if(press == KeyEvent.VK_A) {
			left = true;
		}
		if(press == KeyEvent.VK_D) {
			right = true;
		}
		if(press == KeyEvent.VK_W) {
			up = true;
		}
		if(press == KeyEvent.VK_S) {
			down = true;
		}
		if(press == KeyEvent.VK_LEFT) {
			atkLeft = true;
		}
		if(press == KeyEvent.VK_RIGHT) {
			atkRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int release = e.getKeyCode();
		
		if(release == KeyEvent.VK_W) {		
			up = false;	
			
		}
			if(release == KeyEvent.VK_S) {
			down = false;
			
		}
		if(release == KeyEvent.VK_A) {
			left = false;
			
		}
		if(release == KeyEvent.VK_D) {
			right = false;
		}
		
	}

}
