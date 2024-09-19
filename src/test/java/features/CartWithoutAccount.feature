Feature: Cart Functionality
  Background: User has not logged in yet
    Given I have not logged in yet
  @RegressionTest
  Scenario: Empty Cart without Account
    Given I add some items into the Cart
      |lamps for living room|
      |arm chair            |
    When I delete all items on Cart
    Then The cart will be empty
