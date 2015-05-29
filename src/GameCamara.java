
public class GameCamara {

private Game game;
private float xOffset;
private float yOffset;

public GameCamara(Game game, float xOffset, float yOffset){
	this.game = game;
	this.xOffset = xOffset;
	this.yOffset = yOffset;
}

public void centerOnEntity(EntityA e){
	xOffset = (float) e.getX() - game.getWidth()/2 + 258 /2;
	yOffset = (float) e.getY() - game.getHeight()/2 + 236/2;
}

public void move(float xAmt, float yAmt){
	xOffset += xAmt;
	yOffset += yAmt;
}

public float getxOffset() {
	return xOffset;
}

public void setxOffset(float xOffset) {
	this.xOffset = xOffset;
}

public float getyOffset() {
	return yOffset;
}

public void setyOffset(float yOffset) {
	this.yOffset = yOffset;
}

}
