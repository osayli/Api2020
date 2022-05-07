package get_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetRequest09Test extends HerokuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/1
        When
	 		I send GET Request to the url
	 	Then
	 		Response body should be like that;
	 		{
			    "firstname": "Eric",
			    "lastname": "Smith",
			    "totalprice": 555,
			    "depositpaid": false,
			    "bookingdates": {
			        "checkin": "2016-09-09",
			        "checkout": "2017-09-21"
			     }
			}
     */

    @Test
    public void get09(){
        //1) Set the URL
        spec.pathParams("bookingPath","booking","id",1);
        //2) Set the expected the data
        Map<String,String> bookingDatesMap=new HashMap<>();
        bookingDatesMap.put("checkin","2018-02-11");
        bookingDatesMap.put( "checkout", "2022-04-25");

        Map<String,Object> expectedDataMap=new HashMap<>();
        expectedDataMap.put("firstname", "Mark");
        expectedDataMap.put("lastname", "Jones");
        expectedDataMap.put("totalprice", 381);
        expectedDataMap.put("depositpaid", true);
        expectedDataMap.put("bookingdates",bookingDatesMap);
        System.out.println(expectedDataMap);
        //3) Type the code to send request and Get the response
        Response response=given().
                                  spec(spec).
                          when().
                                  get("/{bookingPath}/{id}");
        response.prettyPrint();
        //4) Do assertion
        Map<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);
        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        //1. way
        assertEquals(bookingDatesMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));

        //2.way
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));
    }
}
