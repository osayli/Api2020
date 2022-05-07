package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest10Test extends GoRestBaseUrl {

    /*
    Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            {
                “meta”: null,
                “data”: {
                    “id”: 13,
                    “name”: “Fr. Ajit Prajapat”,
                    “email”: “ajit_fr_prajapat@barrows.org”,
                    “gender”: “female”,
                    “status”: “active”
                }
            }
     */

    @Test

    public void get10(){
        //1) Set the URL
        spec.pathParams("usersPath","users","id",13);

        //2) Set the expected the data
//        Map<String,String> dataMap=new HashMap<>();
//        dataMap.put("name", "Draupadi Tandon");
//        dataMap.put("email","tandon_draupadi@hickle.net");
//        dataMap.put("gender", "female");
//        dataMap.put("status","active");
        GoRestTestData dataMapObj=new GoRestTestData();
        Map<String,String> dataMap=dataMapObj.dataMap("Draupadi Tandon","tandon_draupadi@hickle.net", "female","active");

//        Map<String,Object> expectedDataMap=new HashMap<>();
//        expectedDataMap.put("meta", null);
//        expectedDataMap.put("data", dataMap);
        GoRestTestData expectedDataMapObj=new GoRestTestData();
        Map<String,Object> expectedDataMap=expectedDataMapObj.expectedDataMap(null,dataMap);

        //3) Type the code to send request and Get the response
        Response response=given().spec(spec).when().get("/{usersPath}/{id}");
        response.prettyPrint();
        //4) Do assertion
        Map<String,Object> actualDataMap=response.as(HashMap.class);

        assertEquals(expectedDataMap.get("meta"),actualDataMap.get("meta"));
        assertEquals(dataMap.get("name"),((Map)actualDataMap.get("data")).get("name"));
        assertEquals(dataMap.get("email"),((Map)actualDataMap.get("data")).get("email"));
        assertEquals(dataMap.get("gender"),((Map)actualDataMap.get("data")).get("gender"));

    }
}
