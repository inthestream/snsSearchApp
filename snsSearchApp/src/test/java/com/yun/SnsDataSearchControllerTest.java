package com.yun;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.yun.controller.SnsDataSearchController;

@RunWith(SpringRunner.class)
@WebMvcTest(SnsDataSearchController.class)
public class SnsDataSearchControllerTest {
	
	@Autowired
	MockMvc mock;
	
	@Test
	public void testHello() throws Exception {
		mock.perform(get("/hello")).andExpect(content().string("Hello World"));
	}

	@Test
	public void testModelParameter() throws Exception {
		mock.perform(get("/poland/poznan/egnyte")).andDo(print())
		.andExpect(model().attributeExists("output"));
	}
}
