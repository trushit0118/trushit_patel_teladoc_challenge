Feature: Test Automation Scenarios

@regression
Scenario Outline: Add a user and validate the user has been added to the table
	Given Application is opened and on Home Page
	When click on add user and provide details with user "<userName>"
	Then The "<userName>" should be displayed in the home page
	Examples:
		|userName|
		|novak123|

@regression		
Scenario Outline: Delete user User Name novak and validate user has been delete
	Given The user "<userName>" is in Home Page
	When click on delete button of "<userName>"
	Then The user "<userName>" should be deleted
	Examples:
		|userName|
		|novak123|