package com.task3.service.training;



import com.task3.Dto.TrainingDtoMicroServiceTaskMicro;
import org.springframework.stereotype.Service;

import com.task3.Entity.Training;

@Service
public interface iTrainingService{
	public Training findbyid(Long id);
	public TrainingDtoMicroServiceTaskMicro save(Training training);
	public TrainingDtoMicroServiceTaskMicro UpdateActionType(String username, String actionType);
}
