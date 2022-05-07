package get_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GetRequest06Test extends HerokuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking/5
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
    "firstname": "Sally",
    "lastname": "Jackson",
    "totalprice": 949,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2016-09-04",
        "checkout": "2017-04-17"
    }
     */
    @Test
    public void get06(){
        //1) Set the URL
        spec.pathParams("bookingPath","booking","id",5);
        //2) Set the expected the data


        //3) Type the code to send request and Get the response
        Response response=given().
                                spec(spec).
                          when().
                                get("/{bookingPath}/{id}");
        response.prettyPrint();

        //4) Do assertion
      //1. way
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Mark"),
                        "lastname",equalTo("Ericsson"),
                        "totalprice",equalTo(423),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2019-09-26"),
                        "bookingdates.checkout",equalTo("2021-11-28"));

        //2. way: we will use JsonPath class
        JsonPath json=response.jsonPath();
        assertEquals("Mark",json.getString("firstname"));
        assertEquals("Ericsson",json.getString("lastname"));
        assertEquals(423,json.getInt("totalprice"));
        assertEquals(false,json.getBoolean("depositpaid"));
        assertEquals("2019-09-26",json.getString("bookingdates.checkin"));
        assertEquals("2021-11-28",json.getString("bookingdates.checkout"));

        //3. way: we will use Soft Assertion
        /*
        To use Soft Assertion follow given 3 steps
        1)Create SoftAssert Object
        2)by using softAssert object to assertion
        3)use assertall() ,otherwise you will get passed everytime,
        but it will be not meaningful
         */
        //1. step: Create SoftAssert Object
        SoftAssert softAssert=new SoftAssert();
        //2)by using softAssert object to assertion
        softAssert.assertEquals(json.getString("firstname"),"Mark","firstname did not match");
        softAssert.assertTrue(json.getString("lastname").equals("Ericsson"),"lastname did not match");
        softAssert.assertEquals(json.getString("lastname"),"Ericsson","lastname did not match");
        softAssert.assertEquals(json.getInt("totalprice"),423,"totalprice did not match");
        softAssert.assertFalse(json.getBoolean("depositpaid"),"Depositpaid is not false");
        softAssert.assertEquals(json.getBoolean("depositpaid"),false,"deposit did not match");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2019-09-26","checkin date did not match");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2021-11-28","checkout date did not match");
        //3)use assertall() method,otherwise you will get passed everytime,
        //        but it will be not meaningful
        softAssert.assertAll();
 }
}
