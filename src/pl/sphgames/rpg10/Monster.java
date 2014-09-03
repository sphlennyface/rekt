package pl.sphgames.rpg10;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Monster {
	
	public Monster() {
		behavior = CURRENTBEHAVIOR.PATROLLING;
		patrolPoints = new ArrayList<PatrolPoint>();
		
		loadImages();
		createPrototype();
		setPatrolPoints();		
		putPlayerInPatrolPointOne();
		putInAIArray();
	}
	
	private void putInAIArray() {
		AI.putMonsterInArray(this);
	}
	
	private class PatrolPoint {
		private int x;
		private int y;
		private int target;
		
		public PatrolPoint(int x_, int y_, int target_) {
			x = x_;
			y = y_;
			target = target_;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public int passTarget() {
			return target;
		}
		
	}
	
	private int x, y, health, damage, experience;
	private int monsterSpeed;
	private int xDirection, yDirection;
	private BufferedImage monsterImg;
	private CURRENTBEHAVIOR behavior;
	private MONSTERTYPE mobtype;
	private BREED breed;
	private boolean isMovingX;
	private PatrolPoint point;
	private ArrayList<PatrolPoint> patrolPoints;
	private int pX, pY;
	private static int currentPatrolTarget;
	private int currentTileX, currentTileY;
	
	private enum CURRENTBEHAVIOR {
		CHASING,
		PATROLLING,
		IDLING
	};
	
	private enum MONSTERTYPE {
		CHASER,
		SHOOTER,
		AFKER
	};
	
	private enum BREED {
		GOBLIN
	};
	
	private void createPrototype() {
		x = 110;
		y = 550;
		monsterSpeed = 10;
		xDirection = 0;
		yDirection = 0;
	}
	
	
	private void loadImages() {

    	try {
           monsterImg = ImageIO.read(new File("monster1.png"));          
		}
		catch (IOException ex) {
			Logger.getLogger(Monster.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void update() {
		//behave();
		updateCurrentTile();
		
			patrol();
		
	}
	
	private void updateCurrentTile() {
		currentTileX = x / 64;
		currentTileY = y / 64;
	}
	
	private void setPatrolPoints() {
		point = new PatrolPoint(1,1,1);
		patrolPoints.add(point);
		point = new PatrolPoint(10,1,2);
		patrolPoints.add(point);
		point = new PatrolPoint(10,5,3);
		patrolPoints.add(point);
		point = new PatrolPoint(5,5,4);
		patrolPoints.add(point);
		point = new PatrolPoint(5,10,5);
		patrolPoints.add(point);
		point = new PatrolPoint(10,10,6);
		patrolPoints.add(point);
		point = new PatrolPoint(1,10,0);
		patrolPoints.add(point);
		currentPatrolTarget = patrolPoints.get(0).passTarget();
		setMovementTarget(currentPatrolTarget);
		
	}
	
	private void putPlayerInPatrolPointOne() {
		point = patrolPoints.get(0);
		x = point.getX() * 64;
		y = point.getY() * 64;
	}
	
	private void patrol() {		
		if (pX == currentTileX && pY == currentTileY) {
			currentPatrolTarget = patrolPoints.get(currentPatrolTarget).passTarget();
			setMovementTarget(currentPatrolTarget);
		}
		for (int i = 0; i < monsterSpeed; i++) {
		checkWhatever();
		move();
		}
	}
	
	private void checkWhatever() {
		if (pX * 64 == x) {
			isMovingX = false;
			xDirection = 0; }
		if (pY * 64 == y)
			yDirection = 0;
		 System.out.println("PX " + pX * 64 + " X " + x + " PY " + pY * 64 + " Y " + y);
	}

	
	private void setMovementTarget(int cpt) {
		point = patrolPoints.get(cpt);
		 System.out.println("TWORZENIE NOWEGO CELU");
		pX = point.getX();
		pY = point.getY();	
		isMovingX = true;
		if (pX * 64 > x){
			xDirection = 1;
		System.out.println("petla 1 bez else");}
		else{
			xDirection = -1;
		System.out.println("petla 1 z else");}
		
		if (pY * 64 > y){
			yDirection = 1;
		System.out.println("petla 2 bez else");}
		else{
			yDirection = -1;
		System.out.println("petla 2 z else");}
		 System.out.println("YDYRECTION " + yDirection + " X DIRECTION " + xDirection);
		
	}
	
	private void move() {
		if (isMovingX)
		x += xDirection;
		else
		y += yDirection;
	}
	
	
	
	private void chase() {
		getBestPath();
		move();
	}
	
	private void getBestPath() {
		int x_ = Player.x;
		int y_ = Player.y;
	}
	
	
	
	public void behave() {
		
	}
	

	
	public void Draw(Graphics2D g2d) {
		g2d.drawImage(monsterImg, x, y, null);
	}
	
	
}
