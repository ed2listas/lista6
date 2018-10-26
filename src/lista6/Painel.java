package lista6;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.util.Random;

public class Painel extends JPanel{
	public void paintComponent( Graphics g ){
	    super.paintComponent( g );
	    int pixel=0;
	    int w = 1550 ;
	    int h = 670;
	    
	    Graphics2D g2d = (Graphics2D) g;

	    g2d.setColor(Color.BLACK);
	    g2d.setStroke(new BasicStroke(10));

	    for (int i = 0; i <= 250; i++) {
	      //Dimension size = getSize();

	      Random r = new Random();
	      int x = Math.abs(r.nextInt()) % w;
	      int y = Math.abs(r.nextInt()) % h;
	      g2d.drawLine(x, y, x, y);
	    }

	    
	    
	}
}
