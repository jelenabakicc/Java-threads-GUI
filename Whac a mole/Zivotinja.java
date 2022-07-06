package basta;


public abstract class Zivotinja {
	
	protected Rupa rupa;
	
	public Zivotinja(Rupa r) { this.rupa=r;}
	
	public abstract void udarenaZivotinja();
	public abstract void pobeglaZivotinja();
	
	public abstract void crtaj();
		
	public int dimenzije() {
		int procenat=100*rupa.getTrenutniBrojKoraka()/rupa.getUkupanBrojKoraka();
		return procenat;
	}
	
	

}