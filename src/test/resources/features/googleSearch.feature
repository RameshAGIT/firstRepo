Feature: To Test Google search

	@GoogleTest
	Scenario: Google search Test
		Given Open browser and launch Google
		When Enter search key word
		Then User should able to search