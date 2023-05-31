package StepDeffinitions;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import whut.Aminosav;
import whut.Packet;
import whut.Storage;
import whut.Virologus;

public class PickUpAmino {
	Storage f2 = new Storage();
	Packet packet = new Packet();
	Aminosav amino = new Aminosav();
	Virologus v= new Virologus();
	
	@Given("the player on storage")
	public void the_player_on_storage() {
		f2.accept(v);
		packet.addMaterial(amino);
		f2.setPacket(packet);
	}

	@When("pick up amino")
	public void pick_up_amino() {
		v.getPacket().addMaterial(amino);
	}

	@Then("amino ammount increases")
	public void amino_ammount_increases() {
		int ammount = v.getPacket().getMaterial("amino").getValue();
		int amm= amino.getValue();
		assertEquals(ammount , amm);
	}
}
