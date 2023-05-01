@region_ci
Feature: Region Management
  Scenario: View the list of all regions
    Given I am on the "region" page
    Then I should see a list with all the "regions"

  Scenario: I open the region create page
    Given I am on the "region" page
    When I click on the "region" create button
    Then I should see the "region" create page

  Scenario: I create a new region
    Given I am on the "region" create page
    When I input the region data
    And I click on the save button
    Then I should see a list with all the "regions"
    And I should see the new region on the region page

  Scenario: I open the region update page
    Given I am on the "region" page
    When I click on the update button for the first element
    Then I should see the update "region" page

  Scenario: I update the new region
    Given I am on the "region" page
    When I click on the update button for the last element
    And I input the updated region data
    And I click on the save button
    Then I should see the updated region on the region page

  Scenario: I delete the updated region
    Given I am on the "region" page
    When I click on the delete button for the "last" element
    Then The "region" list should contain one less element

  Scenario: I try to delete an active region
    Given I am on the "region" page
    When I click on the delete button for the "first" element
    Then I should see an error message
