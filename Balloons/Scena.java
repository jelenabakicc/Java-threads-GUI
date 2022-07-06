package baloni;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Scena extends Canvas implements Runnable {

	private Igra igra;
	private Thread nit=new Thread(this);
	protected Igrac igrac;	
	private boolean radi=false;
	private volatile List<KruznaFigura> figure = new ArrayList<>();
	
		
	public Scena(Igra i) {
		this.igra=i;
		this.igrac=null;
		pokreni();
		
	}
	
	public void pokreni() {
		//nit=new Thread(this);
		this.nit.start();
		//if (igrac==null) generisiIgraca();
		radi=true;
	}
		
	public void zaustavi() {
		radi=false;
		this.nit.interrupt();
	}
	
	public synchronized void generisiIgraca() {
		double w=this.getWidth()/2.0;
	    double h=this.getHeight()-120;
		this.igrac=new Igrac(new Vektor(w, h),Color.GREEN,30.0,new Vektor(2,0),this);
        figure.add(igrac);
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(!radi) wait();
				}
				Thread.sleep(60);
				azurirajScenu();
				//obavesti figure u vremenu i sudaru??
				//osluskivaci();
				repaint();	
			}
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
				
	}
	
	
	private void azurirajScenu() {
        double verovatnoca=Math.random();
        if (verovatnoca <= 0.1) generisiBalon();
        //for (int i=0;i<figure.size();i++) {
        int i=0;
        while(i<figure.size()) {
        	KruznaFigura pom=figure.get(i);
        	if (pom instanceof Balon) {
        		Balon bPom= (Balon)pom;
        		bPom.promeniPolozaj(1); 
        		if (bPom.sudar(igrac)) this.zaustavi();
        		if((bPom.centar.getY()+bPom.precnik/2.0)>=this.getHeight()) {
        			izbaciFiguru(bPom);
        		}
        	}
        	i++;
        }
        repaint();
        //this.revalidate();
    }
	
	private synchronized void generisiBalon() {
		Vektor c= new Vektor(Math.random()*this.getWidth(),15.0);
		Vektor v=new Vektor(0,5);
		Balon b=new Balon(c, Color.RED,20.0,v,this);
		dodajFiguru(b);
	}

	
	
	public synchronized void dodajFiguru(KruznaFigura fig) {
		boolean done=false;
		for (int i=0;i<figure.size();i++) {
			if (fig.sudar(figure.get(i))) done=true;
		}
		if (!done) {
			figure.add(fig);
		}
	    		
	}
	
	public synchronized void izbaciFiguru(KruznaFigura f) {
		figure.remove(f);
	}
	
    
	public void paint(Graphics g) {
		setBackground(Color.WHITE);
		//if (igrac!=null) this.igrac.paint(this);    //proveri treba li
		for (int i=0;i<figure.size();i++) figure.get(i).paint(this);
	}

	public void mis(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:{
			igrac.pomeriHorizontalno(-5);
			break;
			}
		case KeyEvent.VK_RIGHT:{
			igrac.pomeriHorizontalno(5);
			break;
			}
		default:break;
		}		
	}

	
	
	
	
}
