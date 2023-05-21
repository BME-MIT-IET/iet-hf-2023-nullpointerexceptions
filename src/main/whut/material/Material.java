package whut.material;
import java.io.Serializable;
import java.util.Random;

//Egy anyagtulajdonsagaiert felel
//int value - mennyi az anyag mennyisege
public abstract class Material implements Serializable{
	private int amount;

	// Konstruktor, beallitja random az anyag erteket
	protected Material()
	{
		Random rand = new Random();
		amount = rand.nextInt(50) + 1;
	}

	// az anyag tipusut adja vissza, csak a leszarmazottak valositjak meg
	public abstract String getType();

	// megmondja, hogy a parameterkent kapott anyag hasonlo tipusu-e mint ezen amyag
	// Material mat1 - a kapott anyag, ezt hasonlitja ossze
	public boolean sameAs(Material material) {
		return material.getType().equals(this.getType());
	}

	// visszadaja az anyag mennyiseget
	public int getAmount() {
		return amount;
	}

	// beellitja az anyag mennyiseget
	// int ujValue - erre az ertekre lesz allitva az anyag mennyisege
	public void setAmount(int value) {
		amount = value;
	}
	
	
	//a getType a bels�h�z kell ez a kuls� elereshez
	public abstract boolean check(String materialType);
}
