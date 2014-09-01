package pl.sphgames.rpg10;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import pl.sphgames.rpg10.Player.Face;

public class Legolas extends Player {
	public void createNewPlayer() {
		weapon = new Weapon();
		x = (Framework.frameWidth / 2) + 35;
		while (x % 64 != 0)
			x--;
		y = (Framework.frameHeight / 2) - 70;
		while (y % 64 != 0)
			y--;
		level = 1;
		health = 150;
		experience = 0;
		playerSize = 10;
		stamina = 150;
		playerSpeed = (level / 5 ) + 4;
		face = Face.EAST;
		generator = new Random();
		frameDuration = 15;

	}
	public void loadImages() {
		try {
			playerImgW1 = ImageIO.read(new File("legolasW1.png"));
			playerImgW2 = ImageIO.read(new File("legolasW1.png"));
			playerImgN1 = ImageIO.read(new File("legolasN1.png"));
			playerImgN2 = ImageIO.read(new File("legolasN2.png"));
			playerImgE1 = ImageIO.read(new File("legolasE1.png"));
			playerImgE2 = ImageIO.read(new File("legolasE1.png"));
			playerImgS1 = ImageIO.read(new File("legolasS1.png"));
			playerImgS2 = ImageIO.read(new File("legolasS1.png"));
			displayedImage = playerImgN1;
		}
		catch (IOException ex) {
			Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
