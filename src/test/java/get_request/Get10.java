package get_request;

import base_Urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.GorestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Get10 extends GorestBaseUrl {

   /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                {
                 https://gorest.co.in/public/v1/users/2965
        "meta": null,
        "data": {
        "id": 2965,
        "name": "Mr. Gita Menon",
        "email": "gita_menon_mr@bayer.com",
        "gender": "female",
        "status": "inactive"
                            }
                }
     */


    @Test
    public void get01(){

        //1. Step: Set the Url
        spec.pathParams("first","users","second",2965);

        //2. Step: Set the expected data
        GorestTestData dataKey = new GorestTestData();
        Map<String , String> dataKeyMap = dataKey.dataKeyMap("Mr. Gita Menon", "gita_menon_mr@bayer.com","female","inactive");
        Map<String , Object> expectedData = dataKey.expectedDataMap(null,dataKeyMap);


        //3. Step: Send the request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        Map<String, Object> actualDataMap = response.as(HashMap.class); //deserialization

        //4.STEP: Do Assrtion

        Assert.assertEquals(expectedData.get("meta"),actualDataMap.get("meta"));
        Assert.assertEquals(dataKeyMap.get("name"),((Map)actualDataMap.get("data")).get("name"));
        Assert.assertEquals(dataKeyMap.get("email"),((Map)actualDataMap.get("data")).get("email"));
        Assert.assertEquals(dataKeyMap.get("gender"),((Map)actualDataMap.get("data")).get("gender"));
        Assert.assertEquals(dataKeyMap.get("status"),((Map)actualDataMap.get("data")).get("status"));
        //Actual data kismi Object, Map<String,Object>, goruldugu uzere 2. kisim hep object. o yuzden bu map olmadigi
        // icin casting yapiyoruz.


    }
}
