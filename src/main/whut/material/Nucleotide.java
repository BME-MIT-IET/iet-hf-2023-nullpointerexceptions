package whut.material;
//egy konkret anyagtipusert felel
public class Nucleotide extends Material
{
	//Konstruktor, amit az osevel egyenlo
	public Nucleotide() {
		super();
	}
	
	//megvalasitott fuggveny, mely az osben nincs implementalva
	//visszaadja a tipusat az anyagnak
	@Override
    public String getType()
	{
		return toString();
	}
	
	
	public boolean check(String materialType) {
		return materialType.equals(toString());
	}
	
	public String toString() {
		return "Nucleotide";
	}

}
