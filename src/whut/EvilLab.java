package whut;


//A fertõzõ laboratórium mezõ nyilvántartásáért felel. 
//Kezeli a mezõn történõ interakciók megvalósulását. Kezeli a virológusok megfertõzését.
public class EvilLab extends Lab
{
	//private boolean elhasznalt = false;
	
	public EvilLab()
	{
		
		super(new StunCode());
	}
 
	//Létrehoz egy medvetánc ágenst, és megtámadja vele a virológust, ezzel „megfertõzve” õt.
	public void touching(Virologus v) //mezÅ‘ Ã©rintÃ©sekor
	{
		//if(!elhasznalt) {
			//elhasznalt = true;
			//felajÃ¡nlja a jÃ¡tÃ©kosnak a lehetsÃ©ges cselekvÃ©seket
			Beardance br = new Beardance();
			v.uRAttacked(br,null); //megtÃ¡madjad egy medvetÃ¡ncal

		//}
		
	}
	
	public String toString() {
		return "evillab";
	}
	
}
