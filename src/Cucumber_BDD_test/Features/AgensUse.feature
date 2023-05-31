Feature: Player use agens
  A player use an agens against anotherplayer

@UseAgensAgainstProtection
  Scenario Outline: Use agens on another player with protection
    Given player1 learned Vitusdance
    When player2 have <active> agens active
    And player1 cast on player2
    Then The cast status is <status>

    Examples: 
      | active     | status  |
      | protection | fail    |
      | forget     | success |
