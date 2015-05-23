package Escape.Model;

public class Enemy extends ArenaObject {
	public Enemy(int x, int y) {
		super(x, y);
	}
	
	public void setXY(Player player, int step){
		if( Math.abs(player.getX()-x) > Math.abs(player.getY()-y)){
			if (player.getX() > x){
				this.x += step;
			}
			else if (player.getX() < x) {
				this.x -= step;
			}
		}
		else {
			if (player.getY() > y){
				this.y += step;
			}
			else if (player.getY() < y){
				this.y -= step;
			}
		}
	}
}
