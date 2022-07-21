package get_request;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest {
    /*
    https://jsonplaceholder.typicode.com/posts/44 url'ine bir GET request yolladigimizda donen Response'un:

    tatus Code=200
    ontent type=JSON
    esponse body'de bulunan userId=5,
    esponse body'de bulunan title="optio dolor molestias sit"
                                       oldugunu test edin.
    */

   //Api sorgusundaki en temel iki on kosul : Request url'ini ve body'sini ousturmak.
    //Preconditions olarak request url ve body ousturulmali
    //Ancak burada bir get request(sorgu) islemi yaptigimiz icin body olusturmamiza gerek yok

@Test
        public void get01(){
    //1-Request url ve body olustur
    String url="https://jsonplaceholder.typicode.com/posts/44";

    //2-Expected data olustur. (Burada bir suru farkli format kullanilabilir ama biz bugun Json object uzerinden obje
    // olusturarak yapacagiz)
    JSONObject expBody= new JSONObject();
    expBody.put("userId",5);
    expBody.put("title","optio dolor molestias sit");

    //3-Response'u kaydet
    Response response = given().when().get(url);

    //4- Do Asertion (expected data ile actual data ayni mi?)
    response.
            then().
            assertThat().
            statusCode(200).
            contentType(ContentType.JSON);
   // Response'u direk olarak karsilastirmaya uygun formatta hazirlamiyor.Response'u JsonPath olarak kaydetmemiz gerekiyor.
    JsonPath actBody= response.jsonPath();
    Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));
    Assert.assertEquals(expBody.get("title"),actBody.get("title"));

}
}
