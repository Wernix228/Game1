package entity;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler KeyH;
	
	public Player(GamePanel gp, KeyHandler KeyH) {
		this.gp = gp;
		this.KeyH = KeyH;
	}
public void setDefaulValues() {
	x = 100;
	y = 100;
	speed = 4;
}
}
