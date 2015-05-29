import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Bullet extends GameObject implements EntityA{
	

	private int animation = 0;
	private boolean no_more_changes = false;
	Player player;
	private Controller controller;
	private int direction;
	private Textures tex;
	private Game game;
	public LinkedList<EntityA> ea;
	
	private final int LEFT = 0;
	private final int RIGHT = 1;
	private final int UP = 2;
	private final int DOWN = 3;
	
	BufferedImage image,image2,image3,image4,image5,image6,
	image_left,image2_left,image3_left,image4_left,image5_left,image6_left,
	image_up,image2_up,image3_up,image4_up,image5_up,image6_up,
	image_down,image2_down,image3_down,image4_down,image5_down,image6_down;
	
	public Bullet(double x, double y, Textures tex, Game game, Controller controller){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.direction = 0;
		this.controller = controller;
		this.ea = game.ea;
		player = game.getPlayer();
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		//to the right
		image = ss.grabImage(350, 330, 40, 100);
		image2 = ss.grabImage(390, 330, 25, 100);
		image3 = ss.grabImage(415, 330, 30, 100);
		image4 = ss.grabImage(445, 330, 60, 100);
		image5 = ss.grabImage(505, 330, 65, 100);
		image6 = ss.grabImage(570, 330, 70, 100);
		
		image_left = ss.grabImage(620, 420, 40, 40); //first t the left
		image2_left = ss.grabImage(600, 420, 20, 40); //left2
		image3_left = ss.grabImage(562, 430, 40, 40);  //left 3
		image4_left = ss.grabImage(500, 420, 70, 50);  //left4
		image5_left = ss.grabImage(440, 420, 65, 60);  //left5
		image6_left = ss.grabImage(350, 420, 75, 60);  //left6
		
		image_down = ss.grabImage(750, 330, 100, 60); //down 1
		image2_down = ss.grabImage(750, 395, 100, 20); // done 2
		image3_down = ss.grabImage(750, 420, 100, 30);	// down3
		image4_down = ss.grabImage(750, 450, 100, 65); //down4
		image5_down = ss.grabImage(750, 515, 100, 60); //down5
		image6_down = ss.grabImage(745, 575, 105, 70); //down6
		
		
		image6_up = ss.grabImage(670, 330, 40, 100); // up6
		image5_up = ss.grabImage(670, 440, 35, 60);  //up5
		image4_up = ss.grabImage(670, 505, 35, 60);  //up4
		image3_up = ss.grabImage(670, 570, 35, 35);  //up3
		image2_up = ss.grabImage(670, 600, 35, 25);  //up2
		image_up = ss.grabImage(670, 625, 30, 30);  //up1
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void tick(){
		if(animation >= 60){
			animation = 0;
		}

	
		if(Physics.Collision(this, game.eb)){
			ea.remove(this);
			
		}
		
		
	
		

		if(this.getDirection() == DOWN)
			y += 20;
		else if (this.getDirection() == UP)
			y -= 20;
		if(this.getDirection() == RIGHT)
			x += 20;
		else if (this.getDirection() == LEFT)
			x -= 20;


		animation++;

		if (animation >= 0 && animation <= 3 && !no_more_changes){
			//do nothing
			if(this.getDirection() == DOWN)
				image = image_down;
			else if (this.getDirection() == UP)
				image = image_up;
			else if (this.getDirection() == LEFT)
				image = image_left;
			else if (this.getDirection() == RIGHT){
				//do nothing
			}

		}else if (animation >= 3 && animation <= 7 && !no_more_changes){
			if(this.getDirection() == DOWN)
				image = image2_down;
			else if (this.getDirection() == UP)
				image = image2_up;
			else if (this.getDirection() == LEFT)
				image = image2_left;
			else if (this.getDirection() == RIGHT)
				image = image2;
		}else if (animation >= 7 && animation <= 12 && !no_more_changes){
			if(this.getDirection() == DOWN)
				image = image3_down;
			else if (this.getDirection() == UP)
				image = image3_up;
			else if (this.getDirection() == LEFT)
				image = image3_left;
			else if (this.getDirection() == RIGHT)
				image = image3;
		}else if (animation >= 12 && animation <= 16 && !no_more_changes){
			if(this.getDirection() == DOWN)
				image = image4_down;
			else if (this.getDirection() == UP)
				image = image4_up;
			else if (this.getDirection() == LEFT)
				image = image4_left;
			else if (this.getDirection() == RIGHT)
				image = image4;
		}else if (animation >= 16 && animation <= 20 && !no_more_changes){
			if(this.getDirection() == DOWN)
				image = image5_down;
			else if (this.getDirection() == UP)
				image = image5_up;
			else if (this.getDirection() == LEFT)
				image = image5_left;
			else if (this.getDirection() == RIGHT)
				image = image5;
		}else if (animation >= 20 && animation <= 24 && !no_more_changes){
			no_more_changes = true;
			if(this.getDirection() == DOWN)
				image = image6_down;
			else if (this.getDirection() == UP)
				image = image6_up;
			else if (this.getDirection() == LEFT)
				image = image6_left;
			else if (this.getDirection() == RIGHT)
				image = image6;
		}


	}
	
	public void render(Graphics g){
		//g.drawRect((int)x, (int)y, 32, 32);
		g.drawImage(image, (int)x, (int)y, null);
		//to use the textures class I have to choose which image to use here
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32,32);
	}
	//find size of the bullet!!!
	
}
