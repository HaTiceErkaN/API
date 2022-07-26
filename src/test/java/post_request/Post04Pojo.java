package post_request;

import base_Urls.HerokuAppBaseUrl;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends HerokuAppBaseUrl {
     /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
          I send POST Request to the URL
       Then
          Status code is 200
      And
          Response body is like {
                                  "bookingid": 16,
                                  "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */

    @Test
    public void PostPojo01(){
        //1. Step: Set the Url
        spec.pathParam("first", "booking");

        //2. Step: Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo bookingPojo = new BookingPojo("Ali","Can",999, true,bookingDates,"Breakfast with white tea, Dragon juice");

        //3. Step: Send POST Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        BookingResponseBodyPojo actualPojo = response.as(BookingResponseBodyPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(bookingPojo.getFirstname(),actualPojo.getBooking().getFirstname());
        assertEquals(bookingPojo.getLastname(), actualPojo.getBooking().getLastname());
        assertEquals(bookingPojo.getTotalprice(), actualPojo.getBooking().getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(), actualPojo.getBooking().getDepositpaid());

        //1. Yol
        assertEquals(bookingPojo.getBookingDates().getCheckin(), actualPojo.getBooking().getBookingDates().getCheckin());
        assertEquals(bookingPojo.getBookingDates().getCheckout(), actualPojo.getBooking().getBookingDates().getCheckout());

        //2. Yol
        assertEquals(bookingDates.getCheckin(), actualPojo.getBooking().getBookingDates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualPojo.getBooking().getBookingDates().getCheckout());
        assertEquals(bookingPojo.getAdditionalneeds(), actualPojo.getBooking().getAdditionalneeds());
    }
}