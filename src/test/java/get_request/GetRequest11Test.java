package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GetRequest11Test extends GoRestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 20
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 20
        And
            We have at least one "active" status
        And
            "Chandani Mehrotra", "Dakshayani Trivedi" are among the users
        And
            The male users are more than female users
     */


    @Test
    public void get11(){
        //1) Set the URL
       spec.pathParam("usersPath","users");
        //2) Set the expected the data

        //3) Type the code to send request and Get the response
         Response response=given().
                                  spec(spec).
                           when().
                                 get("/{usersPath}");

         response.prettyPrint();
        //4) Do assertion
        //by using response body

        response.then().
                   assertThat().
                   statusCode(200).body("meta.pagination.limit", equalTo(20),
                               "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                               "data.id",hasSize(20),
                               "data.status",hasItem("active"),
                               "data.name",hasItems("Chandani Mehrotra", "Dakshayani Trivedi"));
        // The male users are more than female users
        //I will get all genders then I will count the females and I will
        //calculate the males and we will compare them.
        JsonPath jsonPath=response.jsonPath();
        List<String> gender=jsonPath.getList("data.gender");
        System.out.println(gender);
        int numOfMale=0;
        for(String w:gender){
            if(w.equals("male")){
              numOfMale++;
            }
        }
        System.out.println(numOfMale);
        assertTrue(numOfMale>(gender.size()-numOfMale));
    }
}
