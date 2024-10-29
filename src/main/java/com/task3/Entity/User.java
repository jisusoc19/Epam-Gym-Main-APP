package com.task3.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Entity
@Data
@Table(name = "usergym")
@Slf4j
@NoArgsConstructor
public class User implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,name="first_Name")
	private String firstName;

	@Column(nullable = false,name="last_Name")
	private String lastName;

	@Column(nullable = false,name="username")
	private String username;

	@Column(nullable = false,name="password")
	private String password;

	@Column(nullable = false,name="is_Active")
	private Boolean isActive = true;
	@Transient
	@OneToOne(mappedBy ="userid")
	Trainee trainee;
	@Transient
	@OneToOne(mappedBy ="userid")
	Trainer trainer;


	private static final long serialVersionUID = 1L;

}
