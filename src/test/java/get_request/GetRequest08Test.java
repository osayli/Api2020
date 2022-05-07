package get_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest08Test extends JsonPlaceHolderBaseUrl {


        /*
        Serialization: To convert Java Object to JSON Data
        De-Serialization: To convert JSON Data to Java Object

        To do De-Serialization and Serialization we can use the followings;
        1)Gson: Google Created
        2)Object Mapper: More popular
     */



    /*
     Given
            https://jsonplaceholder.typicode.com/todos/2
        When
	  		I send GET Request to the URL
	    Then
	  		Status code is 200
	  		And "completed" is false
	  		And "userId" is 1
	  		And "title" is "quis ut nam facilis et officia qui"
	  		And header "Via" is "1.1 Vegur"
	  		And header "Server" is "cloudflare"
	  		{
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

   @Test

    public void get08(){
       //1) Set the URL
       spec.pathParams("todosPath","todos","id",2);
       //2) Set the expected the data
       Map<String,Object> expectedData=new HashMap<>();
       expectedData.put("userId",1);
       expectedData.put("title","quis ut nam facilis et officia qui");
       expectedData.put("completed",false);
       expectedData.put("StatusCode",200);
       expectedData.put("Via","1.1 vegur");
       expectedData.put("Server","cloudflare");
       System.out.println(expectedData);
       //3) Type the code to send request and Get the response
       Response response=given().
                             spec(spec).
                             accept(ContentType.JSON).
                          when().get("/{todosPath}/{id}");
       response.prettyPrint();
       //4) Do assertion
       //1. way
       Map<String,Object> actualData=response.as(HashMap.class);
       System.out.println(actualData);

       assertEquals(expectedData.get("userId"),actualData.get("userId"));
       assertEquals(expectedData.get("title"),actualData.get("title"));
       assertEquals(expectedData.get("completed"),actualData.get("completed"));
       assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
       assertEquals(expectedData.get("Via"),response.getHeader("Via"));
       assertEquals(expectedData.get("Server"),response.getHeader("Server"));

   }
   @Test
   public void get0802(){
      //1) Set the URL
      spec.pathParams("todosPath","todos","id",2);

      //2) Set the expected the data
      JsonPlaceHolderTestData ExpectedDataObj=new JsonPlaceHolderTestData();
      Map<String,Object> expectedData=ExpectedDataObj.expectedDataWithAllKeys(1,"quis ut nam facilis et officia qui",false);
      expectedData.put("completed",false);
      expectedData.put("StatusCode",200);
      expectedData.put("Via","1.1 vegur");
      expectedData.put("Server","cloudflare");
      System.out.println(expectedData);
      //3) Type the code to send request and Get the response
      Response response=given().
              spec(spec).
              accept(ContentType.JSON).
              when().get("/{todosPath}/{id}");
      response.prettyPrint();

      //4) Do assertion
      Map<String,Object> actualData=response.as(HashMap.class);
      System.out.println(actualData);
      assertEquals(expectedData.get("userId"),actualData.get("userId"));
      assertEquals(expectedData.get("title"),actualData.get("title"));
      assertEquals(expectedData.get("completed"),actualData.get("completed"));
      assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
      assertEquals(expectedData.get("Via"),response.getHeader("Via"));
      assertEquals(expectedData.get("Server"),response.getHeader("Server"));

   }

}
