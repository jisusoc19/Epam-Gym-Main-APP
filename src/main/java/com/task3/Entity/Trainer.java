package com.task3.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Slf4j
@Table(name = "trainer")
public class Trainer implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	private User userid;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "specialization")
	private Training_Type trainertype;

	@JsonIgnore
	@OneToMany(mappedBy = "trainer_id")
	private Set<Training> traininglist;

	
	
	private static final long serialVersionUID = 1L;

	public Trainer (User userid) {
		this.userid=userid;
		
	}



}
