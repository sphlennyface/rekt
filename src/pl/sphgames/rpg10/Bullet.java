package pl.sphgames.rpg10;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;


public class Bullet {
	private double bulletSpeed;
	public double x, y;
	private double middleX, middleY;
	private int currentXTile, currentYTile;
	private int damagePower;
	private double movingXSpeed, movingYSpeed;
	public static BufferedImage bulletImgN, bulletImgE, fireball, bulletImgS;
	public BufferedImage bulletImg;
	public final static long timeBetweenNewBullets = Framework.secInNanosec / 3;
	public static long timeOfLastCreatedBullet = 0;
	private int[][] trajectory;
	private int destinationX, destinationY;
	private double _angle;
	private AffineTransformOp atop;
	private AffineTransform at;
	private Explosion explo;


	public Point2D getCurrentLocation(){
		Point p = new Point((int)middleX,(int)middleY);
		return p;
	}


	public Bullet(double x, double y, int dmg, Point mousePosition)
	{
		this.damagePower = dmg;
		this.bulletImg = getBulletImage();
		this.x = x - (bulletImg.getWidth()/2);
		this.y = y - (bulletImg.getHeight()/2);
		destinationX = Crosshair.middleX;
		destinationY = Crosshair.middleY;
		this.trajectory = new int[2][2];
		this.trajectory[0][0] = Crosshair.middleX;
		this.trajectory[1][0] = Crosshair.middleY;
		this.trajectory[0][1] = (int)x;
		this.trajectory[1][1] = (int)y;
		this._angle = 0;
		bulletSpeed = 10;
		setBulletVector(trajectory);
		middleX = this.x  + (bulletImg.getWidth()/2) + (bulletImg.getWidth()/2 * Math.cos(Math.toRadians(_angle)));// ((bulletImg.getWidth()/4)  * Math.cos(Math.toRadians(_angle)))
		middleY = this.y + (bulletImg.getHeight()/2) - (bulletImg.getHeight()/2 * Math.sin(Math.toRadians(_angle)));// ((bulletImg.getHeight()/4) * Math.sin(Math.toRadians(_angle)))
		currentXTile = (int)middleX / 64;
		currentYTile = (int)middleY / 64;
	}


	public void setBulletVector(int[][] trajectory){

		_angle = -90 + Math.atan2(trajectory[0][0] - trajectory[0][1],trajectory[1][0]-trajectory[1][1]) * 180/Math.PI;
		double _rad = Math.toRadians(_angle);

		movingXSpeed = bulletSpeed * Math.cos(_rad);
		movingYSpeed = bulletSpeed * -Math.sin(_rad);
	}



	public BufferedImage getBulletImage() {

		return fireball;


	}

	public void update() {
		x += movingXSpeed;
		y += movingYSpeed;
		middleX += movingXSpeed;
		middleY += movingYSpeed;
		currentXTile = (int)middleX / 64;
		currentYTile = (int)middleY / 64;
	}

	public boolean isItLeftScreen() {
		if(x > 0 && x < Framework.frameWidth && y > 0 && y < Framework.frameHeight)
			return false;
		else
			return true;
	}

	public boolean hitUnpassable(ArrayList<Monster> aiList) {
		
		if ( (middleX > destinationX-5) && (middleX < destinationX+5) && (middleY > destinationY-5) && (middleY < destinationY+5)){
			explo = new Explosion(0.5,damagePower,currentXTile,currentYTile,1);
			return true;
		}
		for(int i=0;i<aiList.size();i++){
				if(aiList.get(i).isMonsterHit(this)){
					explo = new Explosion(0.5,damagePower,currentXTile,currentYTile,1);
					return true;
				}
		}
				
		if (currentXTile > 14 || currentYTile > 10){
			explo = new Explosion(0.5,damagePower,currentXTile,currentYTile,1);
			return true;
		}
		if (World.background[currentXTile][currentYTile].isPassable())		
			return false;
		explo = new Explosion(0.5,damagePower,currentXTile,currentYTile,1);
		return true;
	}

	public void Draw(Graphics2D g2d)
	{
		at = AffineTransform.getRotateInstance(Math.toRadians(-_angle),bulletImg.getHeight()/2,bulletImg.getWidth()/2);
		atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		g2d.drawImage(atop.filter(bulletImg, null), (int)x, (int)y, null);

	}

}
