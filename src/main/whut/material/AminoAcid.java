package whut.material;
//egy konkret anyagtipusert felel
public class AminoAcid extends Material {
	// Konstruktor, amit az osevel egyenlo
	public AminoAcid() {
		super();
	}

	// megvalositott fuggveny, mely az osben nincs implementalva
	// visszaadja a tipusat az anyagnak
	@Override
    public String getType() {
		return toString();
	}
	
	public boolean check(String materialType) {
		return materialType.equals(toString());
	}
	
	public String toString() {
		return "AminoAcid";
	}

}
