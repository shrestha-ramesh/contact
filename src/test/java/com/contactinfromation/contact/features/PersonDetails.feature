Feature: Contact Information Management System

  Scenario: Retrieve details of persons by request parameter
    Given the server is running
    When I send a GET request to "/person/details" with request parameter firstName "Ramesh"
    Then the response status should be 200
    And the response should contain person details with first name "Ramesh"

  Scenario: Retrieve details of persons by path variable
    Given the server is running
    When I send a GET request to "/person/details/Shrestha"
    Then the response status should be 100
    And the response should contain person details with last name "Shrestha"
