package pl.sphgames.rpg10;
import java.awt.Graphics2D;
import java.util.Collections;
import java.lang.Math;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.xml.transform.Templates;

import pl.sphgames.rpg10.Timer.STATE;

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
	
	private class PathPoint {
		private int x;
		private int y;
		public PathPoint(int x_, int y_) {
			x = x_;
			y = y_;
		}
	}
	
	private class PatrolPoint {
		private int x;
		private int y;
		private int target;
		private double delay;
		private Timer t;
		private Timer.STATE timerState;

		
		public PatrolPoint(int x_, int y_, int target_) {
			x = x_;
			y = y_;
			delay = 0;
			target = target_;
			timerState = STATE.NONEXISTING;
			t = new Timer();
		}
		
		public void setDelay(int lenght) {
			delay = lenght;
			timerState = STATE.PENDING;
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
	private BufferedImage arrow;
	private int monsterSpeed;
	private int xDirection, yDirection;
	private BufferedImage monsterImg;
	private CURRENTBEHAVIOR behavior;
	private MONSTERTYPE mobtype;
	private BREED breed;
	private boolean aggro;
	private boolean isMovingX;
	private PatrolPoint point;
	private ArrayList<PatrolPoint> patrolPoints;
	private ArrayList<PathPoint> pathPoints;
	private int pX, pY;
	private static int pX2, pY2;
	private static int currentPatrolTarget;
	private int currentTileX, currentTileY;
	private boolean changedBehavior;
	private CURRENTBEHAVIOR lastBehavior;

	private int temp;


	private enum CURRENTBEHAVIOR {
		CHASING,
		PATROLLING,
		IDLING
	};
	
	private enum MONSTERTYPE {
		CHASER,
		SHOOTER,
		DISTANCER,
		AFKER
	};
	
	private enum BREED {
		GOBLIN
	};
	
	private void createPrototype() {
		x = 110;
		y = 550;
		monsterSpeed = 4;
		xDirection = 0;
		yDirection = 0;
		aggro = false;
	}
	
	
	private void loadImages() {

    	try {
           monsterImg = ImageIO.read(new File("monster1.png"));
           arrow = ImageIO.read(new File("arrow.png"));
		}
		catch (IOException ex) {
			Logger.getLogger(Monster.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void update() {
		behave();
		if(lastBehavior != behavior)
			changedBehavior = true;
		updateCurrentTile();
		switch (behavior) {
			case CHASING:
				chase();
			break;
			case PATROLLING:
				patrol();
			break;
			default:
				
			break;
		}
		lastBehavior = behavior;
			
		
	}
	
	private void updateCurrentTile() {
		currentTileX = x / 64;
		currentTileY = y / 64;
	}
	
	private void setPatrolPoints() {
		point = new PatrolPoint(1,1,1);
		point.setDelay(1);
		patrolPoints.add(point);
		point = new PatrolPoint(10,1,2);
<<<<<<< HEAD
		point.setDelay(1);
=======

		point.setDelay(1);

>>>>>>> origin/master
		patrolPoints.add(point);
		point = new PatrolPoint(10,5,3);
		point.setDelay(1);
		patrolPoints.add(point);
		point = new PatrolPoint(5,5,4);
		point.setDelay(1);
		patrolPoints.add(point);
		point = new PatrolPoint(5,10,5);
		point.setDelay(1);
		patrolPoints.add(point);
		point = new PatrolPoint(10,10,6);
		point.setDelay(1);
		patrolPoints.add(point);
		point = new PatrolPoint(1,10,0);
		point.setDelay(1);
		patrolPoints.add(point);
		currentPatrolTarget = patrolPoints.get(0).passTarget();
		setMovementTarget(currentPatrolTarget);
		
	}
	
	private void putPlayerInPatrolPointOne() {
		point = patrolPoints.get(0);
		x = (point.getX()+1) * 64;
		y = (point.getY()+1) * 64;
	}
	
	private void patrol() {		
		if (pX == currentTileX && pY == currentTileY) {
			if (patrolPoints.get(currentPatrolTarget).timerState != STATE.NONEXISTING) {
				switch(patrolPoints.get(currentPatrolTarget).timerState){
				case PENDING:
					patrolPoints.get(currentPatrolTarget).t= new Timer(Framework.gameTime,patrolPoints.get(currentPatrolTarget).delay);
					patrolPoints.get(currentPatrolTarget).timerState=STATE.WORKING;
					temp = monsterSpeed;
					Game.timerList.add(patrolPoints.get(currentPatrolTarget).t);
					break;
				case WORKING:

					if(patrolPoints.get(currentPatrolTarget).t.isWaiting()){
						monsterSpeed=0;
					}
					else
						patrolPoints.get(currentPatrolTarget).timerState=STATE.DONE;
					
					break;
				case DONE:
					monsterSpeed = temp;
					currentPatrolTarget = patrolPoints.get(currentPatrolTarget).passTarget();
					setMovementTarget(currentPatrolTarget);
					patrolPoints.get(currentPatrolTarget).timerState=STATE.PENDING;
					break;
					default:
						break;
				}
			}
			else {
			currentPatrolTarget = patrolPoints.get(currentPatrolTarget).passTarget();
			setMovementTarget(currentPatrolTarget);
			}
		}
		else
			patrolMove();
	}
	
	private void patrolMove() {
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
	}

	
	private void setMovementTarget(int cpt) {
		point = patrolPoints.get(cpt);
		
		pX = point.getX();
		pY = point.getY();	
		isMovingX = true;
		if (pX * 64 > x)
			xDirection = 1;
		
		else
			xDirection = -1;

		
		if (pY * 64 > y)
			yDirection = 1;

		else
			yDirection = -1;	
		}
	
	
	
	
	private boolean playerIsNearby() {		
		if (ancientMathDudeHelpMe(x,y,Player.x,Player.y) < 200) {
			aggro = true;
			return true;
		}
		return false;
	}
	
	private boolean playerIsInMeleeRange() {		
		if (ancientMathDudeHelpMe(x,y,Player.x,Player.y) < 64) {		
			return true;
		}
		return false;
	}
	
	private double ancientMathDudeHelpMe(int x, int y, int x_, int y_) {
		//all hail almighty pythagoras
		return Math.sqrt(Math.abs(y_ - y)*Math.abs(y_ - y) + Math.abs(x_ - x)*Math.abs(x_ - x));
	
		
	}
	
	private void move() {
		if (isMovingX)
		x += xDirection;
		else
		y += yDirection;
	}
	
	
	
	private void chase() {		
		if(changedBehavior){
		Timer t = new Timer(Framework.gameTime,1,arrow,x+(monsterImg.getWidth()/2),y+10);
		Game.timerList.add(t);
		}
		changedBehavior=false;
		
		analyzePathArray();
		setUpWalkingPoints();
		moveUpToNextPoint();
		
	}
	
	private void chaseMove() {
		if (Player.x / 64 != x / 64) {
			for (int i = 0; i < monsterSpeed; i++)
				x += xDirection;
		}
		
		if (Player.y / 64 != y / 64) {
			for (int i = 0; i < monsterSpeed; i++)
				y += yDirection;
		}
		
	}
	
	private void setPlayerTarget() {		
		int x_ = Player.x;
		int y_ = Player.y;
		
		if (x_ > x)
			xDirection = 1;		
		else
			xDirection = -1;

		
		if (y_ > y)
			yDirection = 1;
		else
			yDirection = -1;	
		
		
	}
	
	/////// WELCOME TO THE WORLD OF PATHFINDING
	
	private ArrayList closed;
	//private SortedList open;
	
	
	private void analyzePathArray() {
		int currentX = x / 64;
		int currentY = y / 64;
		int targetX = Player.x / 64;
		int targetY = Player.y / 64;
		
	}
	
	private void setUpWalkingPoints() {
		
	}
	
	private void moveUpToNextPoint() {
		
	}
	
	
	////// PLEASE COME AGAIN
	
	
	public void behave() {
		if (!aggro) {
		if (playerIsNearby()) {
			behavior = CURRENTBEHAVIOR.CHASING;
			aggro = true;
		}
		else {
			behavior = CURRENTBEHAVIOR.PATROLLING;	
			
		}
	}
		
	}
	

	
	public void Draw(Graphics2D g2d) {
		g2d.drawImage(monsterImg, x, y, null);
	}
	
	
}
