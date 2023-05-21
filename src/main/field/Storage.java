package field;


import material.Packet;
import player.Entity;
import player.Virologus;
import ui.control.MyRunnable;
import ui.observer.StorageObserver;

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


