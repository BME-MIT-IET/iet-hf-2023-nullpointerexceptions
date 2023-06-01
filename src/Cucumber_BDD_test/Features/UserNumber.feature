Feature: feature to test player moves

  @UserNumberScenario
  Scenario Outline: User number selection is succsessful
    Given user is on selector page
    When user select players <number>
    And clicks on Start Game
    Then game started with <number> players

    Examples: 
      | number |
      |      1 |
      |      3 |

