Feature: Cart Functionality
  Background: User logged out the website
    Given I am on the landing page
    When I see my account logged in
    Then I will logout

  Scenario: Empty Cart
    Given On the landing page I add some items in the Cart
      |lamps for living room|
      |arm chair            |
    When I navigate to Cart page
    And I delete all items
    Then The cart will be empty
