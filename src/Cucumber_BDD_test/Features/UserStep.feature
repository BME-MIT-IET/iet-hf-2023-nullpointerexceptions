  Feature: user step to field
  
  @UserStepScenario
  Scenario Outline: User can step on fields succsessfully
  
    Given user is on field f1
    When user select field f2
    And clicks on Move
    Then the player move to f2