package baloni;

import java.awt.*;

public class Krug {

	protected Vektor centar;
	protected Color boja;
	protected double precnik;
	
	public Krug (Vektor c, Color b, double r) {
		this.centar=c;
		this.boja=b;
		this.precnik=r;
	}
	
	public static boolean preklapanje(Krug k1, Krug k2) {
		double xPom=k1.centar.getX()-k2.centar.getX();
		double yPom=k1.centar.getY()-k2.centar.getY();
		double granica=k1.precnik/2.0+k2.precnik/2.0;
		double r=Math.sqrt(xPom*xPom+yPom*yPom);
		return (r<granica);
	}
	
	public void paint(Scena scena) {
		Graphics g=scena.getGraphics();
		g.setColor(this.boja);
		int xPom=(int)(this.centar.getX()-precnik/2.0);
		int yPom=(int)(this.centar.getY()-precnik/2.0);
		g.fillOval(xPom, yPom, (int)precnik, (int)precnik);
	}
	
	
	
}
