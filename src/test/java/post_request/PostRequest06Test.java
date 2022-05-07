package post_request;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest06Test extends DummyBaseUrl {
    /*
   URL: https://dummy.restapiexample.com/api/v1/create
   HTTP Request Method: POST Request,
   Request body: {
                    "employee_name": "Tom Hanks",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image"
                 }
   Test Case: Type by using Gherkin Language
   Assert:
            i) Status code is 200
            ii) Response body should be like the following
                {
                    "status": "success",
                    "data": {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id": 6344
                    },
                    "message": "Successfully! Record has been added."
                }
 */
    /*

     */
    @Test
    public void post06(){
        //1) Set the URL
        spec.pathParam("first","create");
        //2) Set the expected the data
        DummyApiDataPojo dummyApiDataPojo=new DummyApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyApiResponseBodyPojo expectedDataPojo=new DummyApiResponseBodyPojo("success",dummyApiDataPojo,"Successfully! Record has been added.");
        //3) Type the code to send request and Get the response
        //3) Type the code to send request and Get the response
        Response response=given().
                          spec(spec).
                contentType(ContentType.JSON).
                body(dummyApiDataPojo).
                when().
                post("/{first}");
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
