package whut.player;

import whut.ui.control.View;
import whut.field.Field;

import java.io.Serializable;

public class Entity extends View implements Serializable{

	protected Field field;
	
	public void step() {
		//lesz�rmazott defini�lja
	}
	
	public void move(Field cel) {
		field.remove(this);
		cel.accept(this);
	}
	
	public Field getField() {
		return field;
	}
	
	public void setField(Field f) {
		field = f;
	}
}