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

import static org.junit.jupiter.api.Assertions.*;
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


    private Exception expectedException;

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
    public void save_training_with_bad_credentials() throws ParseException {
        training = new Training();
        training.setTrainingName(null);
        training.setTrainer_id(new Trainer());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date trainingDate = sdf.parse("2024-10-28T21:00:30.325Z");
        training.setTraining_date(trainingDate);
    }

    @When("user try to save Training with unvalid information Trainingname null")
    public void user_tries_to_save_training_with_invalid_information() {
        expectedException = assertThrows(IllegalArgumentException.class, () -> {
            trainingService.save(training);
        });
    }



    @Then("training doesn't save and throws an error")
    public void training_doesnt_save_and_throws_an_error() {
        assertNotNull(expectedException);
        assertEquals("Training name cannot be null", expectedException.getMessage());
    }

    @Given("Save Null Training")
    public  void save_Null_Training(){
        Training traininnull = null;
    }
    @When("Training Save With Null")
    public void Training_Save_With_Null(){
        trainingService.save(null);
    }
    @Then("Training throw a NullPointerExeption")
    public void Training_throw_a_NullPointerExeption(){
        assertNotNull(expectedException);
        assertTrue(expectedException instanceof NullPointerException);
    }

    @When("Training Save With Null")
    public void training_Save_With_Null() {
        expectedException = assertThrows(NullPointerException.class, () -> {
            trainingService.save(training);
        });
    }

    @Then("Training throws a NullPointerException")
    public void training_throws_a_NullPointerException() {
        assertNotNull(expectedException);
        assertTrue(expectedException instanceof NullPointerException);
    }

    @Given("Update Existing Training by Username")
    public void update_existing_training_by_username() {
        TrainingDtoMicroServiceTaskMicro training =new TrainingDtoMicroServiceTaskMicro();
    }

    @When("Training tries to find an existing Training to update Status")
    public void training_tries_to_find_an_existing_training_to_update_status() {
        // Suponemos que la acción de encontrar y luego actualizar es correcta, gestionada en este paso
        TrainingDtoMicroServiceTaskMicro training = new TrainingDtoMicroServiceTaskMicro();
        when(trainingService.UpdateActionType("PEPITO","Add")).thenReturn(training);
    }

    @Then("Training updates successfully")
    public void training_updates_successfully() {
        TrainingDtoMicroServiceTaskMicro updatedTraining = trainingService.UpdateActionType("PEPITO","ADD");
        assertNotNull(updatedTraining);
        assertEquals("Active", updatedTraining.getActionType());
    }


    @Given("Update Existing Training by a null id")
    public void update_existing_training_by_a_null_id() {
        when(trainingService.findbyid(null)).thenThrow(new NullPointerException("Training ID cannot be null"));
    }

    @When("Training tries to find a wrong Training to update Status")
    public void training_tries_to_find_a_wrong_training_to_update_status() {

        expectedException = assertThrows(NullPointerException.class, () -> {
            trainingService.findbyid(null);
        });
    }

    @Then("Training throws a NullPointerException")
    public void training_throws_a_null_pointer_exception() {
        assertNotNull(expectedException);
        assertEquals("Training ID cannot be null", expectedException.getMessage());
    }



}