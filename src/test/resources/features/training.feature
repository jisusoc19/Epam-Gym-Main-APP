
Feature: TrainingService implement Save Entity a send it to other Microservice

  Scenario: Save new Training succesfully
    Given new Training with valid information
    When user try to save this Training with valid information
    Then Training save succesfully and recive a confirmation message

  Scenario: save new Training bad Credentials
    Given Save Training with bad Credentials
    When user try to save Training with unvalid information Trainingname null
    Then training dont save a throw a error

  Scenario:Save new Training with Null Username
  Given Save Null Training
  When Training Save With Null
  Then Training throw a NullPointerExeption

  Scenario:Save new Training with Null
    Given Save Null Training
    When Training Save With Null User
    Then Training should return

  Scenario Update Existing Training Status
    Given Update Existing Training by Username
    When Training try to find a existing Training to update Status
    Then Training update succesfully

  Scenario Update Existing Training Status Failed
    Given Update Existing Training by a null Username
    When Training try to find an worng Training to update Status
    Then Training throw a NullPointerExeption
    ##Given es lo que quiero probar, entonces ofresco un contexto
  ##when lo que hace es lo que quiero hacer y lo que me llega (Las credenciales que tengo)
  ##then es lo que espero que suceda