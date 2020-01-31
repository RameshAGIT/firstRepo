Feature: To Test the Shipping details in DATAX Shipping Company 
 
 @ShippingTest
  Scenario Outline: Shipping Test 
    Given Start chrome browser and open the shipping application
    When Test the text in title tag and the "<link>" for ShipmentID
    Then Validate the Customer name "<name>" is displayed
 
    Examples:  
      | link    | name | 
      | 6543217 | Maya | 
