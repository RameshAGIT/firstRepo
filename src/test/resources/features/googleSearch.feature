Feature: To Test Google search

	@GoogleTest
	Scenario Outline: Google search Test
		Given Open browser and launch Google
		When Enter "<SearchText>" to search
		Then User should able to search
		
		Examples:
      | SearchText				|
      | oneindia english	|