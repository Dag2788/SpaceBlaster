import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Textures {

	private SpriteSheet ss;
	
	public BufferedImage player, missile, enemy,enemy_down,enemy_up,enemy_left,enemy_right, player_down, player_right, player_left, player_up,image,image2,image3,image4,image5,image6,
	image_left,image2_left,image3_left,image4_left,image5_left,image6_left,
	image_up,image2_up,image3_up,image4_up,image5_up,image6_up,
	image_down,image2_down,image3_down,image4_down,image5_down,image6_down,
	eb,eb2,eb3,eb4,eb5,eb6,eb7, eb8, ebd,ebd2,ebd3,ebd4,ebd5,ebd6,ebd7, ebd8, ebu,ebu2,ebu3,ebu4,ebu5,ebu6,ebu7,ebu8, ebl,ebl2,ebl3,ebl4,ebl5,ebl6,ebl7, ebl8, f1,f2,f3,f4,f5,f6,f7,f8,fl1,fl2,fl3,fl4,fl5,fl6,fl7,fl8,
	heart;
	
	
	public BufferedImage[] bullets_left = new BufferedImage[8];
	public BufferedImage[] bullets_right = new BufferedImage[8];
	public BufferedImage[] bullets_up = new BufferedImage[8];
	public BufferedImage[] bullets_down = new BufferedImage[8];
	
	public BufferedImage[] fire_right = new BufferedImage[8];
	public BufferedImage[] fire_left = new BufferedImage[8];
	
	public Textures(Game game){
		 ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
		
	}

	private void getTextures() {
		
		//THIS CLASS AND METHOD IS BASICALLY FOR STATIC PICTURES. 
		enemy = ss.grabImage(320, 500, 350, 250);	
		
		
		//PLAYER
		player = ss.grabImage(50, 10, 250, 260);
		player_down = ss.grabImage(50, 10, 250, 260);
		player_left = ss.grabImage(350, 0, 250, 300);
		player_right = ss.grabImage(700, 0, 250, 300);
		player_up = ss.grabImage(40, 390, 270, 300);
		
		
		//BULLET for player
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
		
		
		
		//BULLET FOR BAD GUYS
		try {
			
			BufferedImageLoader loader = new BufferedImageLoader();
			//HEART
			heart = loader.loadImage("/ship_heart.png");
			//ENEMY
			enemy = loader.loadImage("/enemy_right.png");
			enemy_down = loader.loadImage("/enemy_down.png");
			enemy_left = loader.loadImage("/enemy_left.png");
			enemy_right = loader.loadImage("/enemy_right.png");
			enemy_up = loader.loadImage("/enemy_up.png");
			
			//BULLET FOR BAD GUYS to the right
			bullets_right[0] = eb = loader.loadImage("/enemy_bullet_right1.png");
			bullets_right[1] = eb2 =  loader.loadImage("/enemy_bullet2_right.png");
			bullets_right[2] =eb3 = loader.loadImage("/enemy_bullet3_right.png");
			bullets_right[3] =eb4 = loader.loadImage("/enemy_bullet4_right.png");
			bullets_right[4] =eb5 = loader.loadImage("/enemy_bullet5_right.png");
			bullets_right[5] =eb6 = loader.loadImage("/enemy_bullet6_right.png");
			bullets_right[6] =eb7 = loader.loadImage("/enemy_bullet7_right.png");
			bullets_right[7] =eb8 = loader.loadImage("/enemy_bullet8_right.png");
			//BULLET FOR BAD GUYS down
			bullets_down[0] =ebd = loader.loadImage("/enemy_bullet_down1.png");
			bullets_down[1] =ebd2 =  loader.loadImage("/enemy_bullet2_down.png");
			bullets_down[2] =ebd3 = loader.loadImage("/enemy_bullet3_down.png");
			bullets_down[3] =ebd4 = loader.loadImage("/enemy_bullet4_down.png");
			bullets_down[4] =ebd5 = loader.loadImage("/enemy_bullet5_down.png");
			bullets_down[5] =ebd6 = loader.loadImage("/enemy_bullet6_down.png");
			bullets_down[6] =ebd7 = loader.loadImage("/enemy_bullet7_down.png");
			bullets_down[7] =ebd8 = loader.loadImage("/enemy_bullet8_down.png");
			//BULLET FOR BAD GUYS up
			bullets_up[0] =ebu = loader.loadImage("/enemy_bullet_up1.png");
			bullets_up[1] =ebu2 =  loader.loadImage("/enemy_bullet2_up.png");
			bullets_up[2] =ebu3 = loader.loadImage("/enemy_bullet3_up.png");
			bullets_up[3] =ebu4 = loader.loadImage("/enemy_bullet4_up.png");
			bullets_up[4] =ebu5 = loader.loadImage("/enemy_bullet5_up.png");
			bullets_up[5] =ebu6 = loader.loadImage("/enemy_bullet6_up.png");
			bullets_up[6] =ebu7 = loader.loadImage("/enemy_bullet7_up.png");
			bullets_up[7] =ebu8 = loader.loadImage("/enemy_bullet8_up.png");
			//BULLET FOR BAD GUYS to the left
			bullets_left[0] =ebl = loader.loadImage("/enemy_bullet_left1.png");
			bullets_left[1] =ebl2 =  loader.loadImage("/enemy_bullet2_left.png");
			bullets_left[2] =ebl3 = loader.loadImage("/enemy_bullet3_left.png");
			bullets_left[3] =ebl4 = loader.loadImage("/enemy_bullet4_left.png");
			bullets_left[4] =ebl5 = loader.loadImage("/enemy_bullet5_left.png");
			bullets_left[5] =ebl6 = loader.loadImage("/enemy_bullet6_left.png");
			bullets_left[6] =ebl7 = loader.loadImage("/enemy_bullet7_left.png");
			bullets_left[7] =ebl8 = loader.loadImage("/enemy_bullet8_left.png");
						
			//COLLISION FIRE
			fire_right[0] =f1 = loader.loadImage("/fire1_right.png");
			fire_right[1] =f2 = loader.loadImage("/fire2_right.png");
			fire_right[2] =f3 = loader.loadImage("/fire3_right.png");
			fire_right[3] =f4 = loader.loadImage("/fire4_right.png");
			fire_right[4] =f5 = loader.loadImage("/fire5_right.png");
			fire_right[5] =f6 = loader.loadImage("/fire6_right.png");
			fire_right[6] =f7 = loader.loadImage("/fire7_right.png");
			fire_right[7] =f8 = loader.loadImage("/fire8_right.png");
			
			fire_left[0] =fl1 = loader.loadImage("/fire1_left.png");
			fire_left[1] =fl2 = loader.loadImage("/fire2_left.png");
			fire_left[2] =fl3 = loader.loadImage("/fire3_left.png");
			fire_left[3] =fl4 = loader.loadImage("/fire4_left.png");
			fire_left[4] =fl5 = loader.loadImage("/fire5_left.png");
			fire_left[5] =fl6 = loader.loadImage("/fire6_left.png");
			fire_left[6] =fl7 = loader.loadImage("/fire7_left.png");
			fire_left[7] =fl8 = loader.loadImage("/fire8_left.png");
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
