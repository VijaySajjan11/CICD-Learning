
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce application

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged with username<name> and password <password>
    When I add the product <product> from cart
    And Checkout <product> and submit the order
    Then "THANKYOU FOR THE ORDER." Message is displayed on confirmation page

    Examples: 
      | name  											| password 	| product  		|
      | vijaykumarsajjan32@gmail.com| Sajjan@77 | ZARA COAT 3 |
      
