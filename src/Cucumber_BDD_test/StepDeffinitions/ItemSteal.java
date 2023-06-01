package StepDeffinitions;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import whut.Field;
import whut.Sack;
import whut.Stun;
import whut.Virologus;

public class ItemSteal {
	Field f1 = new Field();
	Virologus v1 =new Virologus();
	Virologus v2 =new Virologus();
	Stun stun = new Stun();
	Sack sack = new Sack();
	
	@Given("player1 and player2 on same field, player2 stunned")
	public void player1_and_player2_on_same_field_player2_stunned() {
		f1.accept(v1);
		f1.accept(v2);
		v2.addItem(sack);
		v2.addAgensOnMe(stun);
	}

	@When("player1 steal an item from player2")
	public void player1_steal_an_item_from_player2() {
	    v1.stealItem(v2, sack);
	}

	@Then("The action is successful")
	public void the_action_is_successful() {
	    int length = v1.getItemHave().size();
	    assertEquals(1, length);
	}
	
	@Given("player2 is not stunned")
	public void player2_is_not_stunned() {
		f1.accept(v1);
		f1.accept(v2);
		v2.addItem(sack);
	}

	@Then("The action is fail")
	public void the_action_is_fail() {
		int length = v1.getItemHave().size();
	    assertEquals(0, length);
	}

}
