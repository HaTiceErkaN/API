package get_request;

import base_Urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GorestDataPojo;
import pojos.GorestResponsePojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GorestBaseUrl {
     /*
        Given
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And https://gorest.co.in/public/v1/users/2508
            Response body should be like
           {
                "meta": null,
                "data": {
                    "id": 2508,
                    "name": "Akshita Nehru",
                    "email": "nehru_akshita@jast.info",
                    "gender": "female",
                    "status": "active"
                }
            }
    */
    @Test
    public void get01(){
        //1.adim set the url
        spec.pathParams("first","users","second",2508);

        //2.adim Set the expected Data
        GorestDataPojo data = new GorestDataPojo(2508,"Akshita Nehru","nehru_akshita@jast.info","female","active");
        GorestResponsePojo expectedData= new GorestResponsePojo(null,data);

        //3.adim send the request and get the response
        Response response =given().spec(spec).when().get("/{first}/{second}");

        //4.Adim Do Assert
        GorestResponsePojo actualData= response.as(GorestResponsePojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        assertEquals(expectedData.getData().getName(),actualData.getData().getName());
        assertEquals(expectedData.getData().getEmail(),actualData.getData().getEmail());
        assertEquals(expectedData.getData().getGender(),actualData.getData().getGender());
        assertEquals(expectedData.getData().getStatus(),actualData.getData().getStatus());

        assertEquals(expectedData.getMeta(),actualData.getMeta());


    }
}
