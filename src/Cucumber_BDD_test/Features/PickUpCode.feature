Feature: Pick up genetic code from labor

@IsOnLabor
Scenario: Player stayes on labor and pick up stun
Given player stay on lab
When pick up stun
Then stun is added to the learned agents