package com.task3.service.user;


import com.task3.Entity.Trainee;
import com.task3.Entity.Trainer;
import com.task3.Exception.RestHandlerException.ResouceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task3.Entity.User;
import com.task3.Repository.iUserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Slf4j
@Transactional
public class iUserServiceImpl implements iUserService {


	private final iUserRepository userRepo;

	public iUserServiceImpl(iUserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public String generateUniqueUsername(String firstName, String lastName) {
		String gUsername = firstName.toLowerCase() + "." + lastName.toLowerCase();
		String username = gUsername;
		Integer nSerail = 1;
		while (userRepo.findByUsername(username).isPresent()) {
			username = gUsername + nSerail;
			nSerail++;
		}
		log.info("user con username unico generado");
		return username;
	}
	@Override
	public String generatePasword() {
	    int length = 10;
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(letras.length());
            sb.append(letras.charAt(randomIndex));
        }
        log.info("user generado con contraseña");
        return sb.toString();
    }

	@Override
	public User activeUser(String username, Boolean status) {

		User user = userRepo.findByUsername(username).orElse(null);
		if(userRepo.findByUsername(username).isPresent()) {
			user.setIsActive(status);
			userRepo.save(user);
			log.info("user con el username:" + username + " Status cambiado" );
		}else {
			log.error("user con el username:" + username + " no se pudo cambiar el Status" );
			throw new ResouceNotFoundException();
		}

		return user;

	}




	
	@Transactional(readOnly = true)
	@Override
	public User findbyusername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username).orElse(null);
	}

}
