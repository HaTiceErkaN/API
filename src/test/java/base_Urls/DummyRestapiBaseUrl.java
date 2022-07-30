package base_Urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyRestapiBaseUrl {

    protected RequestSpecification spec;


    //Before annotation'ı kullandiğimiz methodlar her test methodundan önce çalişir
    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/api/v1").build();
    }
}
