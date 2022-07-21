package post_request;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class POSTREQUEST {
    /*
    https://jsonplaceholder.typicode.com/posts url'ine asagidaki body ile bir post gonderelim
    {
    "title":"API",
    "body":"API ogrenmek ne guzel",
    "userId":10;
    }
    donen response'un
    Status code: 201
    Content Type: application/json
    Response Body'nin:
    {
    "title":"API",
    "body":"API ogrenmek ne guzel",
    "userId":10;
        }
        oldugunu test edelim
    */
    @Test
        public void post01(){
        //1-Oncelikle preconditions olarak request url ve body’yi oluşturuyoruz
        String url= "https://jsonplaceholder.typicode.com/posts";
        JSONObject reqBody = new JSONObject();
        reqBody.put("title","API");
        reqBody.put("body","API ogrenmek ne guzel");
        reqBody.put("userId",10);
        //Burada olusturdugumuz post ile yapacagimiz islem icin body icine girdigimiz bilgiler.

        //2- Expected data olusturuyoruz
        JSONObject expBody = new JSONObject();
        expBody.put("title","API");
        expBody.put("body","API ogrenmek ne guzel");
        expBody.put("userId",10);
        //Burada yaptigimiz ise post olusturduktan sonra bekledigimiz response icindeki bilgilerin ne olacagi.


        //3-Response kaydet
        Response response =given().
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                post(url);
        //body'i yukarda olusturduk ama gondermedik. Burda body bilgilerini giriyoruz ancak Burada request body'miz bir
        // json obje olarak gonderiliyor ancak body() methodu data tipi olarak   string istiyor.Bu nedenle toString()
        // methodu ile datamizi string'e ceviriyoruz

        JsonPath actBody= response.jsonPath(); //burada response'umuzu jsonpath formatinda kaydediyoruz.

        //4-Do Assertion
        response.
                then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(201);

        Assert.assertEquals(expBody.get("title"),actBody.get("title"));
        Assert.assertEquals(expBody.get("body"),actBody.get("body"));
        Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));
    }
}
