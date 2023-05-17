package whut.field;
import whut.item.Item;
import whut.ui.control.MyRunnable;
import whut.ui.observer.ShelterObserver;
import whut.player.Virologus;

import java.util.ArrayList;

public class Shelter extends Field
{
	private ArrayList<Item> items;
	
	public Shelter()
	{
		super();
		items = new ArrayList<Item>();
		this.attach(new ShelterObserver(this));
	}
	
	//tölri a tárgyat a shelterről
	@Override
	public void removeItem(Item i)
	{
		items.remove(i);
		//MyRunnable.getGame().myNotify();
	}
	
	//hozzáadja a tárgyat a shelterhez
	@Override
	public void addItem(Item i)
	{
		items.add(i);
		//MyRunnable.getGame().myNotify();
	}
	
	
	//A param�terk�nt megadott virol�gus meg�rinti a mez�t. 
	//Megjelent�ti a j�t�kosnak a lehets�ges cselekv�seket, majd a kiv�lasztott cselekv�s alapj�n ind�tja el a megfelel� folyamatot a virol�gusn�l. 
	//Lehets�ges cselekv�sek: t�rgylop�s, anyaglop�s, �genshaszn�lat, t�rgyfelv�tel, t�rgy lerak�s.
	@Override
	public void touching(Virologus v) //mező érintésekor
	{

	}

	@Override
	public ArrayList<Item> getItems() {
		return items;
	}
	
	@Override
	public Item getItem(String getThis) {
		for(Item i : items) {
			if(i.Check(getThis)) {
				return i;
			}
		}
		MyRunnable.log("There is no "+ getThis + " here!");
		return null;
	}
	
	@Override
	public String toString() {
		return "shelter";
	}
}


