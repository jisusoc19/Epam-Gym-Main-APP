package com.task3.Entity;
import java.io.Serializable;



import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Data
@Slf4j
@Table(name = "Trainee")
public class Trainee implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_Birth", nullable = true)
	private Date dateBirth;

	@Column(name="addres", length = 30 , nullable = false)
	private String addres;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private User userid;

	@JsonIgnore
	@OneToMany(mappedBy = "trainee_id")
	Set<Training> traininglist;



    @PrePersist
	public void prePersist() {
		dateBirth = new Date();
	}
	
	


	public Trainee(long l, String addres, String mail, User userid, Object o, String jesus, String david, Object object, Object o1, boolean b) {
		this.addres = addres;
		this.userid = userid;
	}



	private static final long serialVersionUID = 1L;
	



	


}
