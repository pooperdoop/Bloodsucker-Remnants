package GameMain;
import javax.swing.*;

public class MainPanel{

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Bloodsucker Remnants");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		GamePanel gp = new GamePanel();
		frame.setUndecorated(true);
		frame.add(gp);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		gp.RunStart();
		gp.EnemySpawner();
	}

}
