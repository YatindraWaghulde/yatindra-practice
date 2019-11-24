package com.yatindra.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yatindra.demo.AllImplemetationApplicationTests;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AllImplemetationApplicationTests.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {
	private MockMvc mockMVC;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void setup() {
		this.mockMVC = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void getUserDetails_Test() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String uri = "/api/userdetails";
		MvcResult mvcResult = mockMVC.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(null))).andExpect(status().is4xxClientError()).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
		String content = mvcResult.getResponse().getContentAsString();

	}
}
