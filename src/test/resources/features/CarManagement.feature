@ci
Feature: Car Management

  Scenario: View the list of all cars
    Given I am on the "car" page
    Then I should see a list with all the "cars"

  Scenario: I open the Car create page
    Given I am on the "car" page
    When I click on the "car" create button
    Then I should see the "car" create page

  Scenario: I create a new car
    Given I am on the "car" create page
    When I input the car data
    And I click on the save button
    Then I should see a list with all the "cars"
    And I should see the new car on the car page

  Scenario: I open the Car update page
    Given I am on the "car" page
    When I click on the update button for the last element
    Then I should see the update "car" page

  Scenario: I update a car
    Given I am on the "car" update page
    When I input the updated car data
    And I click on the save button
    And I should see the updated car on the car page

  Scenario: I delete a car
    Given I am on the "car" page
    When I click on the delete button for the "last" element
    Then The "car" list should contain one less element


