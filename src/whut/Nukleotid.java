package whut;
//egy konkret anyagtipusert felel
public class Nukleotid extends Material
{
	//Konstruktor, amit az osevel egyenlo
	public Nukleotid() {
		super();
	}
	
	//megvalasitott fuggveny, mely az osben nincs implementalva
	//visszaadja a tipusat az anyagnak
	@Override
	protected String getType() 
	{
		//System.out.println(">[:Nukleotid].getType()");
		return "Nukleotid";
	}
	
	
	public boolean Check(String s) {
		if(s.equals("nukleotid")) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "nukleotid";
	}

}
