package com.task3;

import com.task3.Dto.TrainingDtoMicroServiceTaskMicro;
import com.task3.Entity.Training;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TaskMicroimpl {

    private final ITaskMicro taskMicroClient;

    public TaskMicroimpl(ITaskMicro taskMicroClient) {
        this.taskMicroClient = taskMicroClient;
    }

    @CircuitBreaker(name = "taskMicroCircuitBreaker", fallbackMethod = "fallbackAddTrainer")
    public void addTrainer(TrainingDtoMicroServiceTaskMicro trainingDtoMicro) {
        taskMicroClient.addTrainer(trainingDtoMicro);
    }

    public void fallbackAddTrainer(TrainingDtoMicroServiceTaskMicro trainingDtoMicro, Throwable t) {
        log.error("Fallback for addTrainer due to: " + t.getMessage());
    }

    @CircuitBreaker(name = "taskMicroCircuitBreaker", fallbackMethod = "fallbackDeleteTrainer")
    public void deleteTrainer(Training training) {
        taskMicroClient.deleteTrainer(training);
    }

    public void fallbackDeleteTrainer(Training training, Throwable t) {
        log.error("Fallback for deleteTrainer due to: " + t.getMessage());
    }
}
