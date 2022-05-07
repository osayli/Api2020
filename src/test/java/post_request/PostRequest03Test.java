package post_request;

   /*
   Given
		"http://api.agromonitoring.com/agro/1.0/polygons?appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0&duplicated=true"
            {
               "name":"Polygon Sample",
               "geo_json":{
                  "type":"Feature",
                  "properties":{},
                  "geometry":{
                     "type":"Polygon",
                     "coordinates":[
                        [
                           [-121.1958,37.6683],
                           [-121.1779,37.6687],
                           [-121.1773,37.6792],
                           [-121.1958,37.6792],
                           [-121.1958,37.6683]
                        ]
                     ]
                  }
               }
            }
	When
		 I send POST Request to the Url
	Then
		Assert Status Code (201)
		And Response Body should be like {
										    "id": "5fd8c383714b523b2ce1f154",
										    "geo_json": {
										        "geometry": {
										            "coordinates": [
										                [
										                    [
										                        -121.1958,
										                        37.6683
										                    ],
										                    [
										                        -121.1779,
										                        37.6687
										                    ],
										                    [
										                        -121.1773,
										                        37.6792
										                    ],
										                    [
										                        -121.1958,
										                        37.6792
										                    ],
										                    [
										                        -121.1958,
										                        37.6683
										                    ]
										                ]
										            ],
										            "type": "Polygon"
										        },
										        "type": "Feature",
										        "properties": {
										        }
										    },
										    "name": "Polygon Sample",
										    "center": [
										        -121.1867,
										        37.67385
										    ],
										    "area": 190.9484,
										    "user_id": "5fd8c02a3da20c000759e0f8",
										    "created_at": 1608041347
										}
    */

import base_urls.AgroMonitoringBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AgroMonitoringTestData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class PostRequest03Test extends AgroMonitoringBaseUrl {


    @Test
    public void post03(){

        //1) Set the URL
        spec.pathParams("first","agro","second",1.0,"third","polygons").
                queryParams("appid","f4ffe3b2ef1fcb3600ab1d7fbc88c2f0","duplicated",true);

        //2) Set the expected the data(Set the request body or payload for post request)
        AgroMonitoringTestData requestBodyObj=new AgroMonitoringTestData();
        Map<String,Object> requestBodyMap=requestBodyObj.requestBody();
        System.out.println(requestBodyMap);

        //3) Type the code to send request and Get the response
        Response response=given().
                spec(spec).
                contentType(ContentType.JSON).
                body(requestBodyMap).
                when().
                post("/{first}/{second}/{third}");

        //response.prettyPrint();

        //4) Do assertion
        //1.way :using Map

        Map<String,Object> actualDataMap=response.as(HashMap.class);
        assertEquals(requestBodyMap.get("name"),actualDataMap.get("name"));
        assertEquals(String.valueOf(requestBodyObj.center[0]),actualDataMap.get("center").toString().substring(1,10));
        assertEquals(String.valueOf(requestBodyObj.center[1]),actualDataMap.get("center").toString().substring(12,20));
        assertEquals(requestBodyMap.get("area"),actualDataMap.get("area"));
        assertEquals(String.valueOf(requestBodyObj.coordinates[0][0][0]),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(3,12));
        assertEquals(String.valueOf(requestBodyObj.coordinates[0][0][1]),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(14,21));
        assertEquals(String.valueOf(requestBodyObj.coordinates[0][1][0]),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(25,34));
        assertEquals(String.valueOf(requestBodyObj.coordinates[0][1][1]),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(36,43));
        assertEquals(String.valueOf(requestBodyObj.coordinates[0][2][0]),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(47,56));
        assertEquals(String.valueOf(requestBodyObj.coordinates[0][2][1]),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(58,65));
        assertEquals(String.valueOf(requestBodyObj.coordinates[0][3][0]),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(69,78));
        assertEquals(String.valueOf(requestBodyObj.coordinates[0][3][1]),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(80,87));
        assertEquals(String.valueOf(requestBodyObj.coordinates[0][4][0]),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(91,100));
        assertEquals(String.valueOf(requestBodyObj.coordinates[0][4][1]),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("coordinates").toString().substring(102,109));

        assertEquals(requestBodyObj.geometrySetUp().get("type"),((Map)((Map)actualDataMap.get("geo_json")).get("geometry")).get("type"));

        //2.way: Json Path
        JsonPath json=response.jsonPath();
        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][0][0]")==requestBodyObj.coordinates[0][0][0]);
        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][0][1]")==requestBodyObj.coordinates[0][0][1]);
        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][1][0]")==requestBodyObj.coordinates[0][1][0]);
        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][1][1]")==requestBodyObj.coordinates[0][1][1]);
        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][2][0]")==requestBodyObj.coordinates[0][2][0]);
        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][2][1]")==requestBodyObj.coordinates[0][2][1]);
        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][3][0]")==requestBodyObj.coordinates[0][3][0]);
        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][3][1]")==requestBodyObj.coordinates[0][3][1]);
        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][4][0]")==requestBodyObj.coordinates[0][4][0]);
        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][4][1]")==requestBodyObj.coordinates[0][4][1]);

        assertTrue(json.getString("geo_json.geometry.type").equals(requestBodyObj.geometrySetUp().get("type")));
        assertTrue(json.getString("geo_json.type").equals(requestBodyObj.geo_jsonSetUp().get("type")));
        assertTrue(json.getJsonObject("geo_json.properties").equals(requestBodyObj.geo_jsonSetUp().get("properties")));
        assertTrue(json.getString("geo_json.type").equals(requestBodyObj.geo_jsonSetUp().get("type")));
        assertTrue(json.getString("name").equals(requestBodyObj.requestBody().get("name")));

        assertTrue(json.getFloat("center[0]")==requestBodyObj.center[0]);
        assertTrue(json.getFloat("center[1]")==requestBodyObj.center[1]);

        //To assert "area" value you can use both of the followings
        assertTrue(json.get("area").toString().equals(requestBodyMap.get("area").toString()));
        assertTrue(json.getDouble("area")==(Double)requestBodyMap.get("area"));

    }
}
    //Can we put expected data in json format instead of Map?