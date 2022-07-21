package test;

import base_Urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_BaseURL_TestDataKullanimi extends JsonPlaceHolderBaseUrl {

    /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine bir Get Request yolladigimizda donen response'un;
    StatusCode:200,
    Response Body:
    {
    "userId":3,
    "id":22,
    "title": "dolor sint quo a velit explicabo quia nam",
    "body": "eos qui et ipsum ipsam suscipit aut
sed omnis non odio
expedita earum mollitia molestiae aut atque rem suscipit
nam impedit esse"
    }
    oldugunu test et
     */

    @Test
    public void test01(){
        //1.ADİM: URL ve Request body oluştur
        spec.pathParams("first","posts","second",22);

        //2.ADİM: Expected data olustur
        JsonPlaceHolderTestData jsonPlaceHolder = new JsonPlaceHolderTestData();
       JSONObject expBody= jsonPlaceHolder.expectedDataOlustur();

       //3.ADİM: Response'u kaydet
        Response response = given().spec(spec).when().get("{first}/{second}");
        System.out.println(response);

        //4.ADİM: Do Assert

        JsonPath respJSPath = response.jsonPath();
        assertEquals(jsonPlaceHolder.basariliStatusCode,response.getStatusCode());

        assertEquals(expBody.getInt("userId"),respJSPath.getInt("userId"));
        assertEquals(expBody.getString("title"),respJSPath.getString("title"));
        assertEquals(expBody.getString("body"),respJSPath.getString("body"));
    }
}
