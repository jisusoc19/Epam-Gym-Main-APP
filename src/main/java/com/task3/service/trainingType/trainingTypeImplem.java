package com.task3.service.trainingType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.task3.Entity.Training_Type;
import com.task3.Exception.RestHandlerException.ResouceNotFoundException;
import com.task3.Repository.iTraining_TypeRepo;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class trainingTypeImplem implements training_typeService {


	private final iTraining_TypeRepo trainer_typerepo;

	public trainingTypeImplem(iTraining_TypeRepo trainer_typerepo) {
		this.trainer_typerepo = trainer_typerepo;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Training_Type> findALL(){
		List<Training_Type> lista = (List<Training_Type>) trainer_typerepo.findAll();
		if(lista==null || lista.isEmpty()) {
			log.error("no se encontro ninguna lista de Training_type");
			throw new ResouceNotFoundException("Training_type");
		}
		log.info("lista de Training_type encontrada");
		return lista;
	}
	




}
