package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RestfulBaseUrl  {

    protected RequestSpecification spec;//interface oldugu ıcın burada cons olarak kullanılamıyor

    @Before

    public void setup(){
        spec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }

}
