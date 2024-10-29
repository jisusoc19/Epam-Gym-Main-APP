package com.task3.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task3.Entity.Training_Type;
import com.task3.service.trainingType.training_typeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
public class trainingtypeController {

	private final training_typeService trainingtypeservice;

	public trainingtypeController(training_typeService trainingtypeservice) {
		this.trainingtypeservice = trainingtypeservice;
	}

	@GetMapping("/training_type")
	public List<Training_Type> findAll(){
		log.info("iniciando el controlador trainingtypecontroller");
		return trainingtypeservice.findALL();
	}
}
