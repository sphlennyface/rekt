package pl.sphgames.rpg10;



import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class MainMenu {

	
	private BufferedImage bg;
	public static int exp;
	
	public MainMenu() {
		pointer = new MainMenuPointer();
		loadGraphics();
	}
	
	private MainMenuPointer pointer;
	
	private void loadGraphics() {
		try {
            bg = ImageIO.read(new File("bg.jpg"));
		}
		catch (IOException ex) {
			Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void update() {
		pointer.update();
	}
	
	public void Draw(Graphics2D g2d) {
		g2d.drawImage(bg, 0, 0, null);
		g2d.setColor(Color.white);
		g2d.setFont(new Font("Tahoma", Font.PLAIN, 45));
		g2d.drawString("NEW GAME as GANDALF", 400, 250);
		g2d.drawString("NEW GAME as LEGOLAS", 400, 320);
		g2d.drawString("SURVIVAL", 400, 390);
		g2d.drawString("QUIT", 400, 460);
		pointer.Draw(g2d);
		
	}
	
}
