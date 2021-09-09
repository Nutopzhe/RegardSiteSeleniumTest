Feature: Testing the user basket on the regard website

  Scenario: Adding products to the shopping cart and checking the operation of the shopping cart
    Given user is on main page
    When in the left side menu, the user selected 'Материнские платы' and the subcategory 'Intel Socket 1200'
    Then user selected 5 position from the list and put it in the basket

    When in the left side menu, the user selected 'Корпуса' and the subcategory 'AEROCOOL'
    Then user selected 4 position from the list and put it in the basket
    And user selected 10 products in the list and clicked on the link-name

    When user clicked on the red Add to cart button
    And clicked on the blue button Go to cart
    Then make sure that the basket is not empty and it contains the same products

  Scenario: Adding products to the shopping cart from the pc configuration
    Given user is on main page
    When in the upper block, select the pc configurator
    And click on the button 'Корпус'
    Then user selected 50 products and clicked on it

    When click on the button 'Процессор'
    Then user selected 100 products and clicked on it

    When click on the button 'Видеокарта'
    Then user selected 20 products and clicked on it

    When user clicked on the red checkout button
    Then make sure that the basket is not empty and it contains the same products

  Scenario: Adding products to the cart and deleting some with verification
    Given user is on main page
    When in the left side menu, the user selected 'Видеокарты' and the subcategory 'NVIDIA GeForce'
    Then user selected 1 position from the list and put it in the basket
    And user selected 2 position from the list and put it in the basket
    And user selected 3 position from the list and put it in the basket
    And user selected 4 position from the list and put it in the basket

    When user clicked on the basket from the top right
    Then make sure that the basket is not empty and it contains the same products

    When delete item with index 4
    Then make sure that the basket is not empty and it contains the same products

    When delete item with index 1
    Then make sure that the basket is not empty and it contains the same products