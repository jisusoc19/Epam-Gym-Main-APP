package com.task3;

import com.task3.Dto.TrainingDtoMicroServiceTaskMicro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("TaskMicro")
public interface ITaskMicro {
    @PostMapping("/api/trainer")
    void addTrainer(TrainingDtoMicroServiceTaskMicro trainingDtoMicro);

    @PutMapping("/api/trainer")
    void deleteTrainer(TrainingDtoMicroServiceTaskMicro trainingDtoMicro);

}
