Feature: Contact Information Service

  Scenario: Retrieve person details
    Given the executor service is running
    When I request person details
    Then the person details should be returned
    And the details should include contact number, address and occupation