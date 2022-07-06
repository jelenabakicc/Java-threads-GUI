package baloni;

import java.awt.Color;
import java.awt.Graphics;

public class Balon extends KruznaFigura {

	public Balon(Vektor c, Color b, double r, Vektor br, Scena s) {
		super(c, Color.RED, r, br, s);
	}
		
	@Override
	public void promeniPolozaj(double t) {
		super.promeniPolozaj(t);
	}

	public void paint (Scena scena) {
		int xPom=(int)(this.centar.getX()-this.precnik/2.0);
		int yPom=(int)(this.centar.getY()-this.precnik/2.0);
		Graphics g=scena.getGraphics();
		g.setColor(Color.RED);
		g.fillOval(xPom, yPom, 20, 20); //u sceni pise da je velicina balona 20
	}

}
