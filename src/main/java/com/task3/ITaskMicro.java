package com.task3;

import com.task3.Dto.TrainingDtoMicroServiceTaskMicro;
import com.task3.Entity.Trainer;
import com.task3.Entity.Training;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("TaskMicro")
public interface ITaskMicro {
    @PostMapping("/trainer")
    void addTrainer(TrainingDtoMicroServiceTaskMicro trainingDtoMicro);

    @DeleteMapping("/trainer")
    void deleteTrainer(Training training);

}
