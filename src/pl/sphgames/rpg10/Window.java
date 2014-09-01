package pl.sphgames.rpg10;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class Window extends JFrame{
        
    private Window()
    {
        this.setTitle("Palm Trees");
        

        if(false)
        {
            this.setUndecorated(true);
            this.setExtendedState(this.MAXIMIZED_BOTH);
        }
        else 
        {
        	 this.setUndecorated(true);
            this.setSize(16 * 64 + 4, 13 * 64 - 36);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
        }
        
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setContentPane(new Framework());
        
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}
