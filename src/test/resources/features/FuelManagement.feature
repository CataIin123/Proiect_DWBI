Feature: Fuel Management
  Scenario: View the list of all fuels
    Given I am on the "fuel" page
    Then I should see a list with all the "fuels"

  Scenario: I open the fuel create page
    Given I am on the "fuel" page
    When I click on the "fuel" create button
    Then I should see the "fuel" create page
  Scenario: I create a new fuel
    Given I am on the "fuel" create page
    When I input the fuel data
    And I click on the save button
    Then I should see a list with all the "fuels"
    And I should see the new fuel on the fuel page

  Scenario: I open the fuel update page
    Given I am on the "fuel" page
    When I click on the update button for the first element
    Then I should see the update "fuel" page

#  Scenario: I update a fuel
#    Given I am on the "fuel" page
#    When I click on the update button for the last element
#    When I input the updated fuel data
#    And I click on the save button
#    And I should see the updated fuel on the fuel page

  Scenario: I delete a fuel
    Given I am on the "fuel" page
    When I click on the delete button for the "last" element
    Then The "fuel" list should contain one less element
