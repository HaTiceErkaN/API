package get_request;

import base_Urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get15ObjectMapper extends HerokuAppBaseUrl {

     /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
                "firstname": "Oliver",
                "lastname": "Smith",
                "totalprice": 100,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-07-18",
                    "checkout": "2022-07-19"
                },
                "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void get01(){
        //1.adim set the url:

        spec.pathParams("first","booking","second",22);

        //2.adim send expected data

        String expectedData ="{\n" +
                "                \"firstname\": \"Oliver\",\n" +
                "                \"lastname\": \"Smith\",\n" +
                "                \"totalprice\": 100,\n" +
                "                \"depositpaid\": true,\n" +
                "                \"bookingdates\": {\n" +
                "                    \"checkin\": \"2022-07-18\",\n" +
                "                    \"checkout\": \"2022-07-19\"\n" +
                "                },\n" +
                "                \"additionalneeds\": \"Breakfast\"\n" +
                "            }";

      BookingPojo expectedDataPojo=  JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);

      //3.adim send the get request and get the response
      Response response= given().spec(spec).when().get("/{first}/{second}");

      //4.adim Do Assertion
        //Hard Assertion
        BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
        assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());
        assertEquals(expectedDataPojo.getBookingDates().getCheckin(),actualDataPojo.getBookingDates().getCheckin());
        assertEquals(expectedDataPojo.getBookingDates().getCheckout(),actualDataPojo.getBookingDates().getCheckout());

        //SoftAssertion
        //1. Adım: SoftAssert objesi oluştur
        SoftAssert softAssert = new SoftAssert();

        //2. Adım: Assertion Yap

        softAssert.assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname(),"Firstname uyusmadi");
        softAssert.assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname(),"Lastname uyusmadi");
        softAssert.assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice(),"Total Price uyusmadi");
        softAssert.assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid(),"Deposidpaid uyusmadi");;
        softAssert.assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds(),"Additionalneeds uyusmadi");
        softAssert. assertEquals(expectedDataPojo.getBookingDates().getCheckin(),actualDataPojo.getBookingDates().getCheckin(),"Checkin uyusmadi");
        softAssert. assertEquals(expectedDataPojo.getBookingDates().getCheckout(),actualDataPojo.getBookingDates().getCheckout(),"Checkout uyusmadi");

        //3. Adım: assertAll() methodunu çalıştır.
        softAssert.assertAll();

    }
}
