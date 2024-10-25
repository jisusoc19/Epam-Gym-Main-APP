package com.task3.service.training;

import java.util.Map;

import com.task3.Dto.TrainingDtoMicroServiceTaskMicro;
import com.task3.Entity.Trainee;
import com.task3.Entity.Trainer;
import com.task3.ITaskMicro;
import com.task3.Repository.iTrainerdao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task3.Entity.Training;
import com.task3.Repository.iTrainingdao;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class iTrainingServiceImpl implements iTrainingService {

	@Autowired
	private ITaskMicro taskMicro;
	@Autowired
	private iTrainerdao trainerdao;
	@Autowired
	private iTrainingdao itrainindaoJPA;
	
	@Transactional(readOnly = true)
	@Override
	public Training findbyid(Long id) {
		// TODO Auto-generated method stub
		if(itrainindaoJPA.existsById(id)) {
			log.info("Training con el id " + id + " Seleccionado");
			
			return itrainindaoJPA.findById(id).orElseGet(null);
		}
		else {
			log.error("Training con el id " + id + " no encontrado");
			return null;
		}
		
	}
	@Transactional(readOnly = false)
	@Override
	public Training save(Training training) {
		if(itrainindaoJPA.findByUsername(training.getTrainer_id().getUserid().getUsername()).isEmpty()) {
			itrainindaoJPA.save(training);
			training.getTrainer_id();
			TrainingDtoMicroServiceTaskMicro trainingDtoMicro = new TrainingDtoMicroServiceTaskMicro();
			trainingDtoMicro.setFirstName(training.getTrainer_id().getUserid().getFirstName());
			trainingDtoMicro.setLastName(training.getTrainer_id().getUserid().getLastName());
			trainingDtoMicro.setUsername(training.getTrainer_id().getUserid().getUsername());
			trainingDtoMicro.setActive(training.getTrainer_id().getUserid().getIsActive());
			trainingDtoMicro.setTrainingDate(training.getTraining_date());
			trainingDtoMicro.setTrainingDuration(training.getTraining_duration());
			trainingDtoMicro.setActionType("ADD");
			log.info("Training guardado con exito");
			try {
				taskMicro.addTrainer(trainingDtoMicro);
				log.info("Llamada a microservicio secundario exitosa");
			} catch (Exception e) {
				log.error("Error al llamar al microservicio secundario", e);}
		}else {
			log.error("Error al guardar el Training con el id :" + training.getId());
			return null;
		}
		return training;
	}
	@Override
	public void Delete(String username, String actionType) {
		Training training = itrainindaoJPA.findByUsername(username).orElse(null);
		if (training==null) {
			log.error("No se encontró el TRAINER con el username " + username + ", no se puede eliminar" + username);
			throw new NullPointerException("No hay Trainer para Borrar");

		}
		if(!"DELETE".equals(actionType)){
			log.info("Trainer con el username " + username + " no se pudo eliminar Por action incorrecta");
		}
		taskMicro.deleteTrainer(training);
		training.getTrainee_id().getUserid().setIsActive(false);
		log.info("Trainer borrado correctamente");
	}
	@PostConstruct
	public void init() {
		log.info("Iniciando el iTrainingServiceimpl");
	}

}
