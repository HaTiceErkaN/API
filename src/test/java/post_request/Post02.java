package post_request;

import base_Urls.HerokuAppBaseUrl;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Post02 extends HerokuAppBaseUrl {
     /*
        Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */

    @Test
    public void post01(){
        //1.Adım set the url
        spec.pathParam("first","booking");

        //2.Adim Set the expected data and get the response
        HerokuAppTestData herokuAppTestData = new HerokuAppTestData();
       Map<String,String > bookingDates= herokuAppTestData.bookingDatesSetUp("2021-09-09","2021-09-21");
       Map<String ,Object> expectedData = herokuAppTestData.expectedDataSetUp("John","Doe",11111,true,bookingDates);

       //3.Adim Send the post request and get the response
      Response response=  given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
      response.prettyPrint();

      //4.Adim Do Assert
        //İki map'i karsilastiricaz ama burda response hala json formatta. Burda de-serialization yapacagiz

        assertEquals(200,response.getStatusCode());
        Map<String ,Object> actualDataMap = response.as(HashMap.class);
        assertEquals(expectedData.get("firstname"),((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(bookingDates.get("checkin"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDates.get("checkout"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }
}
