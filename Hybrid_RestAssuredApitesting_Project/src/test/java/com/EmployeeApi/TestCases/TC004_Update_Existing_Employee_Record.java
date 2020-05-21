package com.EmployeeApi.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.EmployeeApi.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Update_Existing_Employee_Record extends Testbase1
{
	RequestSpecification HttpRequest;
	 Response response;
	 public static Logger logger;

	@BeforeClass
	public void UpdateEmployeeDetails() throws InterruptedException
	{
		logger=Logger.getLogger(TestBase.class);
		 PropertyConfigurator.configure("log4j.properties");
		 logger.setLevel(Level.DEBUG);
		logger.info("*************Started TC_004_Update_Existing_Employee_Record**************");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		HttpRequest=RestAssured.given();
		JSONObject requestParams =new JSONObject();
		requestParams.put("name","Hannah"+RandomStringUtils.randomAlphabetic(3));
		requestParams.put("salary", RandomStringUtils.randomNumeric(5));
		requestParams.put("age", RandomStringUtils.randomNumeric(2));
		HttpRequest.header("Content-Type","application/json");
		HttpRequest.body(requestParams.toJSONString());
		response=HttpRequest.request(Method.PUT,"/update/1");
		logger.info("response==>"+response.asString());
		Thread.sleep(2000);
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
		logger.info("*******************TC_004_Update_Existing_Employee_Record FINISHED*****************");
	}
}
