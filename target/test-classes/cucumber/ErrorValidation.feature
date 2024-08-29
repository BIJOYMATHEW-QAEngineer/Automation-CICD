
@tag
Feature: Error validation test
  I want to use this template for my feature file

  
  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on the Ecommerse Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed 


    Examples: 
      |name            |password	|
      |Bijoy@test123.in|Test@1	  |
      