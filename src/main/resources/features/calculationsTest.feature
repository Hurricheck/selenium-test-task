Feature: calculations test

  @selenium @smoke
  Scenario: Positive calculation test
    Given page is opened
    When correct answer has been input
    And the result was submitted
    Then the correct image was shown

  @selenium @negative
  Scenario: Negative calculation test
    Given page is opened
    When incorrect answer has been input
    And the result was submitted
    Then the incorrect image was shown

  @selenium @negative
  Scenario Outline: Invalid values calculation test
    Given page is opened
    When "<sample>" has been input as answer
    And the result was submitted
    Then the incorrect image was shown

    Examples:
    | sample |
    | 0      |
    | -1     |
    | aa     |
    | фф     |

  @selenium
  Scenario Outline: Positive calculation test after more button has been clicked n-times
    Given page is opened
    When ruler "more" button is clicked "<clicksAmount>" times
    And correct answer has been input
    And the result was submitted
    Then the correct image was shown

    Examples:
    | clicksAmount  |
    | 1             |
    | 5             |
    | 8             |
    | 9             |
    | 10            |

  @selenium
  Scenario Outline: Positive calculation test after less button has been clicked n-times
    Given page is opened
    When ruler "less" button is clicked "<clicksAmount>" times
    And correct answer has been input
    And the result was submitted
    Then the correct image was shown

    Examples:
      | clicksAmount  |
      | 1             |
      | 2             |

  @selenium @negative
  Scenario Outline: Negative calculation test after more button has been clicked n-times
    Given page is opened
    When ruler "more" button is clicked "<clicksAmount>" times
    And incorrect answer has been input
    And the result was submitted
    Then the incorrect image was shown

    Examples:
      | clicksAmount  |
      | 1             |
      | 5             |
      | 8             |
      | 9             |
      | 10            |

  @selenium @negative
  Scenario Outline: Negative calculation test after less button has been clicked n-times
    Given page is opened
    When ruler "less" button is clicked "<clicksAmount>" times
    And correct answer has been input
    And the result was submitted
    Then the correct image was shown

    Examples:
      | clicksAmount  |
      | 1             |
      | 2             |