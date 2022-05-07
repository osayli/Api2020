package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class DeleteRequest01Test extends JsonPlaceHolderBaseUrl {



    /*
     Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */




    @Test
    public void delete01(){
        //1) Set the URL
        spec.pathParams("first","todos","second",198);
        //2) Set the expected the data
        Map<String,Object> expectedDataMap=new HashMap<>();

        //3) Type the code to send request and Get the response
        Response response=given().
                spec(spec).
                when().
                delete("/{first}/{second}");
        response.prettyPrint();
        //4) Do assertion
        //1. way
        Map<String,Object> actualDataMap=response.as(HashMap.class);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedDataMap,actualDataMap);
        //2.way: (no need to create expectedDataMap)
        response.then().assertThat().statusCode(200);
        assertTrue(actualDataMap.size()==0);
        //or (recommended)
        assertTrue(actualDataMap.isEmpty());

        /*
        Interview Question: How to automate "delete request" in API Testing?
          a)Create new data by using "Post Request"
          b)Use Delete Request to delete newly created data

        Note:Do not use Delete Request for the existing data create your own data then delete it
         */


    }
}
