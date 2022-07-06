package basta;

import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class Igra extends Frame {
	
	private Basta basta;
	private static Igra instance=null;
	private Button kreni;
	private CheckboxGroup izbor= new CheckboxGroup();
	private Checkbox lako,srednje,tesko;
	private Label LabTezina;
	protected Label Lpovrce;
	private boolean radi=false;
	
	public static Igra getInstance() {
		if(instance==null) instance=new Igra();
		return instance;
	}
	
	private Igra() {   //mora da bude privatan da bismo obezbedili samo jednu instancu
		//this.tezina=new Label("Tezina:");
		//this.povrce=new Label("Povrce: 100");
		this.basta=new Basta(4,4);
		setSize(800,700);
		add(basta,BorderLayout.CENTER);
		azurirajProzor();
		osluskivaci();
		
		setVisible(true);
		
	}
	
	private void azurirajProzor() {
		
		Panel glavni=new Panel(new GridLayout(2,1));
		Panel dugmad=new Panel(new GridLayout(5,1));
		
		//labele
		Lpovrce=new Label("Povrce: 100");
		Lpovrce.setFont(new Font(null, Font.BOLD, 20));
		Lpovrce.setAlignment(Label.CENTER);
		//povezivanje labele povrce sa labelom iz baste
		basta.setLabPovrce(Lpovrce);
		LabTezina=new Label("Tezina:");
		LabTezina.setFont(new Font(null, Font.BOLD, 16));
		LabTezina.setAlignment(Label.CENTER);		
		dugmad.add(LabTezina);
		
		//checkbox
		lako=new Checkbox("Lako", izbor, false);
		srednje=new Checkbox("Srednje", izbor, true);
		tesko=new Checkbox("Tesko", izbor, false);
		dugmad.add(lako);
		dugmad.add(srednje);
		dugmad.add(tesko);
		
		//dugme
		kreni=new Button("Kreni");
		dugmad.add(kreni);
		
		glavni.add(dugmad);		
		glavni.add(Lpovrce);
		
		
		add(glavni, BorderLayout.EAST);
		
	}

	public void osluskivaci() {
			
		//za dugme i checkbox
		kreni.addActionListener(e->{
			if (radi) {
				
				basta.zaustaviBastu();
				kreni.setLabel("Kreni");
		
			}
			else {
				if (izbor.getSelectedCheckbox()==lako) {
					basta.setDt(1000);
					basta.setBrKoraka(10);
					
				}
				if(izbor.getSelectedCheckbox()==srednje) {
					basta.setDt(750);
					basta.setBrKoraka(8);
					
				}
				if (izbor.getSelectedCheckbox()==tesko) {
					basta.setDt(500);
					basta.setBrKoraka(6);
					
				}
				basta.pokreniBastu();
				kreni.setLabel("Stani");
			}
			zabrana(radi);
			radi=!radi;
			
		});
		
		//za zatvaranje prozora
		addWindowListener(new WindowAdapter() {               
			@Override
			public void windowClosing(WindowEvent e) {
				basta.zaustaviBastu();
				dispose();
			}
		});
			
		
		
	}

	private void zabrana(boolean radi2) {
		lako.setEnabled(radi2);
		srednje.setEnabled(radi2);
		tesko.setEnabled(radi2);
		
	}

	
	public static void main(String[] args) {
		getInstance();
	}
}
	

	


