package get_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequest03Test extends JsonPlaceHolderBaseUrl {

/*
 Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be "application/json"
		And
		    "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
		And
		    "completed" is false
		And
		    "userId" is 2
 */






    @Test
    public void get03(){
        //1) Set the URL
        //String url="https://jsonplaceholder.typicode.com/todos/23";// not recommended
        spec.pathParams("todosPath", "todos", "id", 23);
        //2)Set the expected the data(post,put and patch)


        //3)Type the code to send request
        Response response=given().
                              spec(spec).
                          when().
                              get("/{todosPath}/{id}");

        response.prettyPrint();

        //4)Do assertion
        //1. way
        response.then().
                       assertThat().
                       statusCode(200).
                       contentType("application/json").
                       body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                       body( "completed",equalTo(false)).
                       body("userId", equalTo(2));

        //2. way ==>preferable
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                 "completed",equalTo(false),
                  "userId", equalTo(2));

        /*
        Note 1: When you execute assertion, Java stop execution just after the first failure
        it means assertions after the failure were not executed. if assertion were not executed,
        you cannot tell anything about their status. They may pass or they may fail.
        But the assertions before failure were passed, because assertions before failure were executed.

        Note 2: if you type your code as execution will stop in the first failure
        this is called "hard assertion".(1. way hard assertion)(Hard assertion==Assertion)

        Note 3: Note 2: if you type your code as execution will not stop in any failure
        this is called "soft assertion".(2. way body assertion is soft assertion)(Soft Assertion==Verification)

        Note4: if you use multiple body method it will work like "hard assertion",
        if you use a body method with multiple assertions it will work like "soft assertion"
         */
    }
}
