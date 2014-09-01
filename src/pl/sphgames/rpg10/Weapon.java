package pl.sphgames.rpg10;

import java.lang.String;
import java.awt.image.BufferedImage;

public class Weapon {
	
	private Bullet bullet;
	public int damage, mags, magCapacity;
	public static int currentAmmo;
	private BufferedImage weaponImg;
	String name;
	float firingSpeed, reloadTime;
	
	public Weapon() {
		name = "Glock";
		damage = 30;
		magCapacity = 2000;
		firingSpeed = 1;
		reloadTime = 3;
		mags = 3;
		currentAmmo = magCapacity;
	}
	
	public void update() {
		
	}
	
	public void reload() {
		if (mags > 0) {
			mags --;
			currentAmmo = magCapacity;
		}
	}
	
	public int getCurrentAmmo() {
		return currentAmmo;
	}
	
	
	
}
