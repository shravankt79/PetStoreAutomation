package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
// this method will generate fake data 	
	Faker faker;
	User userPayload;
	public Logger logger; //for logs
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userPayload=new User();	
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//Logs 
		logger=LogManager.getLogger(this.getClass());
		
		
	}
// We are passing above generated fake data in the below method
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("************Creating user***************");
		Response response=UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
		
		logger.info("***********User is created***************");
	}
	
// Below method to get Data 
	@Test(priority=2)
	public void testGetUserbyName()
	{
		logger.info("************Regarding user Info***************");
		Response response=UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);	
		
		logger.info("************User info is Displayed***************");
		
	}
	
// below method to update user by Name 
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("************Updating user***************");
	    // update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************User is updated***************");
		
		// Verifying data after update
		Response responseAfterupdate=UserEndPoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);		
		
	}
	
// Below method to delete user
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("************Deleting user***************");
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);	
		
		logger.info("************User is deleted***************");
	}
	
	
}
	

