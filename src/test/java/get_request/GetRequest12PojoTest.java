package get_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest12PojoTest extends HerokuAppBaseUrl {

    /*
         Given
         https://restful-booker.herokuapp.com/booking/2
     When
    I send GET Request to the URL
   Then
    Status code is 200
And
    Response body is like {
                                 "firstname": "Mary",
                                 "lastname": "Brown",
                                 "totalprice": 227,
                                 "depositpaid": true,
                                 "bookingdates": {
                                     "checkin": "2015-10-21",
                                     "checkout": "2021-08-28"
                                 },
                                 "additionalneeds": "Breakfast"
                              }
     */


    @Test
    public void get12(){

        //1) Set the URL
        spec.pathParams("first","booking","second",2);
        //2) Set the expected the data
        BookingDatesPojo bookingDates=new BookingDatesPojo("2015-08-07","2016-06-12");
        BookingPojo bookingPojo=new BookingPojo("Susan","Brown",444,false,bookingDates,"Breakfast");
        //3) Type the code to send request and Get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");

        //Do assertion
        BookingPojo actualPojo=response.as(BookingPojo.class);
        assertEquals(bookingPojo.getFirstname(), actualPojo.getFirstname());
        assertEquals(bookingPojo.getLastname(), actualPojo.getLastname());
        assertEquals(bookingPojo.getTotalprice(), actualPojo.getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(), actualPojo.getDepositpaid());
         //1. way
        assertEquals(bookingPojo.getBookingdates().getCheckin(), actualPojo.getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(), actualPojo.getBookingdates().getCheckout());
        //2. way
        assertEquals(bookingDates.getCheckin(), actualPojo.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualPojo.getBookingdates().getCheckout());

    }
}
