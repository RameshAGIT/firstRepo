Feature: Test Google search

	@GoogleTest
	Scenario: Test Google search
		Given Open browser and launch Google
		When Enter search key word
		Then User should able to search