package patch_request;

import base_Urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */
    @Test
    public void patch01(){

        //1.adim set the url
        spec.pathParams("first","todos","second",198);

        //2.adim set the request body (expected data)
        JsonPlaceHolderTestData requestBody= new JsonPlaceHolderTestData();
         Map<String ,Object> requestBodyMap=  requestBody.expectedDatawithMissingKeys(null,"Wash the dishes",null);

         //3.step send the patch request and get the response
      Response response= given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
        response.prettyPrint();

        //4.adim Do Assert
       Map<String ,Object> actualMap= requestBody.expectedDataWithAllKeys(10,"Wash the dishes",true);

       response.
               then().
               assertThat().
               statusCode(200).
               body(
                       "title",equalTo(actualMap.get("title")),
                       "userId",equalTo(actualMap.get("userId")),
                       "completed",equalTo(actualMap.get("completed")));

    }

    private void assetEquals() {
    }
}
