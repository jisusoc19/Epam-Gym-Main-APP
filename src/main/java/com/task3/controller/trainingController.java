package com.task3.controller;

import MQConfig.MQServiceMessage;
import com.task3.Dto.TrainingDtoMicroServiceTaskMicro;
import com.task3.Entity.Training_Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.task3.Entity.Training;
import com.task3.service.training.iTrainingService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
public class trainingController {

	public  trainingController(MQServiceMessage message, iTrainingService itrainingservice) {
		this.message = message;
        this.itrainingservice = itrainingservice;
    }

	private  MQServiceMessage message;

	private final iTrainingService itrainingservice;
	
	@GetMapping("/training/{id}")
	public Training findbyid(@PathVariable Long id) {
		log.info("iniciando trainingController TRAINING");
		return itrainingservice.findbyid(id);
	}
	@PostMapping("/training")
	public TrainingDtoMicroServiceTaskMicro save(@RequestBody Training training){
		message.sendMessage("Training creado");
		return itrainingservice.save(training);
	}
	@PutMapping("/training/{username}/{action}")
	public void delete(@PathVariable String username, @PathVariable  String action){
		message.sendMessageDelete("Trainin borrado");
		itrainingservice.UpdateActionType(username, action);
	}



}
