package com.task3.IntegrationTest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import com.task3.Task3Application;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task3.Dto.logginDto;
import com.task3.Entity.Trainee;
import com.task3.Entity.Trainer;
import com.task3.Entity.User;
import com.task3.Repository.iUserRepository;
import com.task3.service.JWT.AuthServiceImpl;
import com.task3.service.JWT.IJwtService;
import com.task3.service.user.iUserServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
	@Autowired
	private MockMvc mockMvc;

	public AuthControllerTest(ObjectMapper objectmapper, MockMvc mockMvc, iUserRepository userservice, AuthServiceImpl authservice, IJwtService ijwtservice) {
		this.objectmapper = objectmapper;
		this.mockMvc = mockMvc;
		this.userservice = userservice;
		this.authservice = authservice;
		this.ijwtservice = ijwtservice;
	}

	@Autowired
	private ObjectMapper objectmapper;
	@Autowired
	iUserRepository userservice;

	@Autowired
	private AuthServiceImpl authservice;

	@Autowired
	private IJwtService ijwtservice;


	@Test
	public void TestStatus400Register() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

	}

	@Test
	public void ResgistroTest() throws Exception {
		User user = new User(1L,"asdasd", "asdad","asdasd", "adsfff", true,
				null, null);
		String ob = objectmapper.writeValueAsString(user);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
				.content(ob)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}

	@Test
	public void TestStatusloggin400() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/api/auth/loging")
				.with(httpBasic("username","password")))
		.andExpect(status().isBadRequest())
		.andReturn();

		String token = result.getResponse().getContentAsString();

		mockMvc.perform(get("/api/auth/loging")
				.header("Authorization", "Bearer " + token))
		.andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void TestStatusloggin() throws Exception {
		User user = new User(1L,"jesus", "doca","username", "password", true,
				null, null);
    	userservice.save(user);


    	ijwtservice.generarteJwt(user.getId());

        mockMvc.perform(get("/api/trainer")
                .header("Authorization","Bearer " + ijwtservice.generarteJwt(user.getId())))
            .andExpect(status().isOk());
	}

}
