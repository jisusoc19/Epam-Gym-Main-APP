package com.task3.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@NoArgsConstructor
@AllArgsConstructor  
@Entity
@Data
@Slf4j
@Table(name = "training_type")
public class Training_Type implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="training_Type_Name")
	private String trainingTypeName;

	@JsonIgnore
	@OneToMany(mappedBy = "trainertype")
	Set<Trainer> trainerone;
	@JsonIgnore
	@OneToMany(mappedBy = "training_type_id")
	Set<Training> traininglist;
	
	private static final long serialVersionUID = 1L;


}
