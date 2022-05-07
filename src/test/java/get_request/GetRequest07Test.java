package get_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest07Test extends JsonPlaceHolderBaseUrl {

    /*
    Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds less than 5 on the console
			   Assert that maximum userId less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that “delectus aut autem” is one of the titles whose id is less than 5
     */


    @Test

    public void get07(){

        //1) Set the URL
        spec.pathParam("todosPath","todos");
        //2) Set the expected the data


        //3) Type the code to send request and Get the response
        Response response=given().
                               spec(spec).
                               accept(ContentType.JSON).
                           when().
                               get("/{todosPath}");

        response.prettyPrint();

        //4) Do assertion
        //1)Status code is 200
        response.then().assertThat().statusCode(200);

        //2)Print all ids greater than 190 on the console

        JsonPath json=response.jsonPath();
        //System.out.println(json);
        List<Integer> ids=json.getList("findAll{it.id>190}.id");//Groovy Language:Java base Programming Language
        System.out.println(ids);
        //Assert that there are 10 ids greater than 190
        assertEquals("Number of ids did not match",10, ids.size());

        //3)Print all userIds whose ids less than 5 on the console
        List<Integer> userIds=json.getList("findAll{it.id<5}.userId");
        System.out.println(userIds);
        //Assert that number of userId whose ids are less than 5 is 4
        Collections.sort(userIds);
        assertEquals("the number of userId whose ids are less than 5 is not 4",4,userIds.size());
        //4)Print all titles whose ids are less than 5
        List<String> titles=json.getList("findAll{it.id<5}.title");
        System.out.println(titles);
        //Assert that “delectus aut autem” is one of the titles whose id is less than 5
        //1. way
        assertTrue("Expected title is not among them",titles.contains("delectus aut autem"));

        //2. way
        assertTrue("Expected title is not among them",titles.stream().anyMatch(t->t.equals("delectus aut autem")));



    }
}
