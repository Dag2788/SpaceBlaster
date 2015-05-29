import java.util.LinkedList;


public class Physics {

	public static boolean Collision(EntityA enta, LinkedList<EntityB> entb){
		for (int i=0; i< entb.size(); i++){
			if(enta.getBounds().intersects(entb.get(i).getBounds())){
				return true;
			}
		}
		return false;
	}
	
	public static boolean Collision(EntityA enta, EntityB entb){
		if(enta.getBounds().intersects(entb.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityC entc, EntityB entb){
		if(entc.getBounds().intersects(entb.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityI entc, EntityC entb){
		if(entc.getBounds().intersects(entb.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityC entc, EntityI entb){
		if(entc.getBounds().intersects(entb.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityC entd, EntityD entc){
		if(entd.getBounds().intersects(entc.getBounds())){
			return true;
		}
		return false;
	}
	
	
	
	public static boolean Collision(EntityC entc, LinkedList<EntityB> entb){
		for (int i=0; i< entb.size(); i++){
			if(entc.getBounds().intersects(entb.get(i).getBounds())){
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean Collision_with_Bullets(EntityC entd, LinkedList<EntityD> entc){
		for (int i=0; i< entc.size(); i++){
			if(entd.getBounds().intersects(entc.get(i).getBounds())){
				return true;
			}
		}
		return false;
	}


	public static boolean Collision_fire(EntityB entb, LinkedList<EntityA> enta){
		for (int i=0; i< enta.size(); i++){
			if(entb.getBounds().intersects(enta.get(i).getBounds())){
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean Collision_fireAndShip(EntityD entb, LinkedList<EntityC> enta){
		for (int i=0; i< enta.size(); i++){
			if(entb.getBounds().intersects(enta.get(i).getBounds())){
				return true;
			}
		}
		return false;
	}
	
	
}
