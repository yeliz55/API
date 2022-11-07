package post_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonplaceholderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonplaceholderBaseUrl {


       /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201(@JsonIgnoreProperties(ignoreUnknown = true)) bunun ile bunu gormezden geldik
                                    }
     */

    @Test
    public void pojoPost01() {
        //Set The Url
        spec.pathParam("first","todos");
        //Set the Expected Data
        JsonplaceholderPojo expectedData=new JsonplaceholderPojo(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        //Send The Request and Get the Response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do assertion(expected datanın data tipi ne ise actual datanin data tipi ayni olmali ki karsilastirma yapabilelim)
        JsonplaceholderPojo actualData=response.as(JsonplaceholderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());


    }
}
