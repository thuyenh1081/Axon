Feature: Search weather in your city

  Background:
    Given The site is opened

  Scenario: Detail weather is shown when click on ArrowDown icon
    And Making a search with keyword is "singapore"
    And Click on 10 day link
    And Click on ArrownDown icon of the "2nd" day
    Then The detail weather of the "2nd" day is shown

  Scenario: Detail weather is hidden when click on ArrowUp icon
    And Making a search with keyword is "singapore"
    And Click on 10 day link
    And Click on ArrownUp icon of the "1st" day
    Then The detail weather of the "1st" day is hidden

  Scenario: Check 10 days are listed in detail of weather
    And Making a search with keyword is "singapore"
    And Click on 10 day link
    Then Total of days that listed with weather information is 10

  Scenario Outline: The weather info is updated after changing location
    And Making a search with keyword is "<first location>"
    And Click on 10 day link
    And Making a search with keyword is "<new location>"
    Then The weather info is changed with title is not "<first location >"
    Examples:
      |first location |new location|
      |singapore      |vietnam     |

  Scenario: Check weather information in 10 days
    And Making a search with keyword is "singapore"
    And Click on 10 day link
    Then Written the weather info into a file
