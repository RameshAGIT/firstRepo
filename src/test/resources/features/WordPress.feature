Feature: To Test Wordpress Login 
 
  @SmokeSuite @RegressionSuite
  Scenario Outline: Wordpress Test 
    Given Start chrome browser and open the Wordpress application
    When Enter UserName and Password and Click on Login button
    Then Validate the landing page text "<Expected Text>" is displayed
 
    Examples:  
      | Expected Text         | 
      | Welcome to WordPress! | 
      