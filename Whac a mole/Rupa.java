package basta;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Rupa extends Canvas implements Runnable {

	protected Basta basta;
	private Zivotinja zivotinja;
	protected int sirina,visina;
	private int trenutniBrKoraka,ukupanBrKoraka;
	private Thread nitRupe;
	private boolean zgazenaRupa;
	
	
	public Rupa (Basta b) {
		
		this.basta=b;
		mouseListener();
		
	}
	public Zivotinja getZivotinja() { return zivotinja;}
	public void setZivotinja(Zivotinja zivotinja) {	this.zivotinja = zivotinja;}
	
	private synchronized void zgaziRupu() {
		if (zivotinja==null) return;
		zgazenaRupa=true;
		zivotinja.udarenaZivotinja();
	}	
	
	@Override
	public void paint(Graphics g) {
		
		sirina=getWidth()-getWidth()/5;
		visina=getHeight()-getHeight()/5;
		
		g.setColor(new Color(102,51,0));
		g.fillRect(0, 0, sirina, visina);		
		if (zivotinja!=null) zivotinja.crtaj();
		
	}
	
	public synchronized void stvoriNit() { nitRupe=new Thread(this);}
	
	public synchronized void pokreniNit() {
		zgazenaRupa=false;	nitRupe.start();
		
	}
	
	public synchronized void zaustaviNit() {
		
		nitRupe.interrupt();
		basta.oslobodiRupu(this);
		zivotinja=null;
		
		repaint();
	}
	
	public synchronized boolean pokrenutaNit() { return nitRupe.isAlive();}
	
	@Override
	public void run() {
		try {
			trenutniBrKoraka=0;
			while (this.trenutniBrKoraka!=this.ukupanBrKoraka) {
				this.trenutniBrKoraka++;				
				Thread.sleep(100);
				repaint();
			}
			
			Thread.sleep(2000);
			synchronized(this) {
				if(zivotinja!=null && !zgazenaRupa) zivotinja.pobeglaZivotinja();  //proveravamo  da li je zivotinja bila udarena
				zaustaviNit();
			}
			
		} catch (InterruptedException e) {}
		
	}
	
	
	public synchronized int getTrenutniBrojKoraka() { return trenutniBrKoraka;}
	
	public int getUkupanBrojKoraka() { return ukupanBrKoraka;}	
	public void setUkupanBrojKoraka(int ukupanBrojKoraka) {	this.ukupanBrKoraka = ukupanBrojKoraka;}
	
    private void mouseListener(){         //receno je (u basti) da je rupu moguce zgaziti pritiskom misa, pa dodajemo mouseListener
		
		addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent me) {
				int x=me.getX();
				int y=me.getY();
				if (x>=0 && x<sirina && y>=0 && y<visina) zgaziRupu();
			}
			
		});
	}
	
	
	
}
