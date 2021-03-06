package com.EmployeeApi.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.EmployeeApi.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Data extends Testbase1
{
	RequestSpecification HttpRequest;
	 Response response;
	 public static Logger logger;

	@BeforeClass
	public void getAllEmployeesDetails() throws InterruptedException
	{
		logger=Logger.getLogger(TestBase.class);
		 PropertyConfigurator.configure("log4j.properties");
		 logger.setLevel(Level.DEBUG);
		logger.info("*************Started TC002_Get_Single_Employee_Data**************");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/employee";
		HttpRequest=RestAssured.given();
		response=HttpRequest.request(Method.GET,"/1");
		Thread.sleep(5000);
	}
	
	@Test
	public void checkResponseBody()
	{
		logger.info("*************Checking response Body***************");
		String res = response.asString();
		logger.info("Response body ===>"+res);
		AssertJUnit.assertTrue(res.contains(empID));
	}
	@Test
	public void checkStatuscode()
	{
		logger.info("**************Checking Status Code***************");
		int StatusCode=response.getStatusCode();
		logger.info("Status Code ===>"+StatusCode);
		AssertJUnit.assertEquals(StatusCode, 200);
	}
	@Test
	public void checkResponseTime()
	{
		logger.info("***************Checking Response Time*****************");
		long ResponseTime=response.getTime();
		logger.warn("Response time ===>"+ResponseTime);
		
		if(ResponseTime>2000)
		{
			logger.warn("Response Time is Greater than 2000");
		}
		AssertJUnit.assertTrue(ResponseTime<10000);
	}
	@Test
	public void checkStatusLine()
	{
		logger.info("***************Checking Status Line*********************");
		String StatusLine=response.getStatusLine();
		logger.info("Status Line ===>"+StatusLine);
		AssertJUnit.assertEquals("HTTP/1.1 200 OK", StatusLine);
	}
	
	@Test
	public void checkContentType()
	{
		logger.info("***************Checking Content Type*********************");
		String ContentType=response.contentType();
		logger.info("Content Type===>"+ContentType);
		AssertJUnit.assertEquals("application/json;charset=utf-8", ContentType);
		
	}
	
	@Test
	public void checkServerType()
	{
		logger.info("***************Checking Server Type*********************");
		String ServerType=response.header("Server");
		logger.info("Server Type ===>"+ServerType);
		AssertJUnit.assertEquals("nginx/1.16.0", ServerType);
	}
	
	

	@AfterTest
	public void TearDown()
	{
		logger.info("*******************TC002_Get_Single_Employee_Data FINISHED*****************");
	}
}
