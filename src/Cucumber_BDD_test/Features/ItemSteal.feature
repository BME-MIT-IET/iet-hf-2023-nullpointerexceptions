Feature: Player1 steals an item from player2 while he is stunned.

@ItemSteal1
Scenario: Item steal from another player.
Given player1 and player2 on same field, player2 stunned
When player1 steal an item from player2
Then The action is successful


@ItemSteal2
Scenario: Item steal attempt from another player
Given player2 is not stunned
When player1 steal an item from player2
Then The action is fail