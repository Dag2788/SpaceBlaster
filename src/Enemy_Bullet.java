import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Enemy_Bullet extends GameObject implements EntityD{
	

	private int animation = 0;
	private boolean no_more_changes = false;
	private boolean no_more_explotion = false;
	private int direction;
	private Textures tex;
	private Game game;
	public LinkedList<EntityD> ed;
	Enemy enemy;
	
	private final int LEFT = 0;
	private final int RIGHT = 1;
	private final int UP = 2;
	private final int DOWN = 3;
	
	BufferedImage image,image2,image3,image4,image5,image6,image7,image8,
	image_left,image2_left,image3_left,image4_left,image5_left,image6_left,image7_left,
	image8_left,image7_down,image8_down,
	image_up,image2_up,image3_up,image4_up,image5_up,image6_up,
	image_down,image2_down,image3_down,image4_down,image5_down,image6_down,
	f1,f2,f3,f4,f5,f6,f7,f8,fl1,fl2,fl3,fl4,fl5,fl6,fl7,fl8, fire,image7_up,image8_up;
	
	public Enemy_Bullet(double x, double y, Textures tex, Game game, Enemy enemy){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.direction = 0;
		this.ed = game.ed;
		this.enemy = enemy;
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		//to the right
		image = tex.eb;
		image2 = tex.eb2;
		image3 = tex.eb3;
		image4 = tex.eb4;
		image5 = tex.eb5;
		image6 = tex.eb6;
		image7 = tex.eb7;
		image8 = tex.eb8;
		
		image_left = tex.ebl;
		image2_left = tex.ebl2;
		image3_left = tex.ebl3;
		image4_left = tex.ebl4;
		image5_left = tex.ebl5;
		image6_left = tex.ebl6;
		image7_left = tex.ebl7;
		image8_left = tex.ebl8;
		
		image_down = tex.ebd;
		image2_down = tex.ebd2;
		image3_down = tex.ebd3;
		image4_down = tex.ebd4;
		image5_down = tex.ebd5;
		image6_down = tex.ebd6;
		image7_down = tex.ebd7;
		image8_down = tex.ebd8;
		
		
		image8_up = tex.ebu8;
		image7_up = tex.ebu7;
		image6_up = tex.ebu6;
		image5_up = tex.ebu5;
		image4_up = tex.ebu4;
		image3_up = tex.ebu3;
		image2_up = tex.ebu2;
		image_up = tex.ebu;
		
		
		f1 = tex.f1;
		f2 = tex.f2;
		f3 = tex.f3;
		f4 = tex.f4;
		f5 = tex.f1;
		f6 = tex.f2;
		f7 = tex.f3;
		f8 = tex.f4;
		
		fl1 = tex.f1;
		fl2 = tex.f2;
		fl3 = tex.f3;
		fl4 = tex.f4;
		fl5 = tex.f1;
		fl6 = tex.f2;
		fl7 = tex.f3;
		fl8 = tex.f4;
		fire = f1;
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

		if(Physics.Collision_fireAndShip(this, game.ec)){
			ed.remove(this);

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

		}else if (animation >= 3 && animation <= 6 && !no_more_changes){
			if(this.getDirection() == DOWN)
				image = image2_down;
			else if (this.getDirection() == UP)
				image = image2_up;
			else if (this.getDirection() == LEFT)
				image = image2_left;
			else if (this.getDirection() == RIGHT)
				image = image2;
		}else if (animation >= 6 && animation <= 9 && !no_more_changes){
			if(this.getDirection() == DOWN)
				image = image3_down;
			else if (this.getDirection() == UP)
				image = image3_up;
			else if (this.getDirection() == LEFT)
				image = image3_left;
			else if (this.getDirection() == RIGHT)
				image = image3;
		}else if (animation >= 9 && animation <= 12 && !no_more_changes){
			if(this.getDirection() == DOWN)
				image = image4_down;
			else if (this.getDirection() == UP)
				image = image4_up;
			else if (this.getDirection() == LEFT)
				image = image4_left;
			else if (this.getDirection() == RIGHT)
				image = image4;
		}else if (animation >= 12 && animation <= 15 && !no_more_changes){
			if(this.getDirection() == DOWN)
				image = image5_down;
			else if (this.getDirection() == UP)
				image = image5_up;
			else if (this.getDirection() == LEFT)
				image = image5_left;
			else if (this.getDirection() == RIGHT)
				image = image5;
		}else if (animation >= 15 && animation <= 20 && !no_more_changes){
			if(this.getDirection() == DOWN)
				image = image6_down;
			else if (this.getDirection() == UP)
				image = image6_up;
			else if (this.getDirection() == LEFT)
				image = image6_left;
			else if (this.getDirection() == RIGHT)
				image = image6;
		}else if (animation >= 20 && animation <= 25 && !no_more_changes){
			if(this.getDirection() == DOWN)
				image = image7_down;
			else if (this.getDirection() == UP)
				image = image7_up;
			else if (this.getDirection() == LEFT)
				image = image7_left;
			else if (this.getDirection() == RIGHT)
				image = image7;
		}else if (animation >= 25 && animation <= 30 && !no_more_changes){
			no_more_changes = true;
			if(this.getDirection() == DOWN)
				image = image8_down;
			else if (this.getDirection() == UP)
				image = image8_up;
			else if (this.getDirection() == LEFT)
				image = image8_left;
			else if (this.getDirection() == RIGHT)
				image = image8;
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

