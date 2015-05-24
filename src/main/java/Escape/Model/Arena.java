package Escape.Model;

public class Arena {
	private Player player;
	private Enemy enemy1;
	private Enemy enemy2;
	private Traps traps;
	private int size;
	private int winSize;
	private int step;
	private int trapSize = 2;

	public Arena(int size, int winSize) {
		this.size = size;
		this.winSize = winSize;
		this.step = winSize/size;
		player = new Player((size/2)*step,0*step);
		enemy1 = new Enemy((size-1)*step, (size-1)*step);
		enemy2 = new Enemy(0*step, (size-1)*step);
		traps = new Traps(trapSize, size, step, player, enemy1, enemy2);
	}
	
	public boolean enemiesIsActive(){
		if (enemy1.isActive() == true)
			return true;
		if (enemy2.isActive() == true)
			return true;
		return false;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Enemy getEnemy1() {
		return enemy1;
	}

	public Enemy getEnemy2() {
		return enemy2;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getWinSize() {
		return winSize;
	}

	public void setWinSize(int winSize) {
		this.winSize = winSize;
	}

	public int getStep() {
		return step;
	}

	public int getTrapSize() {
		return trapSize;
	}

	public void setTrapSize(int trapSize) {
		this.trapSize = trapSize;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public Traps getTraps() {
		return traps;
	}
}
