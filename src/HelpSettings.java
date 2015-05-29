import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class HelpSettings {
	BufferedImage background;
	Textures tex;
	Animation anim;
	Animation plane;
	Animation enemy_dying;
	private int x = 0;
	private int y = 0;
	private int a = 400;
	private int b = 500;
	private int xvel = 4;
	private int yvel = 4;
	Random r = new Random();
	
	public HelpSettings(Game game, Textures tex){
		this.tex = tex;
		anim = new Animation(1, tex.f1, tex.f2, tex.f3,tex.f4,tex.f5,tex.f6,tex.f7,tex.f8);
		enemy_dying = new Animation (1,tex.enemy_left, tex.enemy_left, tex.enemy_left,tex.enemy_left,tex.enemy_left,tex.enemy_left);
		plane = new Animation (1,tex.player, tex.player);
	}
	

	public Rectangle helpButton = new Rectangle(475,470, 250, 100);
	public Rectangle quitButton = new Rectangle(475,610, 250, 100);

	
	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			background = loader.loadImage("/ship_settingsMenu.jpg");
		} catch (IOException e) {

			e.printStackTrace();
		}
		g.drawImage(background, 0,0, null);
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("USE THE ARROW KEYS TO MOVE", (Game.WIDTH)/2 -100, 100);
		g.drawString("USE THE SPACE BAR TO SHOOT", (Game.WIDTH)/2 -100, 200);
		g.drawString("YOU CAN LOOP THROUGH THE MAP", (Game.WIDTH)/2 -100, 300);
		Font fnt2 = new Font("arial", Font.BOLD, 70);
		g.setFont(fnt2);
		g.drawString("GIVE 'EM HELL", (Game.WIDTH)/2 +70, 400);
		
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("BACK", helpButton.x+80,helpButton.y+50);
		g2d.draw(helpButton);
		g.drawString("QUIT", quitButton.x+80,quitButton.y+50);
		g2d.draw(quitButton);
		
		
		//if(increment-600 == 0)
		//	increment = 0;
		
		if(x < 0 )
			x = 1300;
		if(x > 1200)
			x = 0;
		
		if(a < 0 )
			a = 1300;
		if(a > 1200)
			a = 0;
		
		if(y < 0 )
			y = 750;
		if(y > 750)
			y = 0;
		
		if(b < 0 )
			b = 750;
		if(b > 750)
			b = 0;
		x += xvel;
		y += yvel;
		
		a += xvel;
		b += yvel;
		
		//anim.drawAnimation(g, 100, 200, 0);
		//anim.drawAnimation(g, 1050, 200, 0);
		enemy_dying.drawAnimation(g, x, y, 0);
		plane.drawAnimation(g, a, b, 0);
		//increment+=20;
	}

	public void tick(){
		anim.runAnimation();
		enemy_dying.runAnimation();
		plane.runAnimation();
	}
}
