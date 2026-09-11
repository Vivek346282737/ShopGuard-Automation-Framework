@Regression @ECommerceCore
Feature: End-to-End E-Commerce Cart and Checkout Clearance

  Scenario Outline: Verify multi-item cart calculation and checkout clearance
    Given Customer is logged into the ShopGuard platform
    When Customer adds item "<item>" with quantity <qty> to the cart
    And Proceeds to checkout with payment method "<paymentMethod>"
    Then The order confirmation status must be "CONFIRMED"
    And The inventory ledger must decrement stock for "<item>" by <qty>

    Examples:
      | item         | qty | paymentMethod |
      | Laptop-Pro   | 1   | CREDIT_CARD   |
      | Wire-Headset | 2   | UPI_INSTANT   |