package whut.field;


import whut.agent.Beardance;
import whut.player.Virologist;
import whut.genetic_code.StunCode;

//A fert�z� laborat�rium mez� nyilv�ntart�s��rt felel.
//Kezeli a mez�n t�rt�n� interakci�k megval�sul�s�t. Kezeli a virol�gusok megfert�z�s�t.
public class EvilLab extends Lab
{
	public EvilLab()
	{
		super(new StunCode());
	}
 
	//L�trehoz egy medvet�nc �genst, �s megt�madja vele a virol�gust, ezzel �megfert�zve� �t.
	@Override
	public void touching(Virologist virologist) //mező érintésekor
	{
		Beardance br = new Beardance();
		virologist.gotAttacked(br,null); //megtámadjad egy medvetáncal
	}
	
	@Override
	public String toString() {
		return "EvilLab";
	}
	
}
