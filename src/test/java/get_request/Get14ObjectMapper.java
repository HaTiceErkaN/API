package get_request;

import base_Urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get14ObjectMapper extends JsonPlaceHolderBaseUrl {

/*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void get01ObjectMapper(){
        //1.adim set the url
        spec.pathParams("first","todos","second",198);

        //2.adim set the expected data (objectMapper kullanacagiz)
        String expectedData ="\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true";
      HashMap<String ,Object> expectedDataMap= JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class); //(De-Serialization)
      //normalde obje olusturmamiz gerekirdi ama bu JsonUtil icindeki methodlar static oldugundan direk cagirabiliyoruz


        //3.adim send the get request and get the response

      Response response= given().spec(spec).when().get("/{first}/{second}");

      //4.adim Do Assertion
        assertEquals(200,response.getStatusCode());

       HashMap<String ,Object> actualDataMap= JsonUtil.convertJsonToJavaObject(response.asString(),HashMap.class);
       assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
       assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
       assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));

    }

    @Test
    public void get02ObjectMapper(){

        //1.adim set the url
        spec.pathParams("first","todos","second",198);

        //2.adim set the expected data (objectMapper kullanacagiz)
        String expectedData ="\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true";
        JsonPlaceHolderPojo  expectedDataPojo= JsonUtil.convertJsonToJavaObject(expectedData, JsonPlaceHolderPojo.class);
        //Bu sefer de pojo class olarak yaptik pojo'ya cevirdik

        //3.adim send the get request and get the response

        Response response= given().spec(spec).when().get("/{first}/{second}");

        //4.adim Do Assertion
        JsonPlaceHolderPojo actualDataPojo= JsonUtil.convertJsonToJavaObject(response.asString(),JsonPlaceHolderPojo.class);
        assertEquals(200,response.getStatusCode());

        assertEquals(expectedDataPojo.getUserId(),actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(),actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(),actualDataPojo.getCompleted());




    }
}
