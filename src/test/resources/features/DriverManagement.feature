@ss
Feature: Driver Management
  Scenario: View the list of all drivers
    Given I am on the "driver" page
    Then I should see a list with all the "drivers"

  Scenario: I open the driver create page
    Given I am on the "driver" page
    When I click on the "driver" create button
    Then I should see the "driver" create page

  Scenario: I create a new driver
    Given I am on the "driver" create page
    When I input the driver data
    And I click on the save button
    Then I should see a list with all the "drivers"
    And I should see the new driver on the driver page

  Scenario: I open the driver update page
    Given I am on the "driver" page
    When I click on the update button for the last element
    Then I should see the update "driver" page

  Scenario: I update a driver
    Given I am on the "driver" update page
    When I input the updated driver data
    And I click on the save button
    And I should see the updated driver on the driver page

  Scenario: I delete a driver
    Given I am on the "driver" page
    When I click on the delete button for the "last" element
    Then The "driver" list should contain one less element

