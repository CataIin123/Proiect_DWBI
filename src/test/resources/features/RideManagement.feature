Feature: Ride Management
  Scenario: View the list of all rides
    Given I am on the "ride" page
    Then I should see a list with all the "rides"

  Scenario: I open the ride create page
    Given I am on the "ride" page
    When I click on the "ride" create button
    Then I should see the "ride" create page

  Scenario: I create a new ride
    Given I am on the "ride" create page
    When I input the ride data
    And I click on the save button
    Then I should see a list with all the "rides"
    And I should see the new ride on the ride page

  Scenario: I open the ride update page
    Given I am on the "ride" page
    When I click on the update button for the first element
    Then I should see the update "ride" page

  Scenario: I update a ride
    Given I am on the "ride" update page
    When I input the updated ride data
    And I click on the save button
    And I should see the updated ride on the ride page

  Scenario: I delete a ride
    Given I am on the "ride" page
    When I click on the delete button for the "first" element
    Then The "ride" list should contain one less element




