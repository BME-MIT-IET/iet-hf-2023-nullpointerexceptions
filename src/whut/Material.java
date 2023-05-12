package whut;
import java.io.Serializable;
import java.util.Random;

//Egy anyagtulajdonsagaiert felel
//int value - mennyi az anyag mennyisege
public abstract class Material implements Serializable{
	private int value;

	// Konstruktor, beallitja random az anyag erteket
	public Material()
	{
		Random rand = new Random();
		value = rand.nextInt(50) + 1;
	}

	// az anyag tipusut adja vissza, csak a leszarmazottak valositjak meg
	protected abstract String getType();

	// megmondja, hogy a parameterkent kapott anyag hasonlo tipusu-e mint ezen amyag
	// Material mat1 - a kapott anyag, ezt hasonlitja ossze
	public boolean isSame(Material mat1) {
		return mat1.getType().equals(this.getType());
	}

	// visszadaja az anyag mennyiseget
	public int getValue() {
		return value;
	}

	// beellitja az anyag mennyiseget
	// int ujValue - erre az ertekre lesz allitva az anyag mennyisege
	public void setValue(int ujValue) {
		value = ujValue;
	}
	
	
	//a getType a bels�h�z kell ez a kuls� elereshez
	public boolean Check(String s) {
		return false;
	}
}
