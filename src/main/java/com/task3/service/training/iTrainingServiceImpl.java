package com.task3.service.training;

import com.task3.Dto.TrainingDtoMicroServiceTaskMicro;
import com.task3.ITaskMicro;
import com.task3.service.trainer.iTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task3.Entity.Training;
import com.task3.Repository.iTrainingdao;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
public class iTrainingServiceImpl implements iTrainingService {


	private final ITaskMicro taskMicro;

	private final iTrainerService trainerdao;

	private final iTrainingdao itrainindaoJPA;

	public iTrainingServiceImpl(ITaskMicro taskMicro, iTrainerService trainerdao, iTrainingdao itrainindaoJPA) {
		this.taskMicro = taskMicro;
		this.trainerdao = trainerdao;
		this.itrainindaoJPA = itrainindaoJPA;
	}

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

	@Override
	public TrainingDtoMicroServiceTaskMicro save(Training training) {
        Training traininSfe;
		TrainingDtoMicroServiceTaskMicro trainingDtoMicro = new TrainingDtoMicroServiceTaskMicro();
        if (training != null) {
            traininSfe = itrainindaoJPA.save(training);
            training.getTrainer_id();

            trainingDtoMicro.setFirstName(trainerdao.findById(training.getTrainer_id().getId()).getUserid().getFirstName());
            trainingDtoMicro.setLastName(trainerdao.findById(training.getTrainer_id().getId()).getUserid().getLastName());
            trainingDtoMicro.setUsername(trainerdao.findById(training.getTrainer_id().getId()).getUserid().getUsername());
            trainingDtoMicro.setActive(trainerdao.findById(training.getTrainer_id().getId()).getUserid().getIsActive());
            trainingDtoMicro.setTrainingDate(training.getTraining_date());
            trainingDtoMicro.setTrainingDuration(training.getTraining_duration());
            trainingDtoMicro.setActionType("ADD");
            log.info("Training guardado con exito");
            try {
                taskMicro.addTrainer(trainingDtoMicro);
                log.info("Llamada a microservicio secundario exitosa");
            } catch (Exception e) {
                log.error("Error al llamar al microservicio secundario", e);
            }
        } else {
            log.error("Error al guardar el Training con el id :" + training.getId());
            return null;
        }
        return trainingDtoMicro;
    }
	@Override
	public TrainingDtoMicroServiceTaskMicro UpdateActionType(String username, String actionType) {
		Training training = itrainindaoJPA.findByUsername(username).orElse(null);
		Training trainingSafe;

		if (training==null) {
			log.error("No se encontró el TRAINER con el username " + username + ", no se puede eliminar" + username);
			throw new NullPointerException("No hay Trainer para Borrar");

		}
		TrainingDtoMicroServiceTaskMicro trainingDtoMicro = new TrainingDtoMicroServiceTaskMicro();
		trainingDtoMicro.setFirstName(trainerdao.findById(training.getTrainer_id().getId()).getUserid().getFirstName());
		trainingDtoMicro.setLastName(trainerdao.findById(training.getTrainer_id().getId()).getUserid().getLastName());
		trainingDtoMicro.setUsername(trainerdao.findById(training.getTrainer_id().getId()).getUserid().getUsername());
		trainingDtoMicro.setActive(trainerdao.findById(training.getTrainer_id().getId()).getUserid().getIsActive());
		trainingDtoMicro.setTrainingDate(training.getTraining_date());
		trainingDtoMicro.setTrainingDuration(training.getTraining_duration());
		if (actionType.equals("DELETE")){
			trainingDtoMicro.setActionType("DELETE");
			training.getTrainer_id().getUserid().setIsActive(false);
		}
		if (actionType.equals("ADD")){
			trainingDtoMicro.setActionType("ADD");
			training.getTrainer_id().getUserid().setIsActive(true);
		}
		trainingSafe = itrainindaoJPA.save(training);

		if(!"DELETE".equals(trainingDtoMicro.getActionType())||!"ADD".equals(trainingDtoMicro.getActionType())){
			log.info("Trainer con el username " + username + " no se pudo eliminar Por action incorrecta");
		}
		taskMicro.deleteTrainer(trainingDtoMicro);
		training.getTrainee_id().getUserid().setIsActive(false);
		log.info("Trainer borrado correctamente");
		return trainingDtoMicro;
	}


}
