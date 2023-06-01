package StepDeffinitions;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import whut.Lab;
import whut.Virologus;

public class PickUpCode {

	Lab f3 = new Lab();
	Virologus v= new Virologus();
	
	@Given("player stay on lab")
	public void player_stay_on_lab() {
		f3.accept(v);
	}

	@When("pick up stun")
	public void pick_up_stun() {
		v.learnGeneticCode(f3.codeHere());
	}

	@Then("stun is added to the learned agents")
	public void stun_is_added_to_the_learned_agents() {
		int ammount = v. getGeneticCodeHave().size();
		assertEquals(ammount , 1);
	}
}
