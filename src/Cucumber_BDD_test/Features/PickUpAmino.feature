Feature: Pick up amino material from storage

@IsOnStorage
Scenario: The player is standing on storage
Given the player on storage
When pick up amino
Then amino ammount increases