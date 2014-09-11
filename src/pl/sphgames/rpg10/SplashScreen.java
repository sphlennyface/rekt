package pl.sphgames.rpg10;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;



public class SplashScreen {

	private static int frames;
	private float alpha;
	private boolean fading, level;
	private static float tempAlpha;
	private ActionHandler ah_;
	private int nextLevel;
	
	public SplashScreen(boolean levSwitch, float alpha_, ActionHandler ah) {
		Game.pause();
		frames = 0;
		fading = true;
		level = levSwitch;
		ah_ = ah;
		alpha = alpha_;
		Game.addFuckingSplash(this);
	}
	
	public int getFrames() {
		return frames;
	}
	
	public void draw(Graphics2D g2d) {
		
    	g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    	
    	g2d.setColor(Color.black);
    	g2d.fillRect(0, 0, Framework.frameWidth, Framework.frameHeight);
    	g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 1.0f));
	}
	
	private void changeLevelm8() {
		nextLevel = Player.getLastActionHelper();
		ah_.changeLevel(nextLevel);
	}
	
	
	public void update() {
		if (fading)
    		alpha += 0.05f;
    	else
    		alpha -= 0.05f;

		if (alpha >= 1.0f) {
			alpha = 1.0f;
			fading = false;
		}
		else if (alpha <= 0.0f)
			alpha = 0.0f;

		frames++;

		if (frames == 20 && level == true) {
			changeLevelm8();
		}
      
      
	}
}
