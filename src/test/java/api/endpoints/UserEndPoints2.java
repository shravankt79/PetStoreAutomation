package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java
//Created for perform Create,Read,Update,Delete requests to the user API.

public class UserEndPoints2 {
	
	// Additional method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // load properties file, here routes is name of the properties file
		return routes; // Now routes is representing the properties file
	}
	
	public static Response createUser(User payload)
	{
	  String post_url=getURL().getString("post_url");  // Additionally added this line to get url from from property file as string  same will repeat for other requests
	  Response response=given()
         .contentType(ContentType.JSON)
         .accept(ContentType.JSON)
         .body(payload)
	  .when()
	      .post(post_url); // this line will post URL
	  return response;
	}
	
	public static Response readUser(String userName)
	{
	  String get_url=getURL().getString("get_url"); 
	  Response response=given()
         .pathParam("username", userName)
	  .when()
	      .get(get_url); 
	  return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
	  String update_url=getURL().getString("update_url"); 
	  Response response=given()
         .contentType(ContentType.JSON)
         .accept(ContentType.JSON)
         .pathParam("username", userName)
         .body(payload)
	  .when()
	      .put(update_url);
	  return response; 
	}
	  
	  public static Response deleteUser(String userName)
		{
		  String delete_url=getURL().getString("delete_url"); 
		  Response response=given()
	         .pathParam("username", userName)
		  .when()
		      .get(delete_url);
		  return response;
		}
	

}
