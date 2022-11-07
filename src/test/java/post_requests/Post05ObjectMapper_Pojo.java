package post_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.JsonplaceholderPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class Post05ObjectMapper_Pojo extends JsonplaceholderBaseUrl {


      /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }


            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post05ObjectMapperPojo() {
        //Set The Url
        spec.pathParam("first", "todos");

        //Set The Expected Data
        JsonplaceholderPojo expectedData=new JsonplaceholderPojo(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        //Send The Request and Get The Response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion

        JsonplaceholderPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),JsonplaceholderPojo.class);
        System.out.println("actualData = " + actualData);

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),201);
        softAssert.assertEquals(actualData.getUserId(),expectedData.getUserId());
        softAssert.assertEquals(actualData.getCompleted(),expectedData.getCompleted());
        softAssert.assertEquals(actualData.getTitle(),expectedData.getTitle());
        softAssert.assertAll();


    }
}
