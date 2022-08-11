package post_request;

import base_Urls.DummyRestapiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post05 extends DummyRestapiBaseUrl {

     /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body:
       Test Case: Type by using Gherkin Language
       Assert:
                    {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                     }
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    /*
    Given
    https://dummy.restapiexample.com/api/v1/create adresine gidilir
    {
            "employee_name": "Tom Hanks",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image",
            "id": 4891
     }
     When
     User send the post request
     Then
     Status code is 200
     And
     response body :
     {
          "status": "success",
          "data": {
              "employee_name": "Tom Hanks",
              "employee_salary": 111111,
              "employee_age": 23,
              "profile_image": "Perfect image",
              "id": 4891
          },
          "message": "Successfully! Record has been added."
      }

     */


    @Test
    public void post01(){

        //1. set the url
        spec.pathParam("first","create");

        //2.adim set the expected data and
        DummyApiDataPojo dummyApiPojo= new DummyApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyApiResponseBodyPojo expectedDataPojo=new DummyApiResponseBodyPojo("success",dummyApiPojo,"Successfully! Record has been added.");

        //3.adim send the post request and get the response

       Response response= given().spec(spec).contentType(ContentType.JSON).body(dummyApiPojo).when().post("/{first}");
       response.prettyPrint();

       //4.adim Do Assert

       DummyApiResponseBodyPojo actualData= JsonUtil.convertJsonToJavaObject(response.asString(),DummyApiResponseBodyPojo.class);


        assertEquals(actualData.getStatus(),expectedDataPojo.getStatus());
        assertEquals(actualData.getMessage(),expectedDataPojo.getMessage());

        assertEquals(actualData.getData().getProfile_image(),expectedDataPojo.getData().getProfile_image());
        assertEquals(actualData.getData().getEmployee_age(),expectedDataPojo.getData().getEmployee_age());
        assertEquals(actualData.getData().getEmployee_salary(),expectedDataPojo.getData().getEmployee_salary());
        assertEquals(actualData.getData().getEmployee_name(),expectedDataPojo.getData().getEmployee_name());



    }
}
