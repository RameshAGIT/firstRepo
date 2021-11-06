Feature: To Test the Shipping details in DATAX Shipping Company 
 
  Scenario Outline: Title of your scenario outline 
    Given Start chrome browser and open the application
    When Test the text in H2 tag and the "<link>" for ShipmentID
    Then Validate the Customer name "<name>" is displayed
 
    Examples:  
      | link    | name | 
      | 6543217 | Maya | 
