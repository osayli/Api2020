package get_request;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest17Test extends DummyBaseUrl {
    /*
   URL: https://dummy.restapiexample.com/api/v1/employee/1
   HTTP Request Method: GET Request
   Test Case: Type by using Gherkin Language
   Assert:     i) Status code is 200
           ii) "employee_name" is "Tiger Nixon"
          iii) "employee_salary" is 320800
           iv)  "employee_age" is 61
            v) "status" is "success"
           vi)  "message" is "Successfully! Record has been fetched."
 */
    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends the GET request the URL
    Then
        Status code is 200
 And
        "employee_name" is "Tiger Nixon"
  And
        "employee_salary" is 320800
  And
        "employee_age" is 61
  And
        "status" is "success"
  And
        "message" is "Successfully! Record has been fetched."


     */

    @Test
    public void get17(){
        //1) Set the URL
      spec.pathParams("first","employee","second",1);
        //2) Set the expected the data
        DummyApiDataPojo dummyApiDataPojo=new DummyApiDataPojo("Tiger Nixon",320800,61,"");
        DummyApiResponseBodyPojo expectedDataPojo=new DummyApiResponseBodyPojo("success",dummyApiDataPojo,"Successfully! Record has been fetched.");
        //3) Type the code to send request and Get the response
        Response response=given().
                spec(spec).
                contentType(ContentType.JSON).
                when().get("/{first}/{second}");
        response.prettyPrint();
        //Do assertion
        response.then().assertThat().statusCode(200);
        DummyApiResponseBodyPojo actualDataPojo= JsonUtil.convertJsonToJavaObject(response.asString(),DummyApiResponseBodyPojo.class);
        assertEquals(expectedDataPojo.getStatus(),actualDataPojo.getStatus());
        assertEquals(expectedDataPojo.getMessage(),actualDataPojo.getMessage());
        assertEquals(dummyApiDataPojo.getEmployee_name(),actualDataPojo.getData().getEmployee_name());
        assertEquals(dummyApiDataPojo.getEmployee_salary(),actualDataPojo.getData().getEmployee_salary());
        assertEquals(dummyApiDataPojo.getEmployee_age(),actualDataPojo.getData().getEmployee_age());
        assertEquals(dummyApiDataPojo.getProfile_image(),actualDataPojo.getData().getProfile_image());
    }
}
