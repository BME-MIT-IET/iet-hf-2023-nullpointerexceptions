package field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Map implements Serializable
{
	private ArrayList<Field> fields;
	
	public Map()
	{
		fields = new ArrayList<>();
	}
	
	public List<Field> getFields(){
		return fields;
	}
	
	public void addField(Field f) {
		fields.add(f);
	}
	
	public Field getField(int index) {
		return fields.get(index);
	}
	
	public int getSize() {
		return fields.size();
	}
	
}
