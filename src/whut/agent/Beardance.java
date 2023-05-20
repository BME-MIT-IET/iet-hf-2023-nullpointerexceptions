package agent;
import player.AgensUsable;
import material.Packet;
import player.Virologus;

import java.util.List;

public class Beardance extends Agens{
	
	//A mezõn amin áll a paraméterül kapott virológus megfertõz minden virológust, majd a paraméterül kapott virológust egy random szomszédos mezõre mozgatja. 
	//Ezután az új mezõn is megfertõz mindenkit. 
	//Végül hamissal tér vissza, mivel a birtokos virológus mást nem csinálhat a körében.
	@Override
	public boolean startTurnEffect(AgensUsable v) {
		infectAll(v.getField().getVirologusok(),v);
		
		if (v.getField().getNeighbourhood().size() > 3) {
			v.move(v.getField().getNeighbourhood().get(2));
		} 
		else{
			v.move(v.getField().getNeighbourhood().get(0));
		}
		infectAll(v.getField().getVirologusok(),v);		
		return false;
	}
	
	//A paraméterül kapott csomagnak lekéri a maximális értékét, majd a maximális értékébõl levonja ezt az értéket, ezzel nullára állítva.
	@Override
	public void destroyEffect(Packet p) {
		p.handlePossibleLostMaterial(p.getMaxMaterial());
	}
	
	//A paraméterül kapott virológuslistából mindenkit megfertõz Beardance ágenssel az alábbi módon: 
	//Csinál egy Beardance ágenst, amit odaad a paraméterül kapott virológusnak. 
	//Ezután a lista elem virológust a viselõ virológus nevében a csinált ágenssel megtámadja.
	public void infectAll(List<AgensUsable> vs, AgensUsable a) {
		for(int i = vs.size()-1; i >= 0; i--) {
			if(!vs.get(i).equals(a)) {
				Beardance b = new Beardance();
				a.addAgens(b);
				Virologus v = (Virologus)vs.get(i);
				a.useAgens(v, b);
			}
		}
	}
	
	@Override
	public boolean check(String s) {
		return s.equals("Beardance");
	}
	
	public String toString() {
		return "beardance";
	}
}
