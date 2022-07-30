package test;

import base_Urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class API_PostRequestWithPojo extends HerokuAppBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking
    request body{
                   "firstname": "Ahmet",
                   "lastname": "Bulut",
                   "totalprice": 15000,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2022-09-09",
                       "checkout": "2022-09-21"
               },
        Status Code:200,
        response body{
                     "bookingid": 11,
                     "booking": {
                                    "firstname": "Ahmet",
                                    "lastname": "Bulut",
                                    "totalprice": 15000,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2022-09-09",
                                        "checkout": "2022-09-21"
                                                                 }
                                   "additionalneeds": "Breakfast"
                                                                     }
                                                                        }
     */
    @Test
    public void test01(){
        //1.adim: set the url and request body
        spec.pathParam("first","booking");

        //2.adim send the expectedData
        BookingDatesPojo bookingDates = new BookingDatesPojo("2022-09-09","2022-09-21");

        BookingPojo booking = new BookingPojo("Ahmet","Bulut",15000,true,bookingDates,"Breakfast");

        BookingResponseBodyPojo bookingResponse= new BookingResponseBodyPojo(11,booking);

        //3.adim send the post request and get the response
        Response response= given().contentType(ContentType.JSON).spec(spec).when().body(bookingResponse).post("/{first}");
        System.out.println(response);

        //4.adim Do Assertion

        BookingResponseBodyPojo actualData = response.as(BookingResponseBodyPojo.class);
        assertEquals(200,response.getStatusCode());

        assertEquals(booking.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(booking.getLastname(),actualData.getBooking().getLastname());
        assertEquals(booking.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(booking.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(booking.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

        assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingDates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingDates().getCheckout());
    }
}
