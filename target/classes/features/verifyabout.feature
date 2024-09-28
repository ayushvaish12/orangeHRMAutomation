Feature: Login to OrangeHRM and Verify About Section

  Scenario Outline: Successful login and verification of the About section
    Given I am on the OrangeHRM login page from given sheetname "<SheetName>"
    When I enter valid credentials for OrangeHRM
    And I click the login button
    Then I should be logged into the OrangeHRM dashboard from "<SheetName1>"
    When I navigate to the about section
    Then I should see the about section details
    
    Examples:
    |SheetName|SheetName1|
		|Login|Profile|
		
  Scenario Outline: Successful login and verification of the Support section
    Given I am on the OrangeHRM login page from given sheetname "<SheetName>"
    When I enter valid credentials for OrangeHRM
    And I click the login button
    Then I should be logged into the OrangeHRM dashboard from "<SheetName1>"
    When I navigate to the support section
    Then I should see the support section details
    
  	Examples:
    |SheetName|SheetName1|
		|Login|Profile|