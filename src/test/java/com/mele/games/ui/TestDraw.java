package com.mele.games.ui;

	import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mele.games.utils.hexarray.Cell;
import com.mele.games.utils.hexarray.HexArray;
import com.mele.tapHerder.ui.HexArrayRenderer;

public class TestDraw extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paintComponentX(Graphics g) {
		int sides = 6;
		int size = 30;
		int startX = 50;
		int startY = 50;
	    super.paintComponent(g);
	    Polygon p = null;
	    
	    g.setColor(Color.red);
	    
	    int row = 0;
	    double rowOffset = size * row * Math.sin(2 * Math.PI / sides);
	    for (int j = 0; j < 6; j++) {
		    p = new Polygon();
		    for (int i = 0; i < sides; i++)
		      p.addPoint((int) ((startX + (size*j*3)) + size * Math.cos(i * 2 * Math.PI / sides)),
		          (int) ((startY+rowOffset) + size * Math.sin(i * 2 * Math.PI / sides)));
	
		    g.drawPolygon(p);
	    }
	    
	    row = 1;
	    rowOffset = size * row * Math.sin(2 * Math.PI / sides);
	    for (int j = 0; j < 5; j++) {
		    p = new Polygon();
		    for (int i = 0; i < sides; i++)
		      p.addPoint((int) ((startX + (size*1.5) + (size*j*3)) + size * Math.cos(i * 2 * Math.PI / sides)),
		          (int) ((startY+rowOffset) + size * Math.sin(i * 2 * Math.PI / sides)));
	
		    g.drawPolygon(p);
	    }
	    
	    row = 2;
	    rowOffset = size * row * Math.sin(2 * Math.PI / sides);
	    for (int j = 0; j < 6; j++) {
		    p = new Polygon();
		    for (int i = 0; i < sides; i++)
		      p.addPoint((int) ((startX + (size*j*3)) + size * Math.cos(i * 2 * Math.PI / sides)),
		          (int) ((startY+rowOffset) + size * Math.sin(i * 2 * Math.PI / sides)));
	
		    g.drawPolygon(p);
	    }	    
	    
	    row = 3;
	    rowOffset = size * row * Math.sin(2 * Math.PI / sides);
	    for (int j = 0; j < 5; j++) {
		    p = new Polygon();
		    for (int i = 0; i < sides; i++)
		      p.addPoint((int) ((startX + (size*1.5) + (size*j*3)) + size * Math.cos(i * 2 * Math.PI / sides)),
		          (int) ((startY + rowOffset) + size * Math.sin(i * 2 * Math.PI / sides)));
	
		    g.drawPolygon(p);
	    }	    
	    /*
	    p = new Polygon();
	    for (int i = 0; i < sides; i++)
		      p.addPoint((int) (startX + (size*2) * Math.cos(i * 2 * Math.PI / sides)),
		          (int) (startY + (size*2) * Math.sin(i * 2 * Math.PI / sides)));

		    g.drawPolygon(p);	    
		    
		    p = new Polygon();
		    for (int i = 0; i < sides; i++)
			      p.addPoint((int) (startX + (size/10) * Math.cos(i * 2 * Math.PI / sides)),
			          (int) (startY + (size/10) * Math.sin(i * 2 * Math.PI / sides)));

			    g.drawPolygon(p);
			    
	    Polygon s = new Polygon();
	    for (int i = 0; i < 360; i++) {
	      double t = i / 360.0;
	      s.addPoint((int) (150 + 50 * t * Math.cos(8 * t * Math.PI)),
	          (int) (150 + 50 * t * Math.sin(8 * t * Math.PI)));
	    }
	    g.drawPolygon(s);
	    */
	  }
	
	public void paintComponent(Graphics g) {
		HexArray map = new HexArray(9, 8, Cell.class);
		HexArrayRenderer har = new HexArrayRenderer(map);
		har.setReferencePointX(50);
		har.setReferencePointY(50);
		
		har.init();
		har.update(g);
		
	}
	
	  public static void main(String[] args) {
	    JFrame frame = new JFrame();
	    frame.setTitle("DrawPoly");
	    frame.setSize(450, 450);
	    frame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	    Container contentPane = frame.getContentPane();
	    contentPane.add(new TestDraw());

	    frame.show();
	  }
}