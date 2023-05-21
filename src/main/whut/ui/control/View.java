package whut.ui.control;

import whut.ui.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;

public class View implements Serializable{
	protected ArrayList<Observer> observers = new ArrayList<>();
	
	public void notifyObservers() {
		for(Observer os : observers)
			os.update();
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}
}
