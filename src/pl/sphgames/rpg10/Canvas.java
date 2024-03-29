package pl.sphgames.rpg10;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;



public abstract class Canvas extends JPanel implements KeyListener, MouseListener{
	
	
	private static boolean[] mouseState = new boolean[3];

	private static boolean[] keyboardState = new boolean[525];
	public static Graphics2D g2d;
	
	public Canvas() {
		
		this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(this);
        if(true)
        {
            BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), null);
            this.setCursor(blankCursor);
        }
        this.addMouseListener(this);
		
	}
	
	
	
    public abstract void Draw(Graphics2D g2d);
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g; 
       
        super.paintComponent(g2d);        
        Draw(g2d);
    }
    
    public static Graphics2D giveTheGraphicsFag() {
    	return g2d;
    }
    
    public static boolean keyboardKeyState(int key)
    {
        return keyboardState[key];
    }
    
    // Methods of the keyboard listener.
    @Override
    public void keyPressed(KeyEvent e) 
    {
        keyboardState[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        keyboardState[e.getKeyCode()] = false;
        keyReleasedFramework(e);
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }
    
    public abstract void keyReleasedFramework(KeyEvent e);
    

	public static boolean mouseButtonState(int button){
		return mouseState[button - 1];
	}
	public void mouseKeyStatus(MouseEvent e, boolean status){
		if(e.getButton() == MouseEvent.BUTTON1)
			mouseState[0]=status;
		else if(e.getButton() == MouseEvent.BUTTON2)
			mouseState[1] = status;
		else if(e.getButton() == MouseEvent.BUTTON3)
			mouseState[2] = status;
	}
	   @Override
	    public void mousePressed(MouseEvent e)
	    {
	        mouseKeyStatus(e, true);
	    }
	    
	    @Override
	    public void mouseReleased(MouseEvent e)
	    {
	        mouseKeyStatus(e, false);
	    }
	    @Override
	    public void mouseClicked(MouseEvent e) { }
	    
	    @Override
	    public void mouseEntered(MouseEvent e) { }
	    
	    @Override
	    public void mouseExited(MouseEvent e) { }
	}

	

