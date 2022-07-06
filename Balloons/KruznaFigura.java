package baloni;

import java.awt.Color;

public class KruznaFigura extends Krug {

	protected Vektor brzina;
	protected Scena scena;
	
	public KruznaFigura(Vektor c, Color b, double r, Vektor br, Scena s) {
		super(c, b, r);
		this.brzina=br;
		this.scena=s;
	}
	
	public void promeniPolozaj(double t) {
		Vektor polozaj=this.centar;
		Vektor pomBrzina=this.brzina;
		int w=scena.getWidth();
		int h=scena.getHeight();
		double x1=centar.getX()-precnik/2.0;
		double x2=centar.getX()+precnik/2.0;
		double y1=centar.getY()-precnik/2.0;
		double y2=centar.getY()+precnik/2.0;
		pomBrzina.mnozi(t);
		polozaj.saberi(pomBrzina);
		this.centar=polozaj;
		if (x1<0 || x2>w || y1<0 || y2>h) scena.izbaciFiguru(this);
	}
	
	public boolean sudar(KruznaFigura figura) {
		return (Krug.preklapanje(this,figura) ? true: false);
	}
	

	
	
}
