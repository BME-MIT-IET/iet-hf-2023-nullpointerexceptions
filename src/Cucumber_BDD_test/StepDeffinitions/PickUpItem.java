package StepDeffinitions;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import whut.Aminosav;
import whut.Cloak;
import whut.Lab;
import whut.Packet;
import whut.Shelter;
import whut.Storage;
import whut.StunCode;
import whut.Virologus;

public class PickUpItem {
	Virologus v= new Virologus();
	Shelter f1 = new Shelter();
	Cloak cloak = new Cloak();
	
	Storage f2 = new Storage();
	Packet packet = new Packet();
	Aminosav amino = new Aminosav();
	
	StunCode stun = new StunCode();
	Lab f3 = new Lab(stun);
	
	@Given("player stay on shelter")
	public void player_stay_on_shelter() {
		f1.accept(v);
		f1.addItem(cloak);
	}

	@When("pick up item")
	public void pick_up_item() {
		v.pickUpItem(cloak);
	}

	@Then("item is active on him")
	public void item_is_active_on_him() {
		int length = v.getItemHave().size();
		assertEquals(1, length);
	}
	
}
