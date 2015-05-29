import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput implements MouseListener {

	Game game;
	Textures tex;


	public void MouseListener(Game game, Textures tex){
	this.game = game;
	this.tex = tex;
	
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent e) {
	
		int mx = e.getX();
		int my = e.getY();
		
		
		if(Game.State == Game.State.MENU){

			if( mx >= 475 && mx <= 725){
				if( my >= 330 && my <= 430){
					Game.State = Game.State.GAME;

				}
			}

			if( mx >= 475 && mx <= 725){
				if( my >= 470 && my <= 570){
					Game.State = Game.State.HELP;

				}
			}

			if( mx >= 475 && mx <= 725){
				if( my >= 610 && my <= 710){
					System.exit(1);

				}
			}
		} // end MENU
		else if(Game.State == Game.State.HELP){
			if( mx >= 475 && mx <= 725){
				if( my >= 470 && my <= 570){
					Game.State = Game.State.MENU;

				}
			}

			if( mx >= 475 && mx <= 725){
				if( my >= 610 && my <= 710){
					System.exit(1);

				}
			}
		} else if(Game.State == Game.State.GAMEOVER){
			
			if( mx >= 475 && mx <= 725){
				if( my >= 470 && my <= 570){

				}
			}
			
			
			if( mx >= 475 && mx <= 725){
				if( my >= 610 && my <= 710){
					System.exit(1);

				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
