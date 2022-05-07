package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetRequest02Test {


    /*
    Given
        https://restful-booker.herokuapp.com/booking/1001
    When
        User send a GET Request to the url
    Then
        HTTP Status code should be 404
    And
        Status Line should be HTTP/1.1 404 Not Found
    And
        Response body contains "Not Found"
    And
        Response body does not contain "TechProEd"
    And
        Server is "Cowboy"
 */







    @Test
    public void get02(){
        //1) Set the URL
        String url="https://restful-booker.herokuapp.com/booking/1001";

        //2)Set the expected the data(post,put and patch)


        //3)Type the code to send request
        Response response=given().
                          when().
                                get(url);
        //remove after finish your assertion
        response.prettyPrint();

        //4)Do assertion

        response.then().
                    assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");
        //How to assert if the response body has a specific text?
        //assertTrue(x) passes if the condition is true.
        assertTrue(response.asString().contains("Not Found"));
        //assertFalse(x) passes if the condition is false.
        assertFalse(response.asString().contains("TechProEd"));

        assertEquals("Cowboy",response.getHeader("Server"));
    }
}
