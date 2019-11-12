Feature: ruler test

  @selenium @smoke
  Scenario: Positive ruler increase test
    Given page is opened
    When ruler "more" button is clicked
    Then ruler value is "30"
    And the ruler length is "3"

  @selenium @smoke
  Scenario: Positive ruler decrease test
    Given page is opened
    When ruler "less" button is clicked
    Then ruler value is "10"
    And the ruler length is "1"

  @selenium
  Scenario Outline: Various more button clicks
    Given page is opened
    When ruler "more" button is clicked "<clicksAmount>" times
    Then ruler value is "<rulerValue>"
    And the ruler length is "<rulerLength>"

    Examples:
    | clicksAmount  | rulerValue  | rulerLength |
    | 4             | 60          | 6           |
    | 0             | 20          | 2           |
    | 8             | 100         | 10          |
    | 9             | 100         | 10          |

  @selenium
  Scenario Outline: Various less button clicks
    Given page is opened
    When ruler "less" button is clicked "<clicksAmount>" times
    Then ruler value is "<rulerValue>"
    And the ruler length is "<rulerLength>"

    Examples:
      | clicksAmount  | rulerValue  | rulerLength |
      | 1             | 10          | 1           |
      | 0             | 20          | 2           |
      | 2             | 10          | 1           |
      | 3             | 10          | 1           |

  @selenium @complex
  Scenario Outline: subsequent more and less button clicks
    Given page is opened
    When ruler "<firstButton>" button is clicked "<firstButtonClicksAmount>" times
    And ruler "<secondButton>" button is clicked "<secondButtonClicksAmount>" times
    Then ruler value is "<rulerValue>"
    And the ruler length is "<rulerLength>"

    Examples:
    | firstButton| firstButtonClicksAmount | secondButton | secondButtonClicksAmount | rulerValue  | rulerLength |
    | more       | 1                       | less         | 1                        | 20          | 2           |
    | more       | 8                       | less         | 1                        | 90          | 9           |
    | more       | 9                       | less         | 1                        | 90          | 9           |
    | less       | 1                       | more         | 2                        | 30          | 3           |
    | less       | 2                       | more         | 2                        | 30          | 3           |
    | less       | 1                       | more         | 9                        | 100         | 10          |