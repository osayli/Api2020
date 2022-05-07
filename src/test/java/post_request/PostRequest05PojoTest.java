package post_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostRequest05PojoTest extends HerokuAppBaseUrl {

    /*
   Given
         https://restful-booker.herokuapp.com/booking
         {
             "firstname": "Ali",
             "lastname": "Can",
             "totalprice": 999,
             "depositpaid": true,
             "bookingdates": {
                 "checkin": "2021-09-21",
                 "checkout": "2021-12-21"
              }
              "additionalneeds": "Breakfast with white tea, Dragon juice"
          }
     When
    I send POST Request to the URL
   Then
    Status code is 200
And
    Response body is like {
                                 "firstname": "Ali",
                                 "lastname": "Can",
                                 "totalprice": 999,
                                 "depositpaid": true,
                                 "bookingdates": {
                                     "checkin": "2021-09-21",
                                     "checkout": "2021-12-21"
                                  }
                                  "additionalneeds": "Breakfast with white tea, Dragon juice"
                               }
     */


    @Test

    public void post05() {
        //1) Set the URL
        spec.pathParam("first","booking");
        //2) Set the expected the data
        BookingDatesPojo bookingDates=new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo bookingPojo=new BookingPojo("Ali","Can",999,true,bookingDates,"Breakfast with white tea, Dragon juice");
        //3) Type the code to send request and Get the response
        Response response=given().
                spec(spec).
                contentType(ContentType.JSON).
                body(bookingPojo).
                when().
                post("/{first}");
        response.prettyPrint();
        //4) Do assertion

        BookingResponseBodyPojo actualPojo = response.as(BookingResponseBodyPojo.class);

        assertEquals(200, response.getStatusCode());

        assertEquals(bookingPojo.getFirstname(), actualPojo.getBooking().getFirstname());
        assertEquals(bookingPojo.getLastname(), actualPojo.getBooking().getLastname());
        assertEquals(bookingPojo.getTotalprice(), actualPojo.getBooking().getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(), actualPojo.getBooking().getDepositpaid());

        //1.Way: Can be used
        assertEquals(bookingPojo.getBookingdates().getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(), actualPojo.getBooking().getBookingdates().getCheckout());

        //2.Way: Recommended
        assertEquals(bookingDates.getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualPojo.getBooking().getBookingdates().getCheckout());



    }
}
