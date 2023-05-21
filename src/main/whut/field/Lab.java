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
	
	public Lab(GeneticCode geneticCode)
	{
		this.geneticCode = geneticCode;
		this.attach(new LabObserver(this));
	}
	
	@Override
	public void setGeneticCode(GeneticCode geneticCode) //genetikus kód beállítása
	{
		this.geneticCode = geneticCode;
	}
	@Override
	public GeneticCode getGeneticCode() {
		return geneticCode;
	}
	
	@Override
	public String toString() {
		return "Lab";
	}
}

