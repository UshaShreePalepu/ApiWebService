Feature: Verification of Objects Creation, Update and Deletion

  Scenario: Add a new object
    Given I create a new object with valid data
      | name          | year | category |
      | Test Object 1 | 2025 | Test     |
    Then the response status code should be 200
    And the response body should contain the object with name "Test Object 1"
    When I retrieve the new object created
    Then the response status code should be 200
    And the retrieve response body should contain the object with name "Test Object 1"
    When I retrieve all the objects
    Then the response status code should be 200
    And the response body should not be empty

  Scenario: Retrieve a non existence object
    Given I retrieve a non existing object
    Then the response status code should be 200
    And the response body should be empty

  Scenario: Update of an existing object
    When I update the following details of the object created
      | name           | year | category |
      | Test Object 12 | 2026 | New test |
    Then the response status code should be 200
    And the response body should contain the object with name "Test Object 12"
    When I retrieve the new object created
    Then the response status code should be 200
    And the retrieve response body should contain the object with name "Test Object 12"

  Scenario: Partial Update of an object
    When I partially update the following details
      | year |
      | 2027 |
    Then the response status code should be 200
    And the response body should contain the object with year "2027"

  Scenario: Update of a non existing object
    When I update the details of an object with null id
      | name          | year | category |
      | Test Object 1 | 2025 | Test     |
    Then the response status code should be 404

  Scenario: Delete an object
    When I delete the object created above
    Then the response status code should be 200
    And I validate the message received