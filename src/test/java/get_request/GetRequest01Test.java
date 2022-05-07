package get_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest01Test {

    /*
    1) Postman is used for manual testing
    2)We use Rest-Assured Library for API Automation Testing
    3)To type Automation scripts follow the given steps
        a)Understand the requirements
        b)Type our test cases
           i)To type our test cases we will use 'Gherkin Language'
           Gherkin Language has some keyword to type test cases
             x)Given: it is used for pre-requisites.
             y)When: it is for actions.
             z)Then:it is for outputs.
             t)And: it for multiple given or then or when
        c)Start to type Automation Scripts
            i)Set URL
            ii) Set the expected the data(post,put and patch)
            iii)Type the code to send request
            iv)Do assertion
     */

    /* TASK
    Given
        https://restful-booker.herokuapp.com/booking/3
    When
        User send a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK

 */

    @Test

    public void get01(){
        //1) Set the URL

        String url="https://restful-booker.herokuapp.com/booking/3";

        //2)Set the expected the data(post,put and patch)

        //3)Type the code to send request
        Response response=given().
                                 contentType("application/json").
                           when().
                                  get(url);
        response.prettyPrint();
        //4)Do assertion

        response.then().
                       assertThat().
                       statusCode(200).
                       contentType(ContentType.JSON).
                       statusLine("HTTP/1.1 200 OK");
        //How to print "Status Code" on the console
        System.out.println(response.getStatusCode());
        //How to print "Content Type" on the console
        System.out.println(response.contentType());
        //How to print "Status Line" on the console
        System.out.println(response.getStatusLine());
        //How to print "Header" on the console
        System.out.println(response.getHeaders());
        //How to print specific part of "Header" on the console
        System.out.println(response.getHeader("Connection"));
        //How to print "Time" on the console
        System.out.println(response.getTime());

    }
}
