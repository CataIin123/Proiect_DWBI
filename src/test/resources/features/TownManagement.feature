@town_ci
Feature: Town Management
  Scenario: View the list of all towns
    Given I am on the "town" page
    Then I should see a list with all the "towns"

  Scenario: I open the town create page
    Given I am on the "town" page
    When I click on the "town" create button
    Then I should see the "town" create page

  Scenario: I create a new town
    Given I am on the "town" create page
    When I input the town data
    And I click on the save button
    Then I should see a list with all the "towns"
    And I should see the new town on the town page

  Scenario: I open the town update page
    Given I am on the "town" page
    When I click on the update button for the first element
    Then I should see the update "town" page

  Scenario: I update the new town
    Given I am on the "town" page
    When I click on the update button for the last element
    And I input the updated town data
    And I click on the save button
    Then I should see the updated town on the town page

  Scenario: I delete the updated town
    Given I am on the "town" page
    When I click on the delete button for the "last" element
    Then The "town" list should contain one less element
