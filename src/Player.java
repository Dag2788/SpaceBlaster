import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;


public class Player extends GameObject implements EntityC{


	
	private double velx;
	private double vely;
	private boolean lookingleft;
	private boolean lookingright;
	private boolean lookingup;
	private boolean lookingdown;
	private Animation anim;
	Random r = new Random();
	private Game game; 
	private boolean hit = false;		//player has a hit variable 

	private int damage = 300;
	public LinkedList<EntityA> ae;
	
	private BufferedImage player, player_down, player_right, player_left, player_up;
	Item heart;
	private Textures tex;
	
	
	
	public Player(double x, double y, Textures tex, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.ae = game.ea;
		anim = new Animation(1, tex.f1, tex.f2, tex.f3,tex.f4,tex.f5,tex.f6,tex.f7,tex.f8); 
		heart = new Item(r.nextInt(1000), r.nextInt(700), game, game.getController(), tex);
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		player = ss.grabImage(50, 10, 250, 260);
		player_down = ss.grabImage(50, 10, 250, 260);
		player_left = ss.grabImage(350, 0, 250, 300);
		player_right = ss.grabImage(700, 0, 250, 300);
		player_up = ss.grabImage(40, 390, 270, 300);

		
	}
	
	public void tick(){
	
		
		x += velx;
		y += vely;
		
		if( x < 0)
			x =1350;
		if( x > 1400)
			x = 0;
		if(y < 0)
			y = 700;
		if(y > 700)
			y = 0;
		
		
		if(Physics.Collision(this, game.eb) ){
			hit = true;
			damage-=2;
			
		}
		
		for(int i=0; i< game.ea.size(); i++){				//This limits my bullets to 700 pixels
			EntityA ob = game.ea.get(i);
			if( Math.abs(this.x - ob.getX()) >= 700){
				game.ea.remove(ob);
			}
		}
		
		for(int i=0; i<game.ed.size(); i++){				//The checks for enemy fire contact
		EntityD temp = game.ed.get(i);
			if(Physics.Collision(this, temp)){
				game.getController().removeEntity(temp);
				hit = true;
				damage-=2;
			}
		}
		
		anim.runAnimation();								//HIT animation
		
		if( damage <= 0){									//game over
			Game.State = Game.State.GAMEOVER;

		}
		
		
		
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public double getVelx() {
		return velx;
	}

	public void setVelx(double velx) {
		this.velx = velx;
	}

	public double getVely() {
		return vely;
	}

	public void setVely(double vely) {
		this.vely = vely;
	}

	public void render(Graphics g){	
			if( hit ){
			g.drawImage(player, (int)x, (int)y, null);	
			anim.drawAnimation(g, (int)x, (int)y, 0);
			}
			else
				g.drawImage(player, (int)x, (int)y, null);

			hit = false;
			
			if(damage < 100 && game.getEnemy_killed() > 3){
				g.drawImage(tex.heart,r.nextInt(1000), r.nextInt(700) , null);
			}
		// to use the textures class you have to select here which image to display!!!
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
	
	public void setPlayer(KeyEvent e){
		int key = e.getKeyCode();
		
		
		if(key == KeyEvent.VK_RIGHT){
			lookingright = true;
			player = player_right;	
			lookingdown = false;
			lookingup = false;
			lookingleft = false;
		} else if(key == KeyEvent.VK_LEFT){
			lookingleft = true;
			player = player_left;	
			lookingdown = false;
			lookingup = false;
			lookingright = false;
		}else if(key == KeyEvent.VK_DOWN){
			lookingdown = true;
			player = player_down;
			lookingup = false;
			lookingleft = false;
			lookingright = false;
		}else if(key == KeyEvent.VK_UP){
			lookingup = true;
			player = player_up;	
			lookingdown = false;
			lookingleft = false;
			lookingright = false;
		}
			
	}

	
	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public boolean isLookingleft() {
		return lookingleft;
	}

	public void setLookingleft(boolean lookingleft) {
		this.lookingleft = lookingleft;
	}

	public boolean isLookingright() {
		return lookingright;
	}

	public void setLookingright(boolean lookingright) {
		this.lookingright = lookingright;
	}

	public boolean isLookingup() {
		return lookingup;
	}

	public void setLookingup(boolean lookingup) {
		this.lookingup = lookingup;
	}

	public boolean isLookingdown() {
		return lookingdown;
	}

	public void setLookingdown(boolean lookingdown) {
		this.lookingdown = lookingdown;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 150,150);
	}
	// find the size of the player!!!!!!!!!


}

