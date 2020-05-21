package com.EmployeeApi.Base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase 
{
 public  RequestSpecification HttpRequest;
 public  Response response;
 public String empID="51838";
 public static Logger logger;
 
 @BeforeClass
 void Setup()
 {
	 logger=Logger.getLogger(TestBase.class);
	 PropertyConfigurator.configure("log4j.properties");
	 logger.setLevel(Level.DEBUG);
	 logger.info("bjbqufbfqbbq");
 }
 
}
