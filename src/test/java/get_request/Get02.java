package get_request;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Get02 {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */
    @Test
    public void get01(){
        //1. step: set the url
        String url="https://restful-booker.herokuapp.com/booking/1005";

        //2.step: set the expected data(post,put,patch)(çünkü burada body'e ihtiyacimiz var bunlari body'nin içine
        // koyacağiz. Şuan get yapacağimiz için ihtiyacimiz yok)

        //3.step: Type code to send request
        Response response;
        try {
            response=given().when().get(url);
            response.prettyPrint();
            // 4. Step :Do Assert
            response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
            // Respon body de spesifik bir veri nasıl assert edilir
            // assertTrue methodu parentesin içi tru ise assertTrue olması beklenir
            // assertFalse ise false doneceğimizi düşündüğümüz için assertFalse kullanılıır
            Assert.assertTrue(response.asString().contains("Not Found"));
            // response body ın  spessifik bir veri bulunmadığını nasıl test edilir
            Assert.assertFalse(response.asString().contains("TechProEd"));
            System.out.println(response.header("Server"));
            Assert.assertEquals("Cowboy",response.header("Server"));  //bu sekilde de olur
        }
        catch(Exception e) {
            e.printStackTrace();
        }



    }
}
