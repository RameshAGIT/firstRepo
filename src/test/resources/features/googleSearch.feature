Feature: To Test Google search

  @SmokeSuite @RegressionSuite
  Scenario Outline: Google search Test
    Given Open browser and launch Google
    When Enter "<SearchText>" to search
    Then User should able to search "<SearchText>"

    Examples: 
      | SearchText |
      | Selenium Grid	 |
      | Cucumber Testing  |
