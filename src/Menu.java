import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class Menu {
	BufferedImage background;
	Textures tex;
	Animation anim;
	Random r = new Random();

	
	public Menu(Game game, Textures tex){
		this.tex = tex;
		anim = new Animation(1, tex.f1, tex.f2, tex.f3,tex.f4,tex.f5,tex.f6,tex.f7,tex.f8);
	}
	
	
	public Rectangle playButton = new Rectangle(475,330, 250, 100);
	public Rectangle helpButton = new Rectangle(475,470, 250, 100);
	public Rectangle quitButton = new Rectangle(475,610, 250, 100);

	
	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			background = loader.loadImage("/menu_background.jpg");
		} catch (IOException e) {

			e.printStackTrace();
		}
		g.drawImage(background, 0,0, null);
		Font fnt0 = new Font("arial", Font.BOLD, 100);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("SPACE BLASTER", (Game.WIDTH)/2 -100, 300);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("SHOOT 'EM UP", playButton.x+20,playButton.y+50);
		g2d.draw(playButton);
		g.drawString("HELP?", helpButton.x+80,helpButton.y+50);
		g2d.draw(helpButton);
		g.drawString("GIVE UP?", quitButton.x+60,quitButton.y+50);
		g2d.draw(quitButton);
		
		
		//if(increment-600 == 0)
		//	increment = 0;
		anim.drawAnimation(g, 100, 200, 0);
		anim.drawAnimation(g, 1050, 200, 0);
		//increment+=20;
	}

	public void tick(){
		anim.runAnimation();
	}
}
