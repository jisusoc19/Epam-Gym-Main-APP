package com.task3.Repository;

import java.util.Optional;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task3.Entity.Trainer;

import jakarta.annotation.PostConstruct;
@Repository
public interface iTrainerdao extends JpaRepository<Trainer,Long> {
	
	//Trainer findByUsername(String username);
	@Query("SELECT t FROM Trainer t WHERE t.userid.username = :username")
	Optional<Trainer> findByUsername(@Param("username") String username);

	@Query("SELECT t FROM Trainer t WHERE t.userid.username = :username AND t.userid.password = :password")
	Optional<Trainer> loggin(String username, String password );
	

}