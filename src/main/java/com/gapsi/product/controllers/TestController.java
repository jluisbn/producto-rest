package com.gapsi.product.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gapsi.product.model.TestModel;

@RestController
public class TestController 
{
	@RequestMapping(value = "/test/database", method = RequestMethod.GET)
	public String testDatabase() 
	{
		return TestModel.testDatabase();
	}
	
	@RequestMapping(value = "/test/service", method = RequestMethod.GET)
	public String testService() 
	{
		return "Service is up and running";
	}
}
