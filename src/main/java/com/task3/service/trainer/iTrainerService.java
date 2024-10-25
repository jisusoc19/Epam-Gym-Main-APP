package com.task3.service.trainer;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.task3.Entity.Trainee;
import com.task3.Entity.Trainer;
import com.task3.Entity.User;

@Service
public interface iTrainerService {
	public Trainer save(Trainer trainer);
	public Trainer findById(Long id);
	public Trainer update(Trainer trainer , Long id);
	public List<Trainer> findAll();
	public Trainer findbyusername(String username);
	public Trainer activeTranierTrainee(String username, Boolean status);
	public Boolean loggin(String username, String Password);
}
