package whut.material;
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
    public String getType()
	{
		return "Nukleotid";
	}
	
	
	public boolean check(String s) {
		return s.equals("nukleotid");
	}
	
	public String toString() {
		return "nukleotid";
	}

}
