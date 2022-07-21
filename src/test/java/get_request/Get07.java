package get_request;

import base_Urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class Get07 extends JsonPlaceHolderBaseUrl {
 /*
        Given
              https://jsonplaceholder.typicode.com/todos
      When
          I send GET Request to the URL
      Then
          1)Status code is 200
          2)Print all ids greater than 190 on the console
            Assert that there are 10 ids greater than 190
          3)Print all userIds whose ids are less than 5 on the console
            Assert that the number of userIds whose ids are less than 5 is 4
          4)Print all titles whose ids are less than 5
            Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParam("first","todos");
        //2.ADİM set the expected data
        //3.ADİM set the request and get the response

        Response response = given().spec(spec).when().get("/{first}");
       // response.prettyPrint();

        //4.ADİM: Do Assertion
        response.then().assertThat().statusCode(200);

        //Print all ids greater than 190 on the console
        JsonPath json=response.jsonPath();
        List<Integer> ids =json.getList("findAll{it.id>190}.id");   //Groovy Language = Java temelli bir programlama dili
        System.out.println(ids);

        // Assert that there are 10 ids greater than 190
        Assert.assertEquals(10,ids.size());

        //Print all userIds whose ids are less than 5 on the console
        List<Integer>ids2=json.getList("findAll{it.id<5}.userId");
        System.out.println(ids2);

        // Assert that the number of userIds whose ids are less than 5 is 4
        Assert.assertEquals(4,ids2.size());

        //Print all titles whose ids are less than 5
        List<String >titles=json.getList("findAll{it.id<5}.title");
        System.out.println(titles);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        Assert.assertTrue(titles.contains("delectus aut autem"));

    }

}
