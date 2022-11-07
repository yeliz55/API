package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class Get04 extends JsonplaceholderBaseUrl {
    /*
          Given
              https://jsonplaceholder.typicode.com/todos
          When
         I send a GET request to the Url
       And
           Accept type is "application/json"
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           There should be 200 todos
       And
           "quis eius est sint explicabo" should be one of the todos title
       And
           2, 7, and 9 should be among the userIds
       */

    @Test
    public void get01() {
        //1.set URL
        spec.pathParam("first","todos");

        //2.Set The Expected Data(put,patch,post)

        //3.Send The Request And Get The Response
        Response response=given().spec(spec).when().accept(ContentType.JSON).get("/{first}");
       // response.prettyPrint();

        //4.Do Assertion
        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id",hasSize(200),//burada uniqe olan id nin size i uzerinden bulduk cunku uniqe olmazsa size'i dogru alamayiz
                        "title",hasItem("quis eius est sint explicabo"),//bu Item title in herhangi bir yerinde varmi demek
                        "userId",hasItems(2,7,9));//bu Itemlar userId de var ise pass yoksa failed verecek

    }


}
