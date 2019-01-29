package com.wayne.partone;

import com.wayne.partone.partone.controller.HelloWorldController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class WebControllerTest {


    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }
    @Test
    public void getUser() throws Exception {
        String responseeString = mockMvc.perform(MockMvcRequestBuilders
                .get("/getUser"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("result: "+responseeString);
    }
    @Test
    public void savaUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/saveUser")
        .param("name", "")
        .param("age", "666")
        .param("pass","test")
        );
    }
}
