package com.sg.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiUserControllerTest {

   @Autowired
   private WebApplicationContext context;
   private MockMvc mvc;

   @Before
   public void setUp() throws Exception {
      mvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc
   }

   @Test
   public void testGetUser() throws Exception {
      ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/api/user/get") // //调用接口
            .param("tid", "4d34d5aed8f411e9bddcda211f4fee1e")
            .accept(MediaType.APPLICATION_JSON))  //接收的类型
            .andExpect(MockMvcResultMatchers.status().isOk());   //判断接收到的状态是否是200
      resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
      resultActions.andDo(MockMvcResultHandlers.print());  //打印内容
   }

}
