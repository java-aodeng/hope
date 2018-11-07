package com.hope;

import com.hope.controller.HelloController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Matches;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
public class Springboot13StarterTestApplicationTests {

	private MockMvc mockMvc;

	//初始化资源
	@Before
	public void setMockMvc() throws Exception{
		mockMvc= MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}

	@Test
	public void test() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/hello?name=低调小熊猫")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
	}
	@Test
	public void test2() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/hello?name=低调小熊猫")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("低调小熊猫")));

	}
	@Test
	public void contextLoads() {
		System.out.println("低调小熊猫");
	}

}
