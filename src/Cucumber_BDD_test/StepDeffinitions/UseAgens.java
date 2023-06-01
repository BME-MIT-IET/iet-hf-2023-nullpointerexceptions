package StepDeffinitions;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import whut.Field;
import whut.Forget;
import whut.Protection;
import whut.Virologus;
import whut.Vitusdance;

public class UseAgens {
	Field f1 = new Field();
	Virologus v1= new Virologus();
	Vitusdance vitus = new Vitusdance();
	
	Virologus v2= new Virologus();
	Protection prot=new Protection();
	Forget forg= new Forget();
	
@Given("player1 learned Vitusdance")
public void player1_learned_vitusdance() {
    f1.accept(v1);
    f1.accept(v2);
    v1.addAgens(vitus);
}

@When("player2 have protection agens active")
public void player2_have_protection_agens_active() {
    v2.addAgensOnMe(prot);
}

@And("player1 cast on player2")
public void player1_cast_on_player2() {
   v2.uRAttacked(vitus, v1);
}

@Then("The cast status is fail")
public void the_cast_status_is_fail() {
	int length = v2.getAgensOnMe().size();
	assertEquals(1, length);
}

@When("player2 have forget agens active")
public void player2_have_forget_agens_active() {
	v2.removeAgensOnMe(prot);
   v2.addAgensOnMe(forg);
}

@Then("The cast status is success")
public void the_cast_status_is_success() {
	int length = v2.getAgensOnMe().size();
	assertEquals(2, length);
}

}
