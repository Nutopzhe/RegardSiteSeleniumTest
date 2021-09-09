Feature: Testing authorization on the regard website

  Scenario Outline: Authorization attempt with invalid username
    Given user in the main page
    When clicked on the personal account tab and went to the registration tab
    And in the login field enter '<username>' and in the password field enter '<password>'
    Then user clicked red button registration
    And check that the login is unavailable

    Examples:
      | username     | password  |
      | qwe@@mail.ru | 123456789 |
      | qwe@mail..ru | 123456789 |
      | qwemail.ru@  | 123456789 |
      | qwe@yandexru | 123456789 |
      | @ya.ru       | 123456789 |