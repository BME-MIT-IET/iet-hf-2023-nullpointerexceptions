package whut.field;


import whut.player.Virologus;
import whut.agent.Beardance;
import whut.genetic_code.StunCode;

//A fertõzõ laboratórium mezõ nyilvántartásáért felel.
//Kezeli a mezõn történõ interakciók megvalósulását. Kezeli a virológusok megfertõzését.
public class EvilLab extends Lab
{
	public EvilLab()
	{
		super(new StunCode());
	}
 
	//Létrehoz egy medvetánc ágenst, és megtámadja vele a virológust, ezzel „megfertõzve” õt.
	@Override
	public void touching(Virologus v) //mezÅ‘ Ã©rintÃ©sekor
	{
		Beardance br = new Beardance();
		v.uRAttacked(br,null); //megtÃ¡madjad egy medvetÃ¡ncal		
	}
	
	@Override
	public String toString() {
		return "evillab";
	}
	
}
