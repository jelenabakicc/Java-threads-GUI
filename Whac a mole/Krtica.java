package basta;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {

	public Krtica(Rupa r) {	super(r);}

	@Override
	public void udarenaZivotinja() {this.rupa.zaustaviNit();}

	@Override
	public void pobeglaZivotinja() { this.rupa.basta.smanjiPovrce();}
	
	@Override
	public void crtaj() {
		
		int x=rupa.sirina/2-rupa.sirina/2*dimenzije()/100;
		int y=rupa.visina/2-rupa.visina/2*dimenzije()/100;
		int width=rupa.sirina*dimenzije()/100;
		int height=rupa.visina*dimenzije()/100;
		
		Graphics g=this.rupa.getGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x, y, width, height); //delim sa 2 da bih dobila tacku u centru
	}

	
}
