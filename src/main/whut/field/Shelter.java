package whut.field;
import whut.item.Item;
import whut.ui.control.MyRunnable;
import whut.ui.observer.ShelterObserver;

import java.util.ArrayList;

public class Shelter extends Field
{
	private ArrayList<Item> items;
	
	public Shelter()
	{
		super();
		items = new ArrayList<>();
		this.attach(new ShelterObserver(this));
	}
	
	//tölri a tárgyat a shelterről
	@Override
	public void removeItem(Item item)
	{
		items.remove(item);
	}
	
	//hozzáadja a tárgyat a shelterhez
	@Override
	public void addItem(Item item)
	{
		items.add(item);
	}

	@Override
	public ArrayList<Item> getItems() {
		return items;
	}
	
	@Override
	public Item getItem(String itemType) {
		for(Item i : items) {
			if(i.check(itemType)) {
				return i;
			}
		}
		MyRunnable.log("There is no "+ itemType + " here!");
		return null;
	}
	
	@Override
	public String toString() {
		return "Shelter";
	}
}


