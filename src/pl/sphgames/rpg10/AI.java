package pl.sphgames.rpg10;

import java.util.ArrayList;
import java.awt.Graphics2D;


public class AI {

	private static ArrayList<Monster> monsterList;
	
	public AI() {
		monsterList = new ArrayList<Monster>();
	}
	
	public static void putMonsterInArray(Monster mob) {
		monsterList.add(mob);
	}
	
	public static void clearArray() {
		monsterList.clear();
	}
	
	public void update() {
		for (int i = 0; i < monsterList.size(); i++) {
			monsterList.get(i).update();
		}
	}
	
	public void Draw(Graphics2D g2d) {
		for (int i = 0; i < monsterList.size(); i++) {
			monsterList.get(i).Draw(g2d);
		}
	}
	
	
}
