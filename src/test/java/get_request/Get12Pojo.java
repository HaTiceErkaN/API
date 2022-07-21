package get_request;

import base_Urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get12Pojo extends HerokuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/2
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                    "firstname": "Mary",
                                    "lastname": "Brown",
                                    "totalprice": 227,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2015-10-21",
                                        "checkout": "2021-08-28"
                                    },
                                    "additionalneeds": "Breakfast"
                                 }
     */

    @Test
    public void get01(){
        //1.adim set the url
        spec.pathParams("first","booking","second",18);

        //2.adim set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2015-10-21","2021-08-28");
        BookingPojo expectedData = new BookingPojo("Mary","Brown",227,true,bookingDates,"Breakfast");

        //3.adim Send the requestd and get the response
        Response response= given().spec(spec).when().get("/{first}/{second}");

        //4.adim Do Asssert
        BookingPojo actualData=response.as(BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
        assertEquals(expectedData.getBookingDates().getCheckin(),actualData.getBookingDates().getCheckin());
        assertEquals(expectedData.getBookingDates().getCheckout(),actualData.getBookingDates().getCheckout());



    }
}
