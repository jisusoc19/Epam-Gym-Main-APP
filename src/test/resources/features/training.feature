
Feature: TrainingService implement Save Entity a send it to other Microservice

  Scenario: Save new Training succesfully
    Given new Training with valid information
    When user try to save this Training with valid information
    Then Training save succesfully and recive a confirmation message

  Scenario: save new Training with null
    Given Save Training with bad Credentials
    When user try to save Training with unvalid information
   Then training dont save a throw a error

    ##Given es lo que quiero probar, entonces ofresco un contexto
  ##when lo que hace es lo que quiero hacer y lo que me llega (Las credenciales que tengo)
  ##then es lo que espero que suceda