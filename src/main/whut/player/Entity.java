package whut.player;

import org.bouncycastle.util.Pack;
import whut.material.Packet;
import whut.ui.control.View;
import whut.field.Field;

import java.io.Serializable;

public class Entity extends View implements Serializable{

	protected Field field;
	
	public void step() {
		//lesz�rmazott defini�lja
	}
	
	public void move(Field field) {
		this.field.remove(this);
		field.accept(this);
	}
	
	public Field getField() {
		return field;
	}
	
	public void setField(Field field) {
		this.field = field;
	}

	public void destroyMaterial(Packet packet){}
}