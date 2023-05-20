package material;
//egy konkret anyagtipusert felel
public class Aminosav extends Material {
	// Konstruktor, amit az osevel egyenlo
	public Aminosav() {
		super();
	}

	// megvalositott fuggveny, mely az osben nincs implementalva
	// visszaadja a tipusat az anyagnak
	@Override
    public String getType() {
		return "Aminosav";
	}
	
	public boolean check(String s) {
		return s.equals("amino");
	}
	
	public String toString() {
		return "amino";
	}

}
