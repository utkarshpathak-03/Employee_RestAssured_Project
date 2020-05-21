package com.EmployeeApi.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.EmployeeApi.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Get_All_Employees_Record 
{
	 public static RequestSpecification HttpRequest;
	 public static Response response;
	 public static Logger logger;

	@BeforeTest
	public void getAllEmployeesDetails() throws InterruptedException
	{
		logger=Logger.getLogger(TestBase.class);
		 PropertyConfigurator.configure("log4j.properties");
		 logger.setLevel(Level.DEBUG);
		logger.info("*************Started TC001_Get_All_Employees_Record**************");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		HttpRequest=RestAssured.given();
		response=HttpRequest.request(Method.GET,"/employees");
	}
	@Test
	public void checkResponseBody()
	{
		logger.info("*************Checking response Body***************");
		String res = response.asString();
		logger.info("Response body ===>"+res);
		AssertJUnit.assertTrue(res!=null);
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
	
	@Test
	public void checkContentEncoding()
	{
		logger.info("***************Checking Content Encoding*********************");
		String ContentEncoding=response.header("Content-Encoding");
		logger.info("Content Encoding===>"+ContentEncoding);
		AssertJUnit.assertEquals("gzip", ContentEncoding);
	} 
	@Test
	public void checkContentLength()
	{
		logger.info("***************Checking Content length*********************");
		String ContentLength=response.header("Content-Length");
		logger.info("Content Type===>"+ContentLength);
		if(Integer.parseInt(ContentLength)<100)
		{
			logger.info("Content Length is less than 100");
		}
		AssertJUnit.assertTrue(Integer.parseInt(ContentLength)>100);
		
	}
	@AfterTest
	public void TearDown()
	{
		logger.info("*******************TC001_Get_All_Employees_Record FINISHED*****************");
	}
	
	
	
}
