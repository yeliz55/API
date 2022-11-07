package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utilities.ObjectMapperUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Map extends JsonplaceholderBaseUrl {
    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void getObjectMapperMap() {
        //Set The Url
        spec.pathParams("first","todos","second",198);
        //Set The expected Data
        JsonPlaceHolderTestData jsonPlaceHolderTestData=new JsonPlaceHolderTestData();//String haline getirmek icin bu classdan obje olusturduk oradaki methodu kullanabılmek icin
        String expectedDataInString=jsonPlaceHolderTestData.expectedDataInString(10,"quis eius est sint explicabo",true);
        HashMap expectedData=ObjectMapperUtils.convertJsonToJava(expectedDataInString, HashMap.class);//String halindeki expected datayi mape cevirdik
        System.out.println("expectedData = " + expectedData);
        //convertJsonToJava==>Stringi mape cevirir

        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        HashMap actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));



    }
}
