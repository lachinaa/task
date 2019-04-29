Feature: Login functionality


  Scenario: user login with invalid credentials
    Given user goes to the main page "url"
    When user enters invalid credentials,user should be able to see error message

# will test using data-driven testing

  Scenario Outline: login with different invalid username and password
    Given user goes to the main page "url"
    When user logs in using "<username>" and "<password>"
    Then user should see error message

    Examples:
      | username         | password        |
      | fbawmeb7         | sherilyngohn    |
      | ucharlot8        | archibaldmelloy |
      | sweeklybh        | bartletcarlin   |
      | teacherva4@      | markwohlberg    |
      | crundall8euz.net | eloisamaccauley |

    # will test using data from excel sheet

  Scenario: - Verify login with invalid username and password
    Given user goes to the main page "url"
    And user should enter invalid "username" and "password", and should be able to see the error message
    