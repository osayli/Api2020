package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest13PojoTest extends GoRestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v1/users/13
    When
        User send GET Request to the URL
    Then
        Status Code should be 200
    And
        Response body should be like
   {
    "meta": null,
    "data": {
        "id": 13,
        "name": "Sanya Pandey",
        "email": "sanya_pandey@collier.io",
        "gender": "female",
        "status": "inactive"
    }
*/
       /*
        To do that task, do the followings;
        1)Check the response body on Postman
        2)Create Pojo Classes
        3)Follow the 4 steps in API automation
     */

    @Test
    public void  get13(){
        //1) Set the URL
        spec.pathParams("first","users","second",13);
        //2) Set the expected the data
        GoRestDataPojo goRestDataPojo=new GoRestDataPojo(13,"Sanya Pandey","sanya_pandey@collier.io","female","inactive");
        GoRestResponseBodyPojo goRestResponseBodyPojo =new GoRestResponseBodyPojo(null,goRestDataPojo);
        //3) Type the code to send request and Get the response

        Response response=given().
                            spec(spec).
                            contentType(ContentType.JSON).
                          when().
                             get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion

        GoRestResponseBodyPojo actualPojo=response.as(GoRestResponseBodyPojo.class);

        assertEquals(goRestResponseBodyPojo.getMeta(),actualPojo.getMeta());
        assertEquals(goRestDataPojo.getId(),actualPojo.getData().getId());
        assertEquals(goRestDataPojo.getName(),actualPojo.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualPojo.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualPojo.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actualPojo.getData().getStatus());

        //Soft Assertion
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualPojo.getMeta(), goRestResponseBodyPojo.getMeta());
        softAssert.assertEquals(actualPojo.getData().getId(),goRestDataPojo.getId());
        softAssert.assertEquals(actualPojo.getData().getName(),goRestDataPojo.getName());
        softAssert.assertEquals(actualPojo.getData().getEmail(),goRestDataPojo.getEmail());
        softAssert.assertEquals(actualPojo.getData().getGender(),goRestDataPojo.getGender());
        softAssert.assertEquals(actualPojo.getData().getStatus(),goRestDataPojo.getStatus());
        softAssert.assertAll();

    }
}
