package delete_request;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiDeletePojo;
import pojos.DummyApiResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class DeleteRequest02Test extends DummyBaseUrl {
  /*
    URL: https://dummy.restapiexample.com/api/v1/delete/2
   HTTP Request Method: DELETE Request
   Test Case: Type by using Gherkin Language
   Assert:     i) Status code is 200
           ii) "status" is "success"
          iii) "data" is "2"
           iv) "message" is "Successfully! Record has been deleted"
 */
  @Test
  public void delete02(){
      //1) Set the URL
      spec.pathParams("first","delete","second",2);
      //2) Set the expected the data
      DummyApiDeletePojo expectedDataPojo=new DummyApiDeletePojo("success", "2","Successfully! Record has been deleted");
      //3) Type the code to send request and Get the response
      Response response=given().
              spec(spec).
              contentType(ContentType.JSON).
              when().
              delete("/{first}/{second}");
      response.prettyPrint();
      //Do assertion
      response.then().assertThat().statusCode(200);
      DummyApiDeletePojo actualDataPojo= JsonUtil.convertJsonToJavaObject(response.asString(),DummyApiDeletePojo.class);
      assertEquals(expectedDataPojo.getStatus(),actualDataPojo.getStatus());
      assertEquals(expectedDataPojo.getData(),actualDataPojo.getData());
      assertEquals(expectedDataPojo.getMessage(),actualDataPojo.getMessage());

  }
}
