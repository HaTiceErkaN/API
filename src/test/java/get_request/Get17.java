package get_request;

import base_Urls.DummyRestapiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;

public class Get17 extends DummyRestapiBaseUrl {

    /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */

    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."

     */

    @Test
    public void get01() {
        spec.pathParams("first", "employee", "second", "1");

        DummyApiDataPojo dummydata = new DummyApiDataPojo("Tiger Nixon", 320800, 61, "");
        DummyApiResponseBodyPojo responseBodyPojo = new DummyApiResponseBodyPojo("success", dummydata, "Successfully! Record has been fetched.");

        Response response= given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        DummyApiResponseBodyPojo responseBody= JsonUtil.convertJsonToJavaObject(response.asString(),DummyApiResponseBodyPojo.class);
        System.out.println(responseBody);
        /*
        Burada
        ResponseBody{status='success', data=DummyApiDataPojo{employee_name='Tiger Nixon', employee_salary=320800, employee_age=61, profile_image=''},
        message='Successfully! Record has been fetched.'}
        bu sekilde yazdirmasinin sebebi responseBodyPojo class'taki toString methodu. O olmadan yazdirsaydik objenin hash kodunu yazdirridi.
         */

    }
}
