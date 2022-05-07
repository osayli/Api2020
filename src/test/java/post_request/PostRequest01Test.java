package post_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class PostRequest01Test extends HerokuAppBaseUrl {

    /*
    Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
             "firstname": "Selim",
             "lastname": "Ak",
             "totalprice": 11111,
             "depositpaid": true,
             "bookingdates": {
                 "checkin": "2021-09-09",
                 "checkout": "2021-09-21"
              }
           }
    When
     I send POST Request to the Url
  Then
     Status code is 200
     And response body should be like {
                                 "bookingid": 11,
                                 "booking": {
                                     "firstname": "Selim",
                                     "lastname": "Ak",
                                     "totalprice": 11111,
                                     "depositpaid": true,
                                     "bookingdates": {
                                         "checkin": "2020-09-09",
                                         "checkout": "2020-09-21"
                                     }
                                 }
                              }
 */

    /*
    Note: The data you send in the request is called "request body" or "payload".
    Note: The data you get in response is called "response body".
     */

    @Test
    public void post01(){
        //1) Set the URL
        spec.pathParam("bookingPath","booking");

        //2) Set the expected the data
        HerokuAppTestData expectedDataObj=new HerokuAppTestData();
        Map<String,String> bookingDates =expectedDataObj.bookingDateSetUp("2020-09-09", "2020-09-21");
        //System.out.println(bookingDates);
        Map<String,Object> expectedDataMap =expectedDataObj.expectedDataSetUp("Selim","Ak",11111,true,bookingDates);
        //System.out.println(expectedDataMap);
        //3) Type the code to send request and Get the response
        Response response=given().
                              spec(spec).
                              contentType(ContentType.JSON).
                              body(expectedDataMap).
                           when().
                              post("/{bookingPath}");
        response.prettyPrint();
        //4) Do assertion
        //1.way: GSON
        Map<String,Object> actualDataMap=response.as(HashMap.class);

        assertEquals(expectedDataMap.get("firstname"),((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),((Map)actualDataMap.get("booking")).get("depositpaid"));
        assertEquals(bookingDates.get("checkin"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDates.get("checkout"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));
    }
}
