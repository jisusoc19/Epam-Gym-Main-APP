package com.task3.Repository;

import com.task3.Entity.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task3.Entity.Training;
import com.task3.Entity.User;

import jakarta.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface iTrainingdao extends JpaRepository<Training, Long> {

    @Query("SELECT t FROM Training t WHERE t.trainer_id.userid.username = :username")
    Optional<Training> findByUsername(@Param("username") String username);

}
