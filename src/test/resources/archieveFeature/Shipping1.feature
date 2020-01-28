Feature: Test DATAX Shipping Company  
 
    Scenario: Test Cargo Shipping cost calculation 
    Given Load the application from the given URL 
    When  Test the cost calculation by giving inputs "air" and "100"  
    Then  Validates results of the test 
     
    Scenario: Test Cargo Shipping cost calculation 
    Given Load the application from the given URL 
    When  Test the cost calculation by giving inputs "ship" and "100"  
    Then  Validates results of the test 
     
    Scenario: Test Cargo Shipping cost calculation 
    Given Load the application from the given URL 
    When  Test the cost calculation by giving inputs "road" and "100"  
    Then  Validates results of the test