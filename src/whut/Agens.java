package whut;

import java.io.Serializable;

public class Agens implements Serializable{
	
	//ezeket a fï¿½ggvï¿½nyeket overridoljï¿½k(opcionï¿½lisan) az ï¿½gensek. Alapï¿½rtelmezetten olyan a visszatï¿½rï¿½si ï¿½rtï¿½k, hogy ne legyen hatï¿½sa(pl.: nem lehet csak ï¿½gy lopni, ezï¿½rt a canStealEffect false)
	
	
	//Akkor hívódik meg, ha a birtokos virológustól valamit lopni akarnak. 
	//Igazzal tér vissza, ha lehet tõle lopni, hamissal ha nem. 
	//Itt tehát igazzal, ezt definiálhatják felül a leszármazottak.
	public boolean canStealEffect() {
		return false;
	}
	
	//Ez akkor hívódik meg, ha egy varázslónak az agensOnMe listájában van az adott ágens a köre kezdetén.
	//A leszármazottak azokkal a hatásokkal definiálják fölül, melyek a kör elején hatnak. 
	//A paraméter a birtokos virológus. 
	//Igazzal tér vissza, ha az adott ágens miatt még tud mozogni az adott körben. 
	//Jelen helyen tehát mindig igazzal, de ezt felül lehet definiálni.
	public boolean startTurnEffect(AgensUsable v) {
		return true;
	}
	
	//Akkor hívódik meg, ha a birtokos virológust ágenssel megtámadják. 
	//Igazzal tér vissza, ha védve van, tehát az ágens hatása nem érvényesülhet, hamissal ha nincs védve.
	public boolean defendEffect() {
		return false;
	}
	
	//Akkor hívódik meg, amikor a birtokos virológus egy raktárra lép. 
	//Paraméterül megkapja a bent lévõ packetet. 
	//Alapértelmezetten ezzel semmit nem csinál, de egyes ágensek(BearDance) kezdhet valamit a megkapott packettel.
	public void destroyEffect(Packet p) {
		return;
	}

	public boolean Check(String s) {
		return false;
	}
}
