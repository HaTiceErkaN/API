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

        //1. step set the url
        spec.pathParam("first","booking");

        //2. step set the expected data
        BookingDatesPojo bookingDates =new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo bookingPojo = new BookingPojo("Ali","Can",999,true,bookingDates,"Breakfast with white tea, Dragon juice");

        //3.step send post and get response
        Response response =given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{first}");

        //4.step Do Assert
       BookingResponseBodyPojo actualData= response.as(BookingResponseBodyPojo.class);
       assertEquals(200,response.getStatusCode());
       assertEquals(bookingPojo.getFirstname(),actualData.getBooking().getFirstname());
       assertEquals(bookingPojo.getLastname(),actualData.getBooking().getLastname());
       assertEquals(bookingPojo.getTotalprice(),actualData.getBooking().getTotalprice());
       assertEquals(bookingPojo.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());
       assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingDates().getCheckin());
       assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingDates().getCheckout());



    }
}
