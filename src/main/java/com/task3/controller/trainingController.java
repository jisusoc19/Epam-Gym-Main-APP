package com.task3.controller;

import com.task3.Entity.Training_Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.task3.Entity.Training;
import com.task3.service.training.iTrainingService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/apitraining")
public class trainingController {
	
	
	
	@Autowired
	private iTrainingService itrainingservice;
	
	@GetMapping("/training/{id}")
	public Training findbyid(@PathVariable Long id) {
		log.info("iniciando trainingController TRAINING");
		return itrainingservice.findbyid(id);
	}
	@PostMapping("/training")
	public Training save(@RequestBody Training training){
		return itrainingservice.save(training);
	}
	@PostConstruct
	public void init() {
		log.info("iniciando trainingController ");
	}


}
