package get_request;

import base_Urls.DummyRestapiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16ObjectMapper extends DummyRestapiBaseUrl {


     /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language

           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */


    @Test
    public void get(){
        //1.adim set the url:
        spec.pathParam("first","employees");

        //2.adim set the expected data

        //3.adim send the get request and get the response
       Response response= given().spec(spec).when().get("/{first}");
       response.prettyPrint();

       //4.adim Do Assertion
       response.then().
               assertThat().
               statusCode(200). //Status code is 200
               body("data.id",hasSize(24),
               "data.employee_name",hasItems("Tiger Nixon","Garrett Winters")); //There are 24 employees


        // iv) The greatest age is 66
        JsonPath json=response.jsonPath();  //response'u jsonpath'e cevirerek jsonpath'in sahip oldugu imkanlara erisicez
        List<Integer> ageList=  json.getList("data.findAll{it.id>0}.employee_age");
        Collections.sort(ageList);
        System.out.println(ageList);
        System.out.println(ageList.get(ageList.size()-1));
        assertEquals(66,(int)ageList.get(ageList.size()-1));

        // The name of the lowest age is "Tatyana Fitzpatrick"

        String groovyString= "data.findAll{it.employee_age == "+ageList.get(0)+"}.employee_name";
        //Concatenation = birlestirme, String ve int ifadeyi birleştirdik ya yukarda. İşte buna concatenation deniyor.
        String minAgeEmployeeName=json.getString(groovyString);
        System.out.println(minAgeEmployeeName);
        assertEquals("[Tatyana Fitzpatrick]",minAgeEmployeeName);

        //Total salary of all employees is 6,644,770
        List<Integer> employeeSalary=  json.getList("data.findAll{it.employee_salary}");
        System.out.println(employeeSalary);

        //  1.YOL
        int sum=0;
        for (int w:employeeSalary) {
            sum+=w;
        }
        assertEquals(6644770,sum);

        //  2.YOL

       int sumLambda= employeeSalary.stream().reduce(0,(t,u) -> t+u) ;
        assertEquals(6644770,sumLambda);

        //  3.YOL
        int sum01= employeeSalary.stream().reduce(0,Math::addExact);
        assertEquals(6644770,sum01);

    }



}
