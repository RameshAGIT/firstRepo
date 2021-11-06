Feature: Validating Place API

  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    #Given Add Place Payload
    Given Add Place Payload with "<Name>" "<Address>" "<Language>"
    #When User calls "maps/api/place/add/json" with POST request
    When User calls "AddPlaceAPI" with "POST" request
    Then API call got success with status code 200
    ## We can use same step definition for line 7 & 8. Just want to understand with default and JsonPath methods
    And "scope" in response body is "APP"			
    ## So created 2 different steps to understand both concepts. Else we can acheive the same using same steps without 'Verify'											
    And Verify "status" in response body is "OK"			
    And Verify placeId created matches to "<Name>" using "GetPlaceAPI"		
    
   #Scenario not allowed here so using Scenario Outline to delete 2 places which creted. If we put 2nd scenario under exaples it will delelte only the last place id which was created
   #Scenario: Verify Delete Place API functionality is working		
    	Given Delete place payload
     	When User calls "DeletePlaceAPI" with "DELETE" request
     	Then API call got success with status code 200
     	And Verify "status" in response body is "OK"				

    Examples:
    |Name				|Address			|Language|
    |JohnSmith	|123 South dr	| English|
    |JimJohn		|234 North dr	|French	|
    
    