@ci
Feature: Client Management
  Scenario: View the list of all clients
    Given I am on the "client" page
    Then I should see a list with all the "clients"

  Scenario: I open the client create page
    Given I am on the "client" page
    When I click on the "client" create button
    Then I should see the "client" create page

  Scenario: I create a new client
    Given I am on the "client" create page
    When I input the client data
    And I click on the save button
    Then I should see a list with all the "clients"
    And I should see the new client on the client page

  Scenario: I open the client update page
    Given I am on the "client" page
    When I click on the update button for the first element
    Then I should see the update "client" page

  Scenario: I update a client
    Given I am on the "client" update page
    When I input the updated client data
    And I click on the save button
    And I should see the updated client on the client page

  Scenario: I delete a client
    Given I am on the "client" page
    When I click on the delete button for the "second" element
    Then The "client" list should contain one less element

  Scenario: I try to delete an active client
    Given I am on the "client" page
    When I click on the delete button for the "first" element
    Then I should see an error message
