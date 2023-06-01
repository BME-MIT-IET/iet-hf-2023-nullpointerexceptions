package whut.agent;

import whut.player.AgentUsable;
import whut.material.Packet;

import java.io.Serializable;

public abstract class Agent implements Serializable{
	
	//ezeket a fuggvenyeket overridoljuk(opcionalisan) az agensek. Alapertelmezetten olyan a visszateresi ertek, hogy ne legyen hatasa(pl.: nem lehet csak ugy lopni, ezert a canStealEffect false)
	
	
	//Akkor hivodik meg, ha a birtokos virologustol valamit lopni akarnak.
	//Igazzal ter vissza, ha lehet tole lopni, hamissal ha nem.
	//Itt tehet igazzal, ezt definialhatjak felul a leszarmazottak.
	public boolean canStealEffect() {
		return false;
	}
	
	//Ez akkor hivodik meg, ha egy varazslonak az agensOnMe listajaban van az adott agens a kore kezdeten.
	//A leszarmazottak azokkal a hatasokkal definialjak felul, melyek a kor elejen hatnak.
	//A paramater a birtokos virologus.
	//Igazzal ter vissza, ha az adott agens miatt meg tud mozogni az adott korben.
	//Jelen helyen tehet mindig igazzal, de ezt felul lehet definialni.
	public abstract boolean startTurnEffect(AgentUsable agentUsable);
	
	//Akkor h�v�dik meg, ha a birtokos virol�gust �genssel megt�madj�k. 
	//Igazzal t�r vissza, ha v�dve van, teh�t az �gens hat�sa nem �rv�nyes�lhet, hamissal ha nincs v�dve.
	public boolean defendEffect() {
		return false;
	}
	
	//Akkor h�v�dik meg, amikor a birtokos virol�gus egy rakt�rra l�p. 
	//Param�ter�l megkapja a bent l�v� packetet. 
	//Alap�rtelmezetten ezzel semmit nem csin�l, de egyes �gensek(BearDance) kezdhet valamit a megkapott packettel.
	public abstract void destroyEffect(Packet packet);

	public abstract boolean check(String agentType);
}
