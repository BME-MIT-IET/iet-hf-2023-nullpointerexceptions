package StepDeffinitions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import whut.Entity;
import whut.Game;
import whut.Menu;

public class StartGame {
	Menu menu;
	Game newGame;
	int playerNumber;
	
	
	@Given("^user is on selector page$")
	public void user_is_on_selector_page() {
		menu = new Menu();
	}

	@When("user select players {int}")
	public void user_select_players_number(int number) {
		playerNumber = number;
	}

	@And("clicks on Start Game")
	public void clicks_on_start_game() {
		newGame = new Game(playerNumber);
	}

	@Then("game started with {int} players")
	public void game_started_with_number_of_players(int number) {
		ArrayList<Entity> players = newGame.getEntity();
		int sizeOfPlayers = players.size();
		assertEquals(sizeOfPlayers, number);
	}

}
