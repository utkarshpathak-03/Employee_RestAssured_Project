package com.EmployeeApi.TestCases;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Testbase1 
{
 public  RequestSpecification HttpRequest;
 public  Response response;
 public String empID="24";
 public  Logger logger;
 
 @BeforeClass
 void Setup()
 {
	 logger=Logger.getLogger(Testbase1.class);
	 PropertyConfigurator.configure("log4j.properties");
	 logger.setLevel(Level.DEBUG);
 }
 
}
