Feature: To Test the Shipment details
 
  @UserTest
  Scenario Outline: User Test 
    Given Start chrome browser and open the application
    When Enter "<UserName>" and click search
    Then Validate user name "<UserName>","<ShipmentId>","<Phone>","<Email>" is displayed
 
    Examples:  
      | UserName    | ShipmentId | Phone 	  | Email 			   		 |
      | Shamili		| SHIP1236   | 9224158877 | shamili93@gamil.com|
