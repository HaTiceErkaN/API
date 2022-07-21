package get_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
        Postman manuel Api testi için kullanilir
        Otomasyon için, Rest-Assured Library kullaniyoruz.
        Otomasyon kodlarinin yazimi için şu adimlari izliyoruz:

        a) Gereksinimleri anlamak (understanding requirement)
        b) Test case'i yazma
            i)Test Case yazimi inin 'Gherkin Language' kullaniyoruz
                - Gherkin bazi keywordlere sahip, bunlar:
                    -Given: Ön kosullar
                    -When: Aksyonlar -->Get, Put vs..
                    -Then: Ciktilar/Donutler --> As sert, response...
                    -And: Coklu islemler icin
        c)Testing kodunun yazimi
            i)Set the URL
            ii)Set the expected Data(Post-Put-Patch)
            iii) Type code to send request
            iiii)Do Assertion  (doğrulama yapma)

     */

     /*
        Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
          */

        @Test
        public void get01(){
        // i)Set the URL
            String url= " https://restful-booker.herokuapp.com/booking/1850";

        // ii)Set the expected Data(Post-Put-Patch)
                //**** Get request yapacagimiz için bu adimi atladik

        // iii) Type code to send request
         Response response = given().when().get(url);   //bu bize data retur'u yapacak. Jason formatinda data getirecek
         response.prettyPrint();

        // iiii)Do Assertion  (doğrulama yapma)

            response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

            //status code nasil yazdirilir?
            System.out.println("Status Code: "+response.statusCode());

            //contentType nasil yazdirilir?
            System.out.println("contentType: "+response.contentType());

            //statusLine nasil yazdirilir?
            System.out.println("statusLine: "+response.statusLine());


            //Header nasil yazirilir?
          //  System.out.println(response.header("Connection"));  //spesific olarak ne istedigini yazdirir
            System.out.println("Headers: \n "+response.headers());  //tum headers'i yazdirir

            //Time nasil yazdiirlir?
            System.out.println("Get Time: "+response.getTime());


        }

}
