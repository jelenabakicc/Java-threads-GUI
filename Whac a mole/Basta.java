package basta;

import java.awt.*;

@SuppressWarnings("serial")
public class Basta extends Panel implements Runnable {

	private Thread nitBaste;
	private boolean pokrenuta=false;	
	private Rupa[][] rupe;
	private boolean[][] slobodneRupe;
	private int povrce;
	private Label LabPovrce;
	private int dt;
	private int brKoraka;
	private int brVrsta;
	private int brKolona;
	
	
	
	public Basta(int v, int k) {
		this.brVrsta=v;
		this.brKolona=k;
		this.povrce=100;
		this.rupe=new Rupa[v][k];
		this.slobodneRupe=new boolean[v][k];
		setBackground(Color.GREEN);
		setLayout(new GridLayout(v,k)); 
		for (int i=0;i<v;i++)
			for (int j=0;j<k;j++) {
				rupe[i][j]=new Rupa(this);
				slobodneRupe[i][j]=true;
				add(rupe[i][j]);     //moramo da dodamo rupu u mrezu!!
			}
		   
		//labele();
		nitBaste=new Thread(this);
		nitBaste.start();
	}
	
	public int getBrKoraka() { return brKoraka;}
	public void setBrKoraka(int brKoraka) { 
		this.brKoraka = brKoraka;
		/*for (int i=0;i<brVrsta;i++)
			for (int j=0;j<brKolona;j++) 
				rupe[i][j].setUkupanBrojKoraka(brKoraka);*/
	}

	public synchronized void smanjiPovrce() { povrce--;}
	
	public synchronized void pokreniBastu() {    //uzmi u obzir da basta vec moze biti pokrenuta, znaci treba sve da vratimo na pocetne vrednosti!!
		if (nitBaste!=null) nitBaste.interrupt();
		povrce=100;
		labele();
		nitBaste=new Thread(this);
		nitBaste.start();
		repaint();
		
		pokrenuta=true;
		
		notify();
	}
	
	public synchronized void zaustaviBastu() {
		nitBaste.interrupt();

		for(int i=0;i<brVrsta;i++)
			for (int j=0;j<brKolona;j++) {
				if (rupe[i][j].getZivotinja()!=null) rupe[i][j].zaustaviNit();
				//slobodneRupe[i][j]=true;
			}
	}
	
	public synchronized void labele() {	LabPovrce.setText("Povrce:" + povrce);}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				synchronized(this){
					while (!pokrenuta) { wait();}
				}
				Thread.sleep(dt);
				postaviKrticu();
				setDt(dt-dt/100);
				repaint();
				
				labele();  //ovo ne mora u blok jer si stavila synchronized metodu
				synchronized(this) {
					
					if(povrce==0) zaustaviBastu();
					
				}
				
			}catch (InterruptedException e) {break;}
		}
		
	}

	private void postaviKrticu() {
		int v=0;
		int k=0;
		
		while(true) {
			v=(int)(brVrsta*Math.random());
			k=(int)(brKolona*Math.random());
		    
		    if(slobodneRupe[v][k]==true && rupe[v][k].getZivotinja()==null) {
		    	
		       Krtica krt=new Krtica(rupe[v][k]);
		       rupe[v][k].setZivotinja(krt);
		       rupe[v][k].setUkupanBrojKoraka(this.brKoraka);
		       rupe[v][k].stvoriNit();
		       rupe[v][k].pokreniNit();
		       break;

		   }
		}
		
	}

	public void setDt(int dt) { this.dt = dt;}
	
    public synchronized void oslobodiRupu(Rupa rupa) {
		
		for(int i=0;i<brVrsta;i++)
			for (int j=0;j<brKolona;j++) {
				
				if(rupe[i][j]==rupa) {
					slobodneRupe[i][j]=true; break;
				}
			}
	}
    
    public void setLabPovrce(Label label) { this.LabPovrce=label;}
	
	
}