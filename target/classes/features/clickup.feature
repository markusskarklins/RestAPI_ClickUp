Feature: ClickUp API Test

  Scenario: Verify ClickUp API Functionality
    Given I have a ClickUp Space with ID "90152203694"
    When I create a new Folder named "TestFolder"
    Then I verify that the Folder named "TestFolder" is created successfully
    When I create a new List named "TestList" in the Folder "TestFolder"
    Then I verify that the List named "TestList" is created successfully
    When I create a new Task in the List "TestList"
    Then I verify that the Task is created successfully
    When I remove the Task from the List "TestList"
    Then I verify that the Task is removed successfully