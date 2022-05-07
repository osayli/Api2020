package get_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest14ObjectMapperTest extends JsonPlaceHolderBaseUrl {
/*
Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
 */


    @Test
    public void get14(){
        //1) Set the URL
        spec.pathParams("first","todos","second",198);
        //2) Set the expected the data
//        String expectedData="{\n" +
//                " \"userId\": 10,\n" +
//                "\"id\": 198,\n" +
//                "\"title\": \"quis eius est sint explicabo\",\n" +
//                " \"completed\": true\n" +
//                " }";
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        String expectedData=obj.expectedDataInString(10,"quis eius est sint explicabo",true);
        HashMap<String,Object> expectedDataMap=JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);
        System.out.println(expectedDataMap);
        //3) Type the code to send request and Get the response
        Response response=given().
                spec(spec).
                contentType(ContentType.JSON).
                when().
                get("/{first}/{second}");
        response.prettyPrint();
        //Do assertion
        HashMap<String,Object> actualDataMap=JsonUtil.convertJsonToJavaObject(response.asString(),HashMap.class);
        assertEquals(200,response.getStatusCode());

        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
    }
    @Test//(This is Best Solution)
    public void get14_2(){
        //1) Set the URL
        spec.pathParams("first","todos","second",198);
        //2) Set the expected the data
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        String expectedData=obj.expectedDataInString(10,"quis eius est sint explicabo",true);
        JsonPlaceHolderPojo expectedDataPojo=JsonUtil.convertJsonToJavaObject(expectedData, JsonPlaceHolderPojo.class);
        //3) Type the code to send request and Get the response
        Response response=given().
                spec(spec).
                contentType(ContentType.JSON).
                when().
                get("/{first}/{second}");
        response.prettyPrint();
        //Do assertion
        JsonPlaceHolderPojo actualDataPojo=JsonUtil.convertJsonToJavaObject(response.asString(),JsonPlaceHolderPojo.class);
        assertEquals(200,response.getStatusCode());

        assertEquals(expectedDataPojo.getUserId(),actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(),actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(),actualDataPojo.getCompleted());

    }
}
