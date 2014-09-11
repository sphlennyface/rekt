package pl.sphgames.rpg10;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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

	private int x, y, health, damage, experience;
	private BufferedImage arrow;
	private int monsterSpeed;
	private int xDirection, yDirection;
	private BufferedImage monsterImg;
	private static CURRENTBEHAVIOR behavior;
	private MONSTERTYPE mobtype;
	private BREED breed;
	private static boolean aggro;
	private boolean isMovingX;
	private PatrolPoint point;
	private ArrayList<PatrolPoint> patrolPoints;
	private ArrayList<PathPoint> pathPoints;
	private int pX, pY;
	private static int currentPatrolTarget;
	private static int currentTileX, currentTileY;
	private boolean changedBehavior;
	private CURRENTBEHAVIOR lastBehavior;
	private int widthHitBox, heightHitBox;
	private int temp;
	private static Path currentPath;
	
	public Monster() {
		behavior = CURRENTBEHAVIOR.PATROLLING;
		patrolPoints = new ArrayList<PatrolPoint>();		
		loadImages();
		createPrototype();
		setPatrolPoints();	
		System.out.printf("\nhehe");
		putPlayerInPatrolPointOne();
		putInAIArray();
		widthHitBox = 45;
		heightHitBox = 45;
	}
	
	private void putInAIArray() {
		AI.putMonsterInArray(this);
	}
	
	public int getXPosition(){
		return currentTileX;
	}

	public int getYPosition(){
		return currentTileY;
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
	

	public boolean isMonsterHit(Bullet b){
		if(new Rectangle(x+(monsterImg.getWidth()/2-widthHitBox/2),y+(monsterImg.getHeight() - heightHitBox),widthHitBox,heightHitBox).contains(b.getCurrentLocation()))
			return true;
		else return false;
	}
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
		monsterSpeed = 3;
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
		point.setDelay(1);
		point.setDelay(1);
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
	
	
	public static void setAggro() {
		aggro = true;
		behavior = CURRENTBEHAVIOR.CHASING;
		analyzePathArray();
	}
	
	private boolean playerIsNearby() {		
		if (ancientMathDudeHelpMe(x,y,Player.x,Player.y) < 200) {
			setAggro();
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
		//if (isMovingX)
		x += xDirection;
	//	else
		y += yDirection;
	}
	
	
	
	private void chase() {		
		if(changedBehavior){
		Timer t = new Timer(Framework.gameTime,(double)1,arrow,x+(monsterImg.getWidth()/2),y+10);
		Game.timerList.add(t);
		}
		changedBehavior=false;
		System.out.printf("czejsuje\n");
		
		if (currentPath == null)
			wyjebSiê();
		
		else if (currentTileX == Player.getPlayerTileX() && currentTileY == Player.getPlayerTileY())
			wyjebSiê();
		else if ( (currentTileX == currentPath.getNextTileX()) ||  (currentTileY == currentPath.getNextTileX())) {
			System.out.printf("o chuj, dojsz³em na tile, czas na nowe doznania xD\n");
			analyzePathArray();
			moveUpToNextPoint();
		}
		else
			moveUpToNextPoint();
	}
		
	
	
	private static void analyzePathArray() {
		System.out.printf("analizuje xD\n");
		currentPath.clearPath();
		currentPath = AI.pathFinder.findPath(currentTileX, currentTileY, Player.getPlayerTileX(), Player.getPlayerTileY());	
		if (currentPath == null)
			System.out.printf("gracz ty chuju, tam nie moge dojsc :(\n");
		else
		System.out.printf("Po analizie wnioskuje ze musze dojsc z X " + currentTileX + " Y  " + currentTileY + " do X " + currentPath.getNextTileX() + " Y " + currentPath.getNextTileY() + " !\n");
	}
	
	private void setUpWalkingPoints() {
		
	}
	
	private void moveUpToNextPoint() {
		System.out.printf("im tryin \n");
		if (!(currentPath == null))
		chaseMove(currentPath.getNextTileX(),currentPath.getNextTileY());
	}
	
	private void wyjebSiê() {
		System.out.printf("wyjebane\n");
	}
	
	private void chaseMove(int x, int y) {
		
		System.out.printf("musze pocisn¹æ z X " + currentTileX + " Y " + currentTileY + " do X " + x + " Y " + y + " !\n");
		
		System.out.printf("probuje zapierdalac\n");
		if (currentTileX > x)
			xDirection = -1;
		else if (currentTileX < x)
			xDirection = 1;
		else
			xDirection = 0;
		
		if (currentTileY > y)
			yDirection = -1;
		else if (currentTileY < y)
			yDirection = 1;
		else
			yDirection = 0;
		
		
		System.out.printf("wyglada na to, ze xDirection wynosi " + xDirection + " a yDirection " + yDirection + "\n no to ruszamy!\n");
		move();
		
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
		//g2d.drawRect(x+(monsterImg.getWidth()/2-widthHitBox/2),y+(monsterImg.getHeight() - heightHitBox),widthHitBox,heightHitBox);
//		g2d.drawRect(x,y,widthHitBox,heightHitBox);

	}
	
	
}
