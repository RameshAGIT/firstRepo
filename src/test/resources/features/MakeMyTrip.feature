Feature: To Test Make My Trip Search
 
  @RegressionTest
  Scenario Outline: Make My Trip 
    Given Start chrome browser and open MakeMyTrip application
    When Enter "<From>" and "<To>" airports and click search
    Then Validate search details "<From>", "<To>" is displayed
 
    Examples:  
      | From      | To          |
      | Chennai		| Bengaluru   |
