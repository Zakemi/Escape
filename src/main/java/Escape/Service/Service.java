package Escape.Service;

import Escape.Model.Arena;

public class Service {

	public static void setDefault(Arena arena){
		arena.getPlayer().setX((arena.getSize()/2)*arena.getStep());
		arena.getPlayer().setY(0*arena.getSize());
		arena.getPlayer().setActive(true);
		
		arena.getEnemy1().setX((arena.getSize()-1)*arena.getStep());
		arena.getEnemy1().setY((arena.getSize()-1)*arena.getStep());
		arena.getEnemy1().setActive(true);
		
		arena.getEnemy2().setX(0*arena.getStep());
		arena.getEnemy2().setY((arena.getSize()-1)*arena.getStep());
		arena.getEnemy2().setActive(true);
		
		arena.getTraps().trapsInit(arena.getSize(), arena.getStep(), 
				arena.getPlayer(), arena.getEnemy1(), arena.getEnemy2());
	}
	
}
