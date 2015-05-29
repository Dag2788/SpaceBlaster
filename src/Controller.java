import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;


public class Controller {

	private LinkedList<EntityA> e = new LinkedList<EntityA>();
	private LinkedList<EntityB> b = new LinkedList<EntityB>();
	private LinkedList<EntityC> c = new LinkedList<EntityC>();
	private LinkedList<EntityD> d = new LinkedList<EntityD>();
	private LinkedList<EntityI> i = new LinkedList<EntityI>();
	
 	public LinkedList<EntityI> getI() {
		return i;
	}

	public void setI(LinkedList<EntityI> i) {
		this.i = i;
	}

	Random r = new Random();
	
	Textures tex;
	Game game;
	EntityA ent;
	EntityB entB;
	EntityC entC;
	EntityD entD;
	EntityI entI;

	
	public Controller(Game game, Textures tex){
		this.game = game;
		this.tex = tex;
		
		
	}
	
	public void tick(){
		//A class
		for (int i =0; i<e.size(); i++){
			ent = e.get(i);
			
		if(ent.getX() >= 1500)
			removeEntity(ent);
		if(ent.getY() < -10)
			removeEntity(ent);
		if(ent.getX() < -10)
			removeEntity(ent);
		
		 ent.tick();
		}
		//B CLASS
		for (int i =0; i<b.size(); i++){
			entB = b.get(i);
			
		if(entB.getX() >= 1500)
			removeEntity(entB);
		if(entB.getY() < -10)
			removeEntity(entB);
		if(entB.getX() < -10)
			removeEntity(entB);
		
		 entB.tick();
		}
		// C CLASS
		for (int i =0; i<c.size(); i++){
			entC = c.get(i);
			
		if(entC.getX() >= 1500)
			removeEntity(entC);
		if(entC.getY() < -10)
			removeEntity(entC);
		if(entC.getX() < -10)
			removeEntity(entC);
		
		 entC.tick();
		}
		
		for (int i =0; i<d.size(); i++){
			entD = d.get(i);
			
		if(entD.getX() >= 1500)
			removeEntity(entD);
		if(entD.getY() < -10)
			removeEntity(entD);
		if(entD.getX() < -10)
			removeEntity(entD);
		
		 entD.tick();
		}
		for (int j =0; j<i.size(); j++){
			entI = i.get(j);
			
		if(entI.getX() >= 1500)
			removeEntity(entI);
		if(entI.getY() < -10)
			removeEntity(entI);
		if(entI.getX() < -10)
			removeEntity(entI);
		
		 entI.tick();
		}
		
		
		
	}
	
	public void render(Graphics g){
		//A CLASS
		for (int i =0; i<e.size(); i++){
			ent = e.get(i);
			ent.render(g);
		}
		// B CLASS
		for (int i =0; i<b.size(); i++){
			entB = b.get(i);
			entB.render(g);
		}
		// C CLASS
		for (int i =0; i<c.size(); i++){
			entC = c.get(i);
			entC.render(g);
		}
		for (int i =0; i<d.size(); i++){
			entD = d.get(i);
			entD.render(g);
		}
		for (int j =0; j<i.size(); j++){
			entI = i.get(j);
			entI.render(g);
		}
	
	}
	
	
	public void addEntity (EntityI block){
		i.add(block);
	}
	
	public void removeEntity(EntityI block){
	    i.remove(block);
	}
	
	public void addEntity (EntityA block){
		e.add(block);
	}
	
	public void removeEntity(EntityA block){
	    e.remove(block);
	}
	
	public void addEntity (EntityB block){
		b.add(block);
	}
	
	public void removeEntity(EntityB block){
	    b.remove(block);
	}
	
	public void addEntity (EntityC block){
		c.add(block);
	}
	
	public void removeEntity(EntityC block){
	    c.remove(block);
	}
	
	
	public LinkedList<EntityC> getC() {
		return c;
	}

	public void setC(LinkedList<EntityC> c) {
		this.c = c;
	}
	

	public LinkedList<EntityD> getD() {
		return d;
	}

	public void setD(LinkedList<EntityD> d) {
		this.d = d;
	}

	public EntityD getEntD() {
		return entD;
	}

	public void setEntD(EntityD entD) {
		this.entD = entD;
	}
	
	
	public void addEntity (EntityD block){
		d.add(block);
	}
	
	public void removeEntity(EntityD block){
	    d.remove(block);
	}
	

	public EntityA getEnt() {
		return ent;
	}

	public void setEnt(EntityA ent) {
		this.ent = ent;
	}

	public EntityB getEntB() {
		return entB;
	}

	public void setEntB(EntityB entB) {
		this.entB = entB;
	}

	public EntityC getEntC() {
		return entC;
	}

	public LinkedList<EntityA> getE() {
		return e;
	}

	public void setE(LinkedList<EntityA> e) {
		this.e = e;
	}

	public LinkedList<EntityB> getB() {
		return b;
	}

	public void setB(LinkedList<EntityB> b) {
		this.b = b;
	}

	public void setEntC(EntityC entC) {
		this.entC = entC;
	}

	public void addEnemy_count (int enemy_count){
		
		for (int i =0; i<enemy_count; i++){
			addEntity(new Enemy(r.nextInt(1200), r.nextInt(700), tex, game));
		}
	}
	
	public void addHeart (){
		Item b2 = new Item(r.nextInt(1000), r.nextInt(700), game, this, tex);
		addEntity(b2);
	}
}
