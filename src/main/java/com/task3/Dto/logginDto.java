package com.task3.Dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class logginDto{
	
	private String username;
	private String password;

}
