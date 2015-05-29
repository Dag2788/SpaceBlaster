import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;


public class Enemy extends GameObject implements EntityB {

	
	
	
	Random r = new Random();
	private double velx = r.nextInt(6)+1;
	private double vely = r.nextInt(6)+1;
	private int direction = 0;
	private boolean hit = false; 			//player and ship have this in common and allows them to have their own explotion animation
	private int damage = 0;
	private int shoot_counter = 1;
	Player player;
	private double blastx;
	private double blasty;
	Item heart;
	private Animation anim;
	private BufferedImage enemy, enemy_down, enemy_right, enemy_left, enemy_up;
	private Textures tex;
	Game game;
	private Controller controller;
	Graphics g;

	public Enemy(double x, double y, Textures tex, Game game ){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.player = game.getPlayer();
		this.controller = game.getController();
		this.g = game.getGraphics();
		anim = new Animation(1, tex.f1, tex.f2, tex.f3,tex.f4,tex.f5,tex.f6,tex.f7,tex.f8);
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		enemy = tex.enemy_right;
		enemy = ss.grabImage(320, 500, 350, 250); //enemy ship
		enemy_down = tex.enemy_down;
		enemy_left = tex.enemy_left;
		enemy_right = tex.enemy_right;
		enemy_up = tex.enemy_up;
		

	} // end enemy
	
	public void tick(){
	
		//THIS SETS THE DIRECTION TO THE LEFT AT A RANDOM SPEED EACH PASS AROUND
		if(x < 0){
			x = 1200;
			velx = r.nextInt(12)+1;
			enemy = enemy_left;
			this.direction = 0;
			//THIS SETS THE DIRECTION TO THE RIGHT AT A RANDOM SPEED EACH PASS AROUND
		}
		if(x >= 1250){
			x = 0;
			velx = r.nextInt(12)+1;
			this.direction = 1;
			//THIS SETS THE DIRECTION TO THE DOWNWARDS and UPWARDS AT A RANDOM SPEED EACH PASS AROUND
		}if(y > 750){
			enemy = enemy_up;
			vely = -vely;
		} else if ( y < 0){
			enemy = enemy_down;
			vely = -vely;
		}
		

			
			//MERELY A HEURISTIC, if player within certain coordinates, then shoot to kill...literally
			if ( Math.abs(y - player.getY()) <= 50 ){
				if(player.getX() <= x){
					Enemy_Bullet b = new Enemy_Bullet(this.getX()+80, this.getY()+20, tex, game, this);
					b.setDirection(0);
					controller.addEntity(b);
					
				}else if(player.getX() >= x){
					Enemy_Bullet b = new Enemy_Bullet(this.getX()+80, this.getY()+20, tex, game, this);
					b.setDirection(1);
					controller.addEntity(b);
					
				}
				Sound.BULLET2.play();
			}

			if ( Math.abs(x - player.getX()) <= 50 ){
				if(player.getY() <= y){
					Enemy_Bullet b = new Enemy_Bullet(this.getX()+80, this.getY()+20, tex, game, this);
					b.setDirection(2);
					controller.addEntity(b);
				}else if(player.getY() >= y){
					Enemy_Bullet b = new Enemy_Bullet(this.getX()+80, this.getY()+20, tex, game, this);
					b.setDirection(3);
					controller.addEntity(b);
				}
				Sound.BULLET2.play();
			}
			////'Heuristic" over

			///ENEMY'S HEALTH through bullets
			for(int i=0; i<game.ea.size(); i++){		//BULLETS ARE ENTITYA type
				EntityA temp = game.ea.get(i);			//We compare each to bullet to THIS ship's rectangles
				if(Physics.Collision(temp, this) ){
					hit = true;									//hit is the signals to show fire animation
					game.getController().removeEntity(temp);	//the bullets will dissappear with contact.
					if(Math.abs(velx) > 9)
						velx++;
					else if (Math.abs(vely) > 9)
						vely++;
					
					/*if(r.nextInt(2)+1 > 1)	{					//YET another enemy behavior, change your direction randomly when hit
						velx = -velx;
					}
					else vely = -vely;
					*/
					this.damage++;									//inflic damage on ship
				}
			}
			if(Physics.Collision(game.getPlayer(), this)){			// this checks to see if THIS ship is crashing with the player
				hit = true;	
				game.getPlayer().setHit(true);					//same behavior except only a single check.
				if(r.nextInt(2)+1 > 1)
					velx = -velx;
				else vely = -vely;
				this.damage++;
			}

			//ENEMY MOVEMENT

			y = y + vely;
			x = x + velx;

			//EXPLOTION ANIMATION
			anim.runAnimation();

			///ENEMY'S HEALTH
			if( this.damage >= 10 ){
				Sound.EXPLODE.play();
				game.setBlastx(this.x);
				game.setBlasty(this.y);
				hit = false;
				controller.removeEntity(this);	
				game.totalEnemiesKilled++;
				game.setBlasted(true);								//hit tells us to display fire before erasing.														//the controller will remove this enemy from the map and call our game mechanic
				game.setEnemy_killed(game.getEnemy_killed()+1);

			}

		}  
	
	
	public int getDirection_Enemy() {
		return direction;
	}

	public void setDirection_Enemy(int direction) {
		this.direction = direction;
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
		//if(game.isBlasted() && !hit){
		//	anim.drawAnimation(g, game.getBlastx(), game.getBlastx(), 0);
		//}
		//else
		if( hit ){
			anim.drawAnimation(g, (int)x, (int)y, 0);
			g.drawImage(enemy, (int)x, (int)y, null);
			if(damage < 5)
				hit = false;
		}
		else  
			g.drawImage(enemy, (int)x, (int)y, null);
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
	
	public Rectangle getBounds(){
		//changes made here
			if(direction == 0 || direction == 1)
				return new Rectangle((int)x, (int)y, 150,150);
			else
				return new Rectangle((int)x, (int)y, 150,150);
	}
	//find size of the enemy!!!!!!!!!!!!!!
	


	

}

