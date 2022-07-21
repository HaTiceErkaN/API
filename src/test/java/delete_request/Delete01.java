package delete_request;

import base_Urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01(){
        //1.adim set the url
        spec.pathParams("first","todos","second",198);

        //2.adim Set the expected data

        Map<String ,Object> expectedDataMap=new HashMap<>();

        //3.adim send the request and get the response

       Response response= given().spec(spec).when().delete("/{first}/{second}");
       response.prettyPrint();

       //4.adim Do Assertion
        response.then().assertThat().statusCode(200);

        Map<String ,Object> actualDataMap = response.as(HashMap.class);
        assertEquals(expectedDataMap,actualDataMap);

        //2. Yol
        assertTrue(actualDataMap.size()==0);
        assertTrue(actualDataMap.isEmpty());//Tavsiye edilen

        //Delete request yapmadan önce "Post Request" yapıp o datayı silmeliyiz.


    }
}
