Feature: As a user I want to get ability to user Docs pages

  @smoke
  @issue=TEST-1234 @link=https://example.com
  Scenario: Docs sections should contains appropriate columns
    Given the user opens Cucumber website
    When the user clicks on the "Docs" section
    And the user clicks on the "Installation" item
    Then there are 10 items in the left-side menu
    And left-side menu contains the following links
      | Installation                 |
      | Guides                       |
      | Professional Services        |
      | Cucumber                     |
      | Gherkin Syntax               |
      | Behaviour-Driven Development |
      | Community                    |
      | Sponsors                     |
      | Tools                        |
      | Terminology                  |