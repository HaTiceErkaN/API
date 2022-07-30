package test;

import base_Urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_Deserialization extends JsonPlaceHolderBaseUrl {
    @Test
    public void test01(){
        //1.ADIM: Url ve request body olustur.
        spec.pathParams("first","posts","second",70);
        JsonPlaceHolderTestData jphTestData = new JsonPlaceHolderTestData();
        Map<String ,Object> requestBody =jphTestData.requestBodyMapOlustur();

        //2.ADIM: Expected Data olustur
        Map<String ,Object> expectedDataMap =jphTestData.requestBodyMapOlustur();

        //3.ADIM: Response'u kaydet
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec).
                when().
                body(requestBody).
                put("{first}/{second}");

        //4.ADIM: Do Assertion
        assertEquals(jphTestData.basariliStatusCode,response.getStatusCode());

        Map<String ,Object> responseBodyMap = response.as(HashMap.class);
        assertEquals(expectedDataMap.get("title"),responseBodyMap.get("title"));
        assertEquals(expectedDataMap.get("body"),responseBodyMap.get("body"));
        assertEquals(expectedDataMap.get("userId"),responseBodyMap.get("userId"));
        assertEquals(expectedDataMap.get("id"),responseBodyMap.get("id"));
    }
 }
