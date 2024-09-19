Feature: Cart Functionality
  Background: User already signed in the website
    Given I already signed in


  @RegressionTest
  Scenario: Empty Cart with Account
    Given I add some items into the Cart
    |lamps for living room|
    |arm chair            |
    When I delete all items on Cart
    Then The cart will be empty

  @SmokeTest
  @RegressionTest
  Scenario Outline: Cart should be reserved after sign out
    Given The cart is empty
    And I add some items <items> into the Cart
    And On the Cart page, I modify amount of the added items in random order <amounts>
    When I sign out
    And I sign in
    Then I should see all items in the Cart as before sign out
    Examples:
    |items                                        |amounts|
    |tea pot, HexClad pan                         | 2, 7          |
    |tea pot, armchair | 5, 4       |

