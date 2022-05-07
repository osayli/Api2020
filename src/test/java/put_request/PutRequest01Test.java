package put_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PutRequest01Test extends JsonPlaceHolderBaseUrl {

    /*
    Given
	        https://jsonplaceholder.typicode.com/todos/198
	        {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
            }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test

    public void put01(){
        //1) Set the URL
    spec.pathParams("todosPath","todos","id",198);
        //2) Set the expected the data
        JsonPlaceHolderTestData expectedDataObj=new JsonPlaceHolderTestData();
        Map<String,Object> expectedDataMap=expectedDataObj.expectedDataWithAllKeys(21,"Wash the dishes", false);
        System.out.println(expectedDataMap);
        //3) Type the code to send request and Get the response
        Response response=given().
                            spec(spec).
                            contentType(ContentType.JSON).
                            body(expectedDataMap).when().put("/{todosPath}/{id}");
        response.prettyPrint();
        //4) Do assertion

        Map<String,Object> actualDataMap=response.as(HashMap.class);
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
    }

}
