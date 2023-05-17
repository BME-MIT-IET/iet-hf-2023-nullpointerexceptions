package whut.field;
import whut.field.Field;

import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable
{
	private ArrayList<Field> Fields;
	
	public Map()
	{
		Fields = new ArrayList<Field>();
	}
	
	public ArrayList<Field> getFields(){
		return Fields;
	}
	
	public void addField(Field f) {
		Fields.add(f);
	}
	
	public Field getField(int index) {
		return Fields.get(index);
	}
	
	public int getSize() {
		return Fields.size();
	}
	
}
