package get_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get04b extends RestfulBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"


 */

    @Test
    public void get04() {
        //  Given
        //        https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        spec.pathParams("first","booking").queryParams("firstname","Almedin","lastname","Alikadic");
        //    When
        //        User sends get request to the URL
        Response response=given().spec(spec).when().get("/{first}");
       // response.prettyPrint();
        //    Then
        //        Status code is 200
        assertEquals(200,response.statusCode());
        //And
        //   Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"
        assertTrue(response.asString().contains("bookingid"));//eger bir tane bile bookingid varsa o kisi orada vardır demektir

    }
}
