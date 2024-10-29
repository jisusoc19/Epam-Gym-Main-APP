package com.task3.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.extern.slf4j.Slf4j;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Data
@Slf4j
@Table(name = "training")
public class Training  implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="trainee_id")
	private Trainee trainee_id;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="trainer_id")
	private Trainer trainer_id;

	@Column(nullable = false,name = "training_name")
	private String trainingName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "training_type_id")
	private Training_Type training_type_id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date training_date;
	
	@Column(name = "duration" , nullable = false)
	private Long training_duration;


	
	
	@PrePersist
	public void prePersist() {
		training_date = new Date();
	}
	private static final long serialVersionUID = 1L;
	





}
