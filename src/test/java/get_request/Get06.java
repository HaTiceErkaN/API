package get_request;

import base_Urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class Get06 extends HerokuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/101
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            "firstname": "GGS",
            "lastname": "FINCH",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            }

        }
     */
    @Test
    public void get01() {

        //1.ADİM Set the url
        spec.pathParams("first","booking","second",101);

        //2.ADİM: Set the expected data

        //3.ADİM: Set the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.ADİM: Do Assertion

        //******1.YOL******

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname",equalTo("Sally"),
                        "lastname",equalTo("Brown"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2013-02-23"),
                        "bookingdates.checkout",equalTo("2014-10-23"));

        //******2.YOL******  JasonPath Class kullanilir

        //obje olusturcaz

        JsonPath json = response.jsonPath();
       assertEquals("Sally",json.getString("firstname"));
       assertEquals("Brown",json.getString("lastname"));
       assertEquals(111,json.getInt("totalprice"));
       assertEquals(true,json.getBoolean("depositpaid"));
       assertEquals("2013-02-23",json.getString("bookingdates.checkin"));
       assertEquals("2014-10-23",json.getString("bookingdates.checkout"));

        //******3.YOL******  SoftAssert  kullanilir

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("firstname"),"slly","first name uyusmadi");
        softAssert.assertEquals(json.getString("lastname"),"Brown","last name uyusmadi");
        softAssert.assertEquals(json.getInt("totalprice"),121,"totalPrice uyusmadi");
        softAssert.assertEquals(json.getBoolean("depositpaid"),true,"depositpaid uyusmadi");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2013-02-23","checkin uyusmadi");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2014-10-23","checkout uyusmadi");
        softAssert.assertAll();




    }
}