package baloni;

import java.awt.*;

public class Igrac extends KruznaFigura {

	public Igrac(Vektor c, Color b, double r, Vektor br, Scena s) {
		super(c, Color.GREEN, r, new Vektor(0,0), s);
	}

	@Override
	public void promeniPolozaj(double dt) {
		super.promeniPolozaj(0);
	}
	
	public void pomeriHorizontalno(double pomeraj) {  //proveri da li pomeraj treba da bude vektor  ili moze ovako?
		Vektor pomerajV=new Vektor(pomeraj,0);
		Vektor pom=this.centar;
		pom.saberi(pomerajV);
		double pomX1=pom.getX()-this.precnik/2.0;
		double pomX2=pom.getX()+this.precnik/2.0;
		double w=this.scena.getWidth();
		if (pomX1>=0 && pomX2<=w) this.centar=pom;
	}
	
	public void paint (Scena scena) {
		Graphics g=scena.getGraphics();
		//veci krug
		g.setColor(Color.GREEN);		
		int pomX1=(int)(this.centar.getX()-this.precnik/2.0);
		int pomY1=(int)(this.centar.getY()-this.precnik/2.0);
		g.fillOval(pomX1, pomY1, 30, 30);
		//manji krug
		g.setColor(Color.BLUE);
		int pomX2=(int)(this.centar.getX()-this.precnik/4.0);
		int pomY2=(int)(this.centar.getY()-this.precnik/4.0);
		g.fillOval(pomX2, pomY2, 15, 15);
	}

	@Override
	public boolean sudar(KruznaFigura figura) {
		if (figura instanceof Balon) {
			Balon balon=(Balon)figura;
			return super.sudar(balon);
		} 
		else return false;   //promeni ako bude jos neka figura
	}
	
	

}
