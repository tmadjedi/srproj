import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphPicture extends JPanel {
	
	Point2D[] points;
	ArrayList<Point> edges;
	
	public GraphPicture(Point2D[] points, ArrayList<Point> edges) {
		super();
		setBackground(Color.WHITE);
		
		this.points = new Point2D[points.length];
		this.edges = new ArrayList<Point>();
		
		for (int i = 0; i < points.length; i++) {
			this.points[i] = points[i];
		}
		
		for (Point p : edges) {
			this.edges.add(p);
		}
	}

	public void paint(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		int width = getWidth(); 
		int height = getHeight();

		//super.paint(g); 

		// Drawing code goes here
		
		for (int i = 0; i < this.points.length; i++) {
			g.draw(new Ellipse2D.Double(this.points[i].getX()*100+110,this.points[i].getY()*100+110,20,20));
			g.drawString(String.valueOf(i), (float) this.points[i].getX()*100 + 118, (float) this.points[i].getY()*100 + 122);
		}
		
		for (Point p : edges) {
			g.draw(new Line2D.Double(this.points[(int) p.getX()].getX()*100+128, this.points[(int) p.getX()].getY()*100+128, this.points[(int) p.getY()].getX()*100+128, this.points[(int) p.getY()].getY()*100+128));
		}

	}

	public static void main(String[] args) {
		
		int[][] adj = {{0,1,0,1,0},{0,0,0,0,0},{0,1,0,0,0},{0,0,0,0,0},{0,0,1,0,0}};
		
		GraphPicture panel = new GraphPicture(Tools.getPoints(5), Tools.getEdges(adj)); // window for drawing
		JFrame application = new JFrame(); // the program itself

		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		panel.repaint();
		application.add(panel);

		application.setSize(400, 400); // window is 500 pixels wide, 400 high

		application.setVisible(true);
	}

}