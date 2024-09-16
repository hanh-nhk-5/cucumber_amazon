Feature: Cart Functionality
  Background: User logged in the website
    Given I am on the website
    When I sign in
    Then I am logged in successfully

  Scenario: Empty Cart
    Given I add some items in the Cart
    |lamps for living room|
    |arm chair            |
    When I navigate to Cart page
    And I delete all items
    Then The cart will be empty
