package baloni;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {
	private Scena s;
	public Igra() {
		super("Baloni");
		//setSize(800,700);
		setBounds(700,50,750,750);
		setResizable(true);
    	s= new Scena(this);
        s.setBounds(700,50,750,750);
    	s.generisiIgraca();
    	add(s, BorderLayout.CENTER);
    	
    	addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				s.zaustavi();
				dispose();
			}
		});
    	
    	s.addKeyListener(new KeyAdapter() {
    		
    		@Override
    			public void keyPressed(KeyEvent e) {
					s.mis(e);
			}
		});
    	
    	setVisible(true);
    }
	/*private void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				scena.zaustavi();
				dispose();
			}
		});
		
		this.scena.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:{
						scena.igrac.pomeriHorizontalno(-0.5);
						break;
						}
					case KeyEvent.VK_RIGHT:{
						scena.igrac.pomeriHorizontalno(0.5);
						break;
						}
					}
				
			}
		});
		
		
	}*/
	public static void main(String[] args) {
		new Igra();
	}
}
