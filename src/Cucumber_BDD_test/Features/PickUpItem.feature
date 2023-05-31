Feature: Player picks up something

@IsOnShelter
Scenario: Player stayes on shelter and pick up an item.

Given player stay on shelter
When pick up item
Then item is active on him

