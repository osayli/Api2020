package patch_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class PatchRequest01Test extends JsonPlaceHolderBaseUrl {

    /*
    Given
	        https://jsonplaceholder.typicode.com/todos/198
	        {
                "title": "Wash the dishes"
            }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */




    @Test
    public void patch01(){

        //1) Set the URL
        spec.pathParams("first","todos","second",198);

        //2) Set the expected the data(Set the request body or payload for post request)
        JsonPlaceHolderTestData expectedDataObj=new JsonPlaceHolderTestData();
        Map<String,Object> expectedDataMap=expectedDataObj.expectedDataWithMissingData(null,"Wash the dishes", null);
        System.out.println(expectedDataMap);
        //3) Type the code to send request and Get the response
        Response response=given().
                              spec(spec).
                              contentType(ContentType.JSON).
                              body(expectedDataMap).
                          when().
                              patch("/{first}/{second}");
        response.prettyPrint();
        //4) Do assertion
         //1. way
        response.then().assertThat().statusCode(200).body("title",equalTo(expectedDataMap.get("title")));

        /*
        when you do Patch Assertion, just the data you updated should be asserted.
        But if someone insists on assert all parts do the following:

         */
        //2. way

        Map<String,Object> expectedDataMapAll=expectedDataObj.expectedDataWithAllKeys(10,"Wash the dishes", true);
        response.then().assertThat().statusCode(200).body("userId",equalTo(expectedDataMapAll.get("userId")),
                "title",equalTo(expectedDataMapAll.get("title")),
                        "completed",equalTo(expectedDataMapAll.get("completed")));

    }
}
