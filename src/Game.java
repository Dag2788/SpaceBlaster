import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */////////////////////////ESSENTIALS///////////////////
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = WIDTH / 12 *9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Space Game";
	public boolean running = false;
	private Thread thread;
	////////////////////////////////////
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private boolean is_Shooting = false; 
	Animation anim;			// this is the animation for fire
	private int counter = 0; ///?
	
	private int enemy_count = 2;	//AI mechanics
	private int enemy_killed = 0;	//AI mechanics
	private boolean chosenHeart = false;
	public boolean isChosenHeart() {
		return chosenHeart;
	}

	public void setChosenHeart(boolean chosenHeart) {
		this.chosenHeart = chosenHeart;
	}

	private Player player;			
	private Controller controller; //the AI of the project
	private Textures tex;		//all the pictures
	private Menu menu; 			//game state
	private HelpSettings help;  //game state
	private GameOver gameover;  // game state
	
	private GameCamara gameCamara;  //for future projects
									
	public LinkedList<EntityA> ea;  //game entities A = good guy's bullets
	public LinkedList<EntityB> eb;	// B = bad guys
	public LinkedList<EntityC> ec;	// C is the main character
	public LinkedList<EntityD> ed;	// D = bullets from bad guys
	public LinkedList<EntityI> ei;	// HEALTH ITEM
	private boolean blasted;
	private double blasty;
	private double blastx;
	Random r = new Random();


	public static enum STATE{
		MENU,
		GAME,
		HELP,
		GAMEOVER
	};
	
	public static STATE State = STATE.MENU;

	
	public STATE getState() {
		return State;
	}

	public void setState(STATE state) {
		State = state;
	}

	private void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
		spriteSheet = loader.loadImage("/ship_spritesheet.png");
		background = loader.loadImage("/background.jpg");
		//spriteSheet = loader.loadImage("/spaceship_bullets.gif");
		}catch(IOException e){
			e.printStackTrace();
		}
		//SpriteSheet ss = new SpriteSheet(spriteSheet);
		//player = ss.grabImage(50, 50, 250, 210);
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		//player = new Player(200,200, this);
		tex = new Textures(this);
		player = new Player(r.nextInt(1600), r.nextInt(750), tex, this);
		controller = new Controller(this, tex);
		menu = new Menu(this, tex);
		help = new HelpSettings(this, tex);
		gameover = new GameOver();
		//gameCamara = new GameCamara(this, 0,0);
		anim = new Animation(2, tex.f1, tex.f2, tex.f3,tex.f4,tex.f5,tex.f6,tex.f7,tex.f8);


		
		ea = controller.getE();
		eb = controller.getB();
		ec = controller.getC();
		ed = controller.getD();
		ei = controller.getI();
		
		controller.addEnemy_count(enemy_count);
		
		controller.addHeart();
			
	

		
	}
	
	public Textures getTex() {
		return tex;
	}

	public void setTex(Textures tex) {
		this.tex = tex;
	}


	public static void main(String args[]){
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame (game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		game.start();
	}
	
	private synchronized void start(){
		
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	
	
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now- lastTime)/ns;
			lastTime = now;
			if(delta >= 1){
				tick(delta);
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private void tick(double delta) {
		
		
		if(State == STATE.MENU){
			menu.tick();
			}
		
		
		if(State == STATE.HELP){
			help.tick();
			}
		
		if(State == STATE.GAMEOVER){
			gameover .tick();
			}
		
		
		if(State == STATE.GAME){
			player.tick();
			controller.tick();
			
			
			
			if(enemy_killed >= enemy_count){
				enemy_count +=1;
				enemy_killed = 0;
				if(enemy_count >= 4){
					enemy_count = 1;
					enemy_killed = 0;
				}
				controller.addEnemy_count(enemy_count);
			}
		
			
			
			
		}
		
		anim.runAnimation();
	}
	
	private void render(){
		if(counter > 50){
			counter = 0;
			blasted = false;
		}
		
		counter++;
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//////////////////////////////////
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		//g.drawImage(player, 100, 100, this);
		g.drawImage(background, 0,0, null);
		
		if(State == STATE.GAME){
			player.render(g);
			controller.render(g);
			g.setColor(Color.gray);
			g.fillRect(5, 5, 300, 50);

			g.setColor(Color.green);
			g.fillRect(5, 5, player.getDamage(), 50);

			g.setColor(Color.white);
			g.drawRect(5, 5, 300, 50);

	
			if(isBlasted() && counter > 0 && counter < 50 ){
				
				anim.drawAnimation(g, getBlastx(), getBlasty(), 0);
			}


		} else if(State == STATE.MENU){

			menu.render(g);

		}else if(State == STATE.HELP){
			help.render(g);
		}else if(State == STATE.GAMEOVER){
			gameover.render(g);
		}
		
		///////////////////////////////////
		g.dispose();
		bs.show();
	}

	
	public double getBlasty() {
		return blasty;
	}

	public void setBlasty(double blasty) {
		this.blasty = blasty;
	}

	public double getBlastx() {
		return blastx;
	}

	public void setBlastx(double blastx) {
		this.blastx = blastx;
	}

	public boolean isBlasted() {
		return blasted;
	}

	public void setBlasted(boolean blasted) {
		this.blasted = blasted;
	}

	public  BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		
		if(State == STATE.GAME){
			player.setPlayer(e);
		if(key == KeyEvent.VK_RIGHT){
			player.setVelx(25);		
		} else if(key == KeyEvent.VK_LEFT){
			player.setVelx(-25);
		}else if(key == KeyEvent.VK_DOWN){
			player.setVely(25);
		}else if(key == KeyEvent.VK_UP){
			player.setVely(-25);
		}else if(key == KeyEvent.VK_SPACE && !is_Shooting){
			is_Shooting = true;
			if(player.isLookingright()){	
				Bullet b = new Bullet(player.getX()+80, player.getY()+20, tex, this, this.controller);
				b.setDirection(1);
				Bullet c = new Bullet(player.getX()+80, player.getY()+140, tex, this, this.controller);
				c.setDirection(1);
				controller.addEntity(b);
				controller.addEntity(c);
			}		
			if(player.isLookingleft()){
				Bullet b = new Bullet(player.getX()+80, player.getY()+20, tex, this, this.controller);
				b.setDirection(0);
				Bullet c = new Bullet(player.getX()+80, player.getY()+140, tex, this, this.controller);
				c.setDirection(0);
				controller.addEntity(b);
				controller.addEntity(c);
				} else
					if(player.isLookingup()){
						Bullet b = new Bullet(player.getX()+40, player.getY()+50, tex, this, this.controller);
						b.setDirection(2);
						Bullet c = new Bullet(player.getX()+150, player.getY()+50, tex, this, this.controller);
						c.setDirection(2);
						controller.addEntity(b);
						controller.addEntity(c);
						
						} else
							if(player.isLookingdown()){
								Bullet b = new Bullet(player.getX()+40, player.getY()+50, tex, this, this.controller);
								b.setDirection(3);
								Bullet c = new Bullet(player.getX()+150, player.getY()+50, tex, this, this.controller);
								c.setDirection(3);
								controller.addEntity(b);
								controller.addEntity(c);
								} 
		}
		}
	}
	
	
	public GameCamara getGameCamara() {
		return gameCamara;
	}

	public void setGameCamara(GameCamara gameCamara) {
		this.gameCamara = gameCamara;
	}
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	
	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		player.setPlayer(e);
		if(key == KeyEvent.VK_RIGHT){
			
			player.setVelx(0);
		} else if(key == KeyEvent.VK_LEFT){
		
			player.setVelx(0);		
		}else if(key == KeyEvent.VK_DOWN){
	
			player.setVely(0);		
		}else if(key == KeyEvent.VK_UP){
	
			player.setVely(0);
			
		}else if(key == KeyEvent.VK_SPACE){
	
			is_Shooting = false;
		}
	}
	
	
}
