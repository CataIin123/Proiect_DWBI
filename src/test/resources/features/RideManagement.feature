@ci
Feature: Ride Management

#  Scenario: View the list of all rides
#    Given I am on the "ride" page
#    Then I should see a list with all the "rides"
#
#  Scenario: I open the Ride create page
#    Given I am on the "ride" page
#    When I click on the "ride" create button
#    Then I sould see the "ride" create page

  Scenario: I create a new ride
    Given I am on the "ride" create page
    When I input the ride data
    And I click on the save button
    Then I should see a list with all the "rides"
    And I should see the new ride on the Ride page


