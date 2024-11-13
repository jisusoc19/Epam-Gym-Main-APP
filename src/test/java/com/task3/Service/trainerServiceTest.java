package com.task3.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.task3.Entity.Trainer;
import com.task3.Exception.RestHandlerException.ResouceNotFoundException;
import com.task3.Repository.iTrainerdao;
import com.task3.service.trainer.iTrainerServiceImpl;


import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.when;

@SpringBootTest
public class trainerServiceTest {
	@Mock
	private iTrainerdao trainerjpa;
	
	

	@InjectMocks
	private iTrainerServiceImpl trainerservice;
	
	@Test
	public void findallTest() {
		Trainer trainer1 = new Trainer(1L,null,null,null);;
		Trainer trainer2 = new Trainer(2L,null,null,null);
		given(trainerjpa.findAll()).willReturn(Arrays.asList(trainer1, trainer2));
		List<Trainer> trainer = trainerservice.findAll();
		assertThat(trainer).hasSize(2);
		assertThat(trainer).contains(trainer1);
	}
	@Test
	public void Testfindbyallnull() {
		given(trainerjpa.findAll()).willReturn(null);
		
		assertThrows(ResouceNotFoundException.class, () -> {
	            List<Trainer> train = trainerservice.findAll();
	        });
	}
	@Test
	public void findbyid() {
		Trainer trainer1 = new Trainer(1L,null,null,null);
		Long id =1L;
		when(trainerjpa.existsById(id)).thenReturn(true);
        when(trainerjpa.findById(id)).thenReturn(Optional.of(trainer1));
		given(trainerjpa.findById(trainer1.getId())).willReturn(Optional.of(trainer1));
		Trainer training1encontrado = trainerservice.findById(id);
		
		assertThat(training1encontrado).isEqualTo(trainer1);
	
		
        assertEquals(trainer1, training1encontrado);
	}
	@Test
	public void findbyidnull() {
		Long id = 1L;
        when(trainerjpa.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResouceNotFoundException.class, () -> {
            trainerservice.findById(id);
        });

        String expectedMessage = "No se encontro el id " + id;
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));
	}
	@Test
	public void saveTest() {
		Trainer trainer1 = new Trainer(1L,null,null,null);
		
		Trainer trainesave = trainerservice.save(trainer1);
    	
    	assertThat(trainesave).isEqualTo(trainer1);
	}
	@Test
	public void saveTestnull() {
		Trainer trainer1 = null;
		
		when(trainerjpa.save(trainer1)).thenReturn(null);
		
        assertThrows(ResouceNotFoundException.class, () -> {
        	trainerservice.save(trainer1);
        });
	}
	@Test
	public void updateTestnull() {
		Trainer trainer1 = new Trainer(1L,null,null,null);
		Trainer trainesave = trainerservice.save(trainer1);
		Exception exception = assertThrows(ResouceNotFoundException.class, () -> {
			Trainer trainern =trainerservice.update(trainer1, trainer1.getId());
	        });
		assertThat(trainesave).isEqualTo(trainer1);
	    String expectedMessage = "Trainer with ID " + trainer1.getId() + " no update in the database";
	    String actualMessage = exception.getMessage();

	    assertThat(actualMessage.contains(expectedMessage));
	}
    @Test
    public void TrainerUpdate() {
        Long id = 1L;
        Trainer existingTrainer = new Trainer(1L,null,null,null);// Configura el trainer existente
        Trainer updatedTrainer = new Trainer(1L,null,null,null);
        Trainer trainergaurdado = trainerservice.save(existingTrainer);

        
        when(trainerjpa.findById(id)).thenReturn(Optional.of(existingTrainer));

        Trainer result = trainerservice.update(updatedTrainer, id);

        assertNull(result);
    }
    


    @Test
    public void findusernameResourceException() {
    	String username = "jesus";
    	   assertThrows(ResouceNotFoundException.class, () -> {
           	trainerservice.findbyusername(username);
           });
    }
    
 

    @Test
    public void isResourceExceptionactive() {
    	Boolean newStatus = true;
    	   assertThrows(ResouceNotFoundException.class, () -> {
    		   trainerservice.activeTranierTrainee(null, null);
              });
    	
    }
	
	

}
