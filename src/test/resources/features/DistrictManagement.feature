@district_ci
Feature: District Management
  Scenario: View the list of all districts
    Given I am on the "district" page
    Then I should see a list with all the "districts"

  Scenario: I open the district create page
    Given I am on the "district" page
    When I click on the "district" create button
    Then I should see the "district" create page

  Scenario: I create a new district
    Given I am on the "district" create page
    When I input the district data
    And I click on the save button
    Then I should see a list with all the "districts"
    And I should see the new district on the district page

  Scenario: I open the district update page
    Given I am on the "district" page
    When I click on the update button for the first element
    Then I should see the update "district" page

  Scenario: I update the new district
    Given I am on the "district" page
    When I click on the update button for the last element
    And I input the updated district data
    And I click on the save button
    Then I should see the updated district on the district page

  Scenario: I delete the updated district
    Given I am on the "district" page
    When I click on the delete button for the "last" element
    Then The "district" list should contain one less element

  Scenario: I try to delete an active district
    Given I am on the "district" page
    When I click on the delete button for the "first" element
    Then I should see an error message
