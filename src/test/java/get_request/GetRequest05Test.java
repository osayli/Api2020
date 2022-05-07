package get_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest05Test extends HerokuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	And
	   Among the data there should be someone whose firstname is "Mary" and last name is "Ericsson"
     */


    @Test
    public void get05(){
        //1) Set the URL
        //   //https://restful-booker.herokuapp.com/booking?firstname=Eric&lastname=Jackson
        spec.pathParam("bookingPath","booking").
                queryParams("firstname","Eric","lastname","Jackson");

        //2) Set the expected the data(post,put and patch)


        //3) Type the code to send request and Get the response
        Response response=given().
                                accept(ContentType.JSON).
                                spec(spec).
                           when().
                                 get("/{bookingPath}");

        response.prettyPrint();

        //4) Do assertion

        response.then().assertThat().statusCode(200);

        assertTrue(response.asString().contains("bookingid"));


    }
}
