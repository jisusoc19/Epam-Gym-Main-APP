package com.task3.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class TrainingDtoP {
    private String trainingName;
    private String trainingDate; // En formato adecuado para conversión a Date
    private Long trainingDuration;
    private Long traineeId; // ID del Trainee
    private Long trainerId; // ID del Trainer
    private Long trainingTypeId; // ID del TrainingType

        // Getters y setter
}
