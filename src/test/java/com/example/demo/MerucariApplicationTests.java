package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.service.OriginalService;
import com.example.demo.service.Service;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MerucariApplicationTests {

	
	@Autowired
	private OriginalService service;
	
	@Autowired
	private Service s;
	
	@Test
	public void contextLoads() {
		
		try {
			s.setCategory();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}


