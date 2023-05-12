package whut;

public class Forget extends Agens{

	//Meghívja a paraméterül kapott virológus forgetAll() függvényét, amivel a virológus elfelejti az általa ismert összes genetikai kódot. 
	//Ezután kitörli a paraméterül kapott virológuson ható ágensek közül.
	@Override
	public boolean startTurnEffect(AgensUsable au) {
		//kï¿½r elejï¿½n elfelejtteti minden megtanult kï¿½djï¿½t
		au.forgetAll();
		//kiotï¿½rli a gazdï¿½ja listï¿½jï¿½bï¿½l magï¿½t
		au.removeAgensOnMe(this);
		//visszatï¿½r igazzal, mert tud mï¿½g mozogni
		return true;
	}
	
	public boolean Check(String s) {
		if(s.equals("forget"))
			return true;
		return false;
	}
	
	public String toString() {
		return "forget";
	}
}
