package whut.agent;

import whut.player.AgentUsable;
import whut.material.Packet;

import java.io.Serializable;

public abstract class Agent implements Serializable{
	
	//ezeket a f�ggv�nyeket overridolj�k(opcion�lisan) az �gensek. Alap�rtelmezetten olyan a visszat�r�si �rt�k, hogy ne legyen hat�sa(pl.: nem lehet csak �gy lopni, ez�rt a canStealEffect false)
	
	
	//Akkor h�v�dik meg, ha a birtokos virol�gust�l valamit lopni akarnak. 
	//Igazzal t�r vissza, ha lehet t�le lopni, hamissal ha nem. 
	//Itt teh�t igazzal, ezt defini�lhatj�k fel�l a lesz�rmazottak.
	public boolean canStealEffect() {
		return false;
	}
	
	//Ez akkor h�v�dik meg, ha egy var�zsl�nak az agensOnMe list�j�ban van az adott �gens a k�re kezdet�n.
	//A lesz�rmazottak azokkal a hat�sokkal defini�lj�k f�l�l, melyek a k�r elej�n hatnak. 
	//A param�ter a birtokos virol�gus. 
	//Igazzal t�r vissza, ha az adott �gens miatt m�g tud mozogni az adott k�rben. 
	//Jelen helyen teh�t mindig igazzal, de ezt fel�l lehet defini�lni.
	public abstract boolean startTurnEffect(AgentUsable v);
	
	//Akkor h�v�dik meg, ha a birtokos virol�gust �genssel megt�madj�k. 
	//Igazzal t�r vissza, ha v�dve van, teh�t az �gens hat�sa nem �rv�nyes�lhet, hamissal ha nincs v�dve.
	public boolean defendEffect() {
		return false;
	}
	
	//Akkor h�v�dik meg, amikor a birtokos virol�gus egy rakt�rra l�p. 
	//Param�ter�l megkapja a bent l�v� packetet. 
	//Alap�rtelmezetten ezzel semmit nem csin�l, de egyes �gensek(BearDance) kezdhet valamit a megkapott packettel.
	public abstract void destroyEffect(Packet p);

	public abstract boolean check(String s);
}
