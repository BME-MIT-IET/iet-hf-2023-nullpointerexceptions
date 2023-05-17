package whut.field;

import whut.ui.observer.LabObserver;
import whut.genetic_code.GeneticCode;

public class Lab extends Field
{
	private GeneticCode geneticCode;
	
	public Lab()
	{
		this.attach(new LabObserver(this));
	}
	
	public Lab(GeneticCode g)
	{
		geneticCode = g;
		this.attach(new LabObserver(this));
	}
	
	@Override
	public void setGeneticCode(GeneticCode g) //genetikus kód beállítása
	{
		geneticCode = g;
	}
	@Override
	public GeneticCode codeHere() {
		return geneticCode;
	}
	
	@Override
	public String toString() {
		return "lab";
	}
}

