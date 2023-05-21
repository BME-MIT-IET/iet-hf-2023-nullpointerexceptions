package whut.field;


import whut.material.Packet;
import whut.player.Entity;
import whut.player.Virologist;
import whut.ui.control.MyRunnable;
import whut.ui.observer.StorageObserver;

public class Storage extends Field
{
	private Packet packet;
	
	public Storage()
	{
		super();
		packet = new Packet();
		this.attach(new StorageObserver(this));
	}
	
	
	@Override
	public void accept(Entity entity) {
		Virologist virologist = (Virologist) entity;
		agentUsableList.add(virologist);
		virologist.setField(this);
		
		virologist.destroyMaterial(packet);
		
	}
	
	public void setPacket(Packet packet) {
		this.packet = packet;
		MyRunnable.getGame().notifyObservers();
	}
	
	@Override
	public Packet getPacket() {
		return packet;
	}
	
	@Override
	public String toString() {
		return "Storage";
	}
}


