package get_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get02b extends ReqresBaseUrl {

    /*
   Given
       https://reqres.in/api/users/23
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Server is "cloudflare"
   And
       Response body should be empty

*/

    @Test
    public void get02() {
        /*Set The Url*/
        //Given
        //       https://reqres.in/api/users/23
        spec.pathParams("first","users","second",23);

        /*Set The Request and Get The Response*/
        //   When
        //       User send a GET Request to the url
       Response response=given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

        /*Do Assertion*/
        //   Then
        //       HTTP Status code should be 404
        /*  response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");*/
        assertEquals(404,response.statusCode());
        System.out.println(response.statusCode());//404
        //   And
        //       Status Line should be HTTP/1.1 404 Not Found
        assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
        //   And
        //       Server is "cloudflare"
        assertEquals("cloudflare",response.getHeader("Server"));
       /* assertEquals("cloudflare",response.header("Server"));*/ //buda ayni sonucu verir
        //   And
        //       Response body should be empty
        assertEquals(0,response.asString().replaceAll("[^A-Za-z0-9]","").length());
        assertEquals(2,response.asString().replaceAll("\\s","").length());//spaceleri yok edince iki { den 2 vermesi lazim lengt
        assertEquals("",response.asString().replaceAll("\\W",""));//spaceleri yok edince iki { den 2 vermesi lazim lengt


    }


}
