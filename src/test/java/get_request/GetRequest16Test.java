package get_request;

import base_urls.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.*;

public class GetRequest16Test extends DummyBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/employees
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert:  i) Status code is 200
               ii) There are 24 employees
              iii) "Tiger Nixon" and "Garrett Winters" are among the employees
               iv) The greatest age is 66
                v) The name of the lowest age is "Tatyana Fitzpatrick"
               vi) Total salary of all employees is 6,644,770
*/
    /*
    Given
       URL: https://dummy.restapiexample.com/api/v1/employees
    When
       User sent GET request to the URL
    Then
       Status code is 200
    And
       There are 24 employees
    And
       "Tiger Nixon" and "Garrett Winters" are among the employees
    And
       The greatest age is 66
    And
       The name of the lowest age is "Tatyana Fitzpatrick"
    And
       Total salary of all employees is 6,644,770
     */

    @Test
    public void get16(){
        //1) Set the URL
         spec.pathParam("first","employees");
        //2) Set the expected the data

        //3) Type the code to send request and Get the response
        Response response =given().
                                 spec(spec).
                                 contentType(ContentType.JSON).
                           when().
                                 get("/{first}");
        response.prettyPrint();
        //Do assertion
        //      Status code is 200
        //       There are 24 employees
        //       "Tiger Nixon" and "Garrett Winters" are among the employees
        response.then().
                assertThat().
                statusCode(200).
                body("data.id",hasSize(24),
                        "data.employee_name",hasItems("Tiger Nixon" , "Garrett Winters"));
        //The greatest age is 66
        JsonPath json=response.jsonPath();
        List<Integer> ageList=json.getList("data.findAll{it.id>0}.employee_age");
        System.out.println(ageList);
        Collections.sort(ageList);
        System.out.println(ageList);
        assertEquals(66,(int) ageList.get(ageList.size()-1));
        //The name of the lowest age is "Tatyana Fitzpatrick"
        // String minAgeEmployeeName=json.getString("data.findAll{it.employee_age==19}.employee_name");
        //System.out.println(minAgeEmployeeName);
        String groovyString="data.findAll{it.employee_age=="+ageList.get(0)+"}.employee_name";
        assertEquals("[Tatyana Fitzpatrick]",json.getString(groovyString));
        //Total salary of all employees is 6,644,770
        List<Integer> salaryList=json.getList("data.findAll{it.id>0}.employee_salary");
        System.out.println(salaryList);
        //1. way to calculate total salary
        int totalSalary=0;
        for(Integer w:salaryList){
            totalSalary+=w;
        }
        System.out.println(totalSalary);
        assertEquals(6644770,totalSalary);
        //2. way to calculate total salary
        int totalSalaryWithLambda=salaryList.stream().reduce(0,(x,y)->x+y);
        assertEquals(6644770,totalSalaryWithLambda);
        //3. way to calculate total salary-->best way
        int totalSalaryWithLambdaMethodReference=salaryList.stream().reduce(0,Math::addExact);
        assertEquals(6644770,totalSalaryWithLambdaMethodReference);
    }
}
