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

#  Scenario Outline: Cart is reserve after logout
#    Given I add some items <items> in the Cart
#    When I navigate to Cart page
#    And modify the amount of items <amount>
#    And I sign out
#    And I sign in
#    And I navigate to Cart page
#    Then the cart will be the same as before logout
#    Examples:
#    |items                                        |amount|
#    |tea pot, HexClad pan                         | 2, 7          |
#    |tennis racket, tennis grip, tennis grip tape | 5, 4, 3       |

