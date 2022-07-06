package baloni;

public class Vektor implements Cloneable {

	private double x;
	private double y;
	
	public Vektor (double xx, double yy) {
		this.x=xx;
		this.y=yy;
	}
	
	public double getX() {return x;}
	public double getY() {return y;}
	
	public void mnozi (double br) {
		this.x=x*br;
		this.y=y*br;
	}
	
	public void saberi (Vektor v) {
		this.x=x+v.x;
		this.y=y+v.y;
	}
	
	@Override
	public Vektor clone() {
		Vektor v=new Vektor(0,0);
		try {
			v=(Vektor)super.clone();
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		return v;
	}
	
	
	
}
