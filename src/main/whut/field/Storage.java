package whut.field;


import whut.material.Packet;
import whut.player.Entity;
import whut.player.Virologus;
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
	public void accept(Entity e) {
		Virologus ag = (Virologus)e;
		au.add(ag);
		ag.setField(this);
		
		ag.destroyMaterial(packet);
		
	}
	
	public void setPacket(Packet p) {
		packet = p;
		MyRunnable.getGame().myNotify();
	}
	
	@Override
	public Packet getPacket() {
		return packet;
	}
	
	@Override
	public String toString() {
		return "storage";
	}
}


