
@tag
Feature: Purchase the  order from Ecommerse website 
  I want to use this template for my feature file

Background:
Given I landed on the Ecommerse Page
  

  @Regression
  Scenario Outline: Positive test of submitting order 
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart 
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message displayed on the Confirmationpage

    Examples: 
      |name            |password	|productName  |
      |Bijoy@test123.in|Test@123	|ZARA COAT 3  |
      
                