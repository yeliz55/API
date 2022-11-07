package get_requests;

import base_urls.AutomationexerciseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetHomework extends AutomationexerciseUrl {

          /*
      Given
          https://automationexercise.com/api/productsList
      When
          User sends a GET Request to the url
      Then
          HTTP Status Code should be 200
      And
          Content Type should be "text/html; charset=utf-8"
      And
          Status Line should be HTTP/1.1 200 OK
      And
           There must be 12 Women, 9 Men, 13 Kids usertype in products
        */

    @Test
    public void homework() {
        //   Given
        //          https://automationexercise.com/api/productsList
        spec.pathParams("first","api","second","productsList");
        //      When
        //          User sends a GET Request to the url
        Response response=given().spec(spec).when().get("/{first}/{second}");
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();
        //      Then
        //          HTTP Status Code should be 200
        assertEquals(200,response.statusCode());

        //      And
        //          Content Type should be "text/html; charset=utf-8"
        assertEquals("text/html; charset=utf-8",response.contentType());
        //      And
        //          Status Line should be HTTP/1.1 200 OK
        assertEquals("HTTP/1.1 200 OK",response.statusLine());

        //      And
        //           There must be 12 Women, 9 Men, 13 Kids usertype in products

        List<String> usertypeWomen=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        System.out.println("idWomen.size() = " + usertypeWomen.size());
        List<String> usertypeMen=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        System.out.println("idWomen.size() = " + usertypeMen.size());
        List<String> usertypeKids=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        System.out.println("idWomen.size() = " + usertypeKids.size());




    }
}
