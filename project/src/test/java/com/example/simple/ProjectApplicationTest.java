package com.example.simple;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.project.URL;
import com.example.project.UrlController;
import com.example.project.UrlRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UrlController.class)
@WebMvcTest(UrlController.class)
public class ProjectApplicationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
	 
    @MockBean
    private UrlRepository url;
    
    @Test
	public void storeUrlTest() throws Exception {
	    URL record = URL.builder()
	            .url("facebook.com")
	            .build();
	    Mockito.when(url.save(record)).thenReturn(record);
	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/storeurl")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(record.getUrl()).replaceAll("\"", ""));

	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk());
	    }
    
   
    @Test
    public void getAllUrlsTest() throws Exception {
    	short k=23;
    	URL record=new URL(k,1,"google.com");
        List<URL> records = new ArrayList<URL>();
        records.add(record);
       Pageable paging=PageRequest.of(1,2);
       System.out.println(paging);
      Page<URL> page=new PageImpl<>(records);
      Mockito.when(url.findAll(paging)).thenReturn(page);
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/list?page=1&size=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
}




