@ci
Feature: Invoice Management

  Scenario: View the list of all invoices
    Given I am on the "invoice" page
    Then I should see a list with all the "invoices"

  Scenario: I open the invoice create page
    Given I am on the "invoice" page
    When I click on the "invoice" create button
    Then I should see the "invoice" create page

  Scenario: I create a new invoice
    Given I am on the "invoice" create page
    When I input the invoice data
    And I click on the save button
    Then I should see a list with all the "invoices"
    And I should see the new invoice on the invoice page

  Scenario: I open the invoice update page
    Given I am on the "invoice" page
    When I click on the update button for the first element
    Then I should see the update "invoice" page

  Scenario: I update the new invoice
    Given I am on the "invoice" page
    When I click on the update button for the last element
    And I input the updated invoice data
    And I click on the save button
    Then I should see the updated invoice on the invoice page

  Scenario: I delete the updated invoice
    Given I am on the "invoice" page
    When I click on the delete button for the "last" element
    Then The "invoice" list should contain one less element

