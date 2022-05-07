package post_request;

   /*
    Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
    */

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostRequest02Test extends JsonPlaceHolderBaseUrl {


    @Test
    public void post02(){
    //1) Set the URL
    spec.pathParam("todosPath","todos");

    //2) Set the expected the data(Set the request body or payload for post request)
        JsonPlaceHolderTestData expectedDataObj=new JsonPlaceHolderTestData();
        Map<String,Object> expectedDataMap=expectedDataObj.expectedDataWithAllKeys(55,"Tidy your room", false);
        System.out.println(expectedDataMap);
    //3) Type the code to send request and Get the response
        Response response=given().
                spec(spec).
                contentType(ContentType.JSON).
                body(expectedDataMap).when().
                post("/{todosPath}");

        response.prettyPrint();
    //4) Do assertion

      //Use GSON

        Map<String,Object> actualDataMap=response.as(HashMap.class);
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
    }
}
