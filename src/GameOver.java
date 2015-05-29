
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class GameOver {
	BufferedImage background;

	Game game;
	
	public GameOver (Game game){
		this.game = game;
	}
	
	
	public Rectangle playButton = new Rectangle(475,330, 250, 100);
	public Rectangle helpButton = new Rectangle(475,470, 250, 100);
	public Rectangle quitButton = new Rectangle(475,610, 250, 100);

	
	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			background = loader.loadImage("/ship_gameover.jpg");
		} catch (IOException e) {

			e.printStackTrace();
		}
		g.drawImage(background, 0,0, null);
		Font fnt0 = new Font("arial", Font.BOLD, 100);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("GAME OVER", (Game.WIDTH)/2 , 300);
		
		
		Font fntTOTAL = new Font("arial", Font.BOLD, 100);
		//g.setFont(fntTOTAL);
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		
		g.setFont(fnt1);
		String wow = (game.totalEnemiesKilled).toString();
		g.drawString("ENEMIES KILLED", helpButton.x,helpButton.y-50);
		
		Font fntNUMBER = new Font("arial", Font.BOLD, 70);
		
		g.setFont(fntNUMBER);
		g.drawString(wow, helpButton.x+100,helpButton.y+50);
		g.setFont(fnt1);
		g.drawString("QUIT", quitButton.x+80,quitButton.y+50);
		g2d.draw(quitButton);
		

	}

	public void tick(){

	}
}

