import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;




public class Item extends GameObject implements EntityI{

	

	Game game;
	Controller controller;
	Textures tex;
	Random r = new Random();
	BufferedImage heart;
	boolean getIt = false;
	boolean rendered = false;
	//randomized appearance based on 2 kills
	//vertical : from down up, from up down
	//horizontal: left to right, right to left
	
	public Item(double x, double y, Game game, Controller controller, Textures tex){
		super(x,y);
		this.game = game;
		this.controller = controller;
		this.tex = tex;

		
	}
	private boolean render = true;
	private boolean hiding = false;
	private boolean pickedup = false;
	public int try3= 0;
	public void tick(){
		//System.out.println("enemies killed  " + game.getEnemy_killed());
		if(try3 > 1000){
			try3 = 0;
		}
		
		try3++;
		
		
			if( !hiding && Physics.Collision(this, (EntityC)(game.getPlayer() ) )){
				setX((int)getX());
				setY((int)getY());
				//setX(800)
				//setY(20);
				game.getPlayer().setDamage(300);
				render = false;
				pickedup = true;
				hiding= true;
				//System.out.println("PICKED UP -> hiding true -> DON'T RENDER;");
			}
			if( render && !pickedup && try3 > 120) {
				setX((int)getX());
				setY((int)getY());
				//setX(20);
				//setY(20);
				hiding = true;
				render = false;
				//System.out.println(" NOT PICKED UP, TIME RAN OUT -> hiding true -> DON'T RENDER");
			}
			
			if (hiding && try3 > 200 && try3 < 250){
				pickedup = false;
				hiding = false;
				//setX( (int) (getX()+5) );
				//setY((int) (getY()+5));
				//System.out.println(" I AM HIDING WAITING FOR TIMER TO SET LOCATION -> RENDER");
				render = true;
			}
			
			if(getY() > 725)
				setY(0);
			if(getX() > 1245)
				setX(0);
			
			
			setX((int)getX()+2); 
			setY((int)getY()+2); 
			
			//System.out.println("render " + render );
			//System.out.println("pickedup " + pickedup );
			//System.out.println("hiding " + hiding );
			//System.out.println("time " + try3 );
			//System.out.println("x " + x );
			//System.out.println("y " + y );
	}

	
	
	
	public void render(Graphics g){
		
		
		
		if(hiding){
			
		}
		else if (render){
			g.drawImage(tex.heart,(int)x , (int)y , null);
		}
		
		
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void moveHeart(){
		x = r.nextInt(1);
		y = r.nextInt(1);
	}
	
	@Override
	public Rectangle getBounds() {

		// TODO Auto-generated method stub
		  return new Rectangle((int)x, (int)y, 133,133);
	}
}
