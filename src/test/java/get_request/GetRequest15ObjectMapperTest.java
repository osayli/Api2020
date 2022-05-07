package get_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest15ObjectMapperTest extends HerokuAppBaseUrl {
/*
Given
	            https://restful-booker.herokuapp.com/booking/2
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
{
    "firstname": "Jim",
    "lastname": "Smith",
    "totalprice": 649,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2015-09-16",
        "checkout": "2018-04-09"
    },
    "additionalneeds": "Breakfast"
}
 */

    @Test
    public void get15(){
        //1) Set the URL
        spec.pathParams("first","booking","second",2);
        //2) Set the expected the data
        String expectedData="{\n" +
                "    \"firstname\": \"Jim\",\n" +
                "    \"lastname\": \"Ericsson\",\n" +
                "    \"totalprice\": 249,\n" +
                "    \"depositpaid\": false,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2015-06-28\",\n" +
                "        \"checkout\": \"2016-06-15\"\n" +
                "    },\n" +
                "    \"additionalneeds\": null\n" +
                "}";
        BookingPojo expectedDataPojo=JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);

        //3) Type the code to send request and Get the response
        Response response=given().
                spec(spec).
                contentType(ContentType.JSON).
                when().
                get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        BookingPojo actualDataPojo=JsonUtil.convertJsonToJavaObject(response.asString(), BookingPojo.class);
        assertEquals(200,response.getStatusCode());
        //Hard Assertion
        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
        assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());
        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());

        //Soft Assertion
        //1)create SoftAssert Object
        SoftAssert softAssert=new SoftAssert();
        //2)do assertion
        softAssert.assertEquals(actualDataPojo.getFirstname(),expectedDataPojo.getFirstname(),"Firstname did not match");
        softAssert.assertEquals(actualDataPojo.getLastname(),expectedDataPojo.getLastname(),"Lastname did not match");
        softAssert.assertEquals(actualDataPojo.getTotalprice(),expectedDataPojo.getTotalprice(),"Total price did not match");
        softAssert.assertEquals(actualDataPojo.getDepositpaid(),expectedDataPojo.getDepositpaid(),"Deposit paid did not match");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(),expectedDataPojo.getBookingdates().getCheckin(),"Check_in dates did not match");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(),expectedDataPojo.getBookingdates().getCheckout(),"Checkout dates did not match");
        softAssert.assertEquals(actualDataPojo.getAdditionalneeds(),expectedDataPojo.getAdditionalneeds(),"Additional need did not match");
        //3) assertAll
        softAssert.assertAll();
    }
    //Note: Improve String Expected Data to a Method in HerOkuAppTestData Class
}
