Feature: Route Management
  Scenario: View the list of all routes
    Given I am on the "route" page
    Then I should see a list with all the "routes"

  Scenario: I open the route create page
    Given I am on the "route" page
    When I click on the "route" create button
    Then I should see the "route" create page

  Scenario: I create a new route
    Given I am on the "route" create page
    When I input the route data
    And I click on the save button
    Then I should see a list with all the "routes"
    And I should see the new route on the route page

  Scenario: I open the route update page
    Given I am on the "route" page
    When I click on the update button for the first element
    Then I should see the update "route" page

  Scenario: I update a route
    Given I am on the "route" page
    When I click on the update button for the first element
    And I input the updated route data
    And I click on the save button
    Then I should see the updated route on the route page

  Scenario: I try to delete an active route
    Given I am on the "route" page
    When I click on the delete button for the "first" element
    Then I should see an error message
