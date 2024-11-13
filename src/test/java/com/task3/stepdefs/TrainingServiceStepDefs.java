package com.task3.stepdefs;


import com.task3.Dto.TrainingDtoMicroServiceTaskMicro;
import com.task3.Entity.*;
import com.task3.Repository.iTrainingdao;
import com.task3.service.trainee.iTraineeService;
import com.task3.service.trainer.iTrainerService;
import com.task3.service.training.iTrainingService;
import com.task3.service.training.iTrainingServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class TrainingServiceStepDefs {


    @InjectMocks
    private iTrainingServiceImpl trainingService;
    private String exceptionMessage;
    private Training training;
    private TrainingDtoMicroServiceTaskMicro savedTraining;

    @Given("new Training with valid information")
    public void new_training_with_valid_information() throws ParseException {
        User userId = new User();
        userId.setId(1L);

        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setUserid(userId);

        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setUserid(userId);

        Training_Type trainingType = new Training_Type();
        trainingType.setId(1L);

        training = new Training();
        training.setTrainee_id(trainee);
        training.setTrainer_id(trainer);
        training.setTrainingName("string");
        training.setTraining_type_id(trainingType);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date trainingDate = sdf.parse("2024-10-28T21:00:30.325Z");
        training.setTraining_date(trainingDate);
    }

    @When("user try to save this Training with valid information")
    public void user_try_to_save_this_training() {
        savedTraining = trainingService.save(training);
    }

    @Then("Training save successfully and receive a confirmation message")
    public void el_training_se_guarda_correctamente_y_se_recibe_un_mensaje_de_confirmación() {
        assertNotNull(savedTraining);
    }

    @Given("Save Training with bad credentials")
    public void save_training_with_bad_credentials() {
        training = new Training();
        training.setTrainingName(null);
    }

    @When("user tries to save Training with invalid information")
    public void user_tries_to_save_training_with_invalid_information() {
        try {
            savedTraining = trainingService.save(training);
            exceptionMessage = null;
        } catch (Exception ex) {
            exceptionMessage = ex.getMessage();
        }
    }

    @Then("training doesn't save and throws an error")
    public void training_doesnt_save_and_throws_an_error() {
        assertNull(savedTraining);
        assertNotNull(exceptionMessage);
    }


}