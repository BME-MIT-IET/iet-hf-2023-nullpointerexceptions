package StepDeffinitions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import whut.AgensUsable;
import whut.Entity;
import whut.Field;
import whut.Virologus;

public class Move {
	Field f1 = new Field();
	Field f2 = new Field();
	Entity v = new Virologus();
	
	@Given("user is on field f1")
	public void user_is_on_field_f1() {
		f1.accept(v);
		f1.setNeighbour(f2);
	}

	@When("user select field f2")
	public void user_select_field_f2() {
	}

	@And("clicks on Move")
	public void clicks_on_move() {
		f2.accept(v);
		f1.remove(v);
	}

	@Then("the player move to f2")
	public void the_player_move_to_f2() {
		ArrayList<AgensUsable> ent = f2.getVirologusok();
		int length = ent.size();
		assertEquals(1, length);

	}
}
