package deleteRequest;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonplaceholderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {
        //Set The Url
        spec.pathParams("first","todos","second",198);
        //Set The Expected Data
        Map<String,String> expectedData=new HashMap<>();//Bos bir hashmap olusturduk dogrulama yapabilmek icin
        //Send The Request And Get The Response
        Response response=given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        HashMap actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.getStatusCode());

        //1.yol
        assertEquals(expectedData,actualData);

        //2.yol
        assertTrue(actualData.isEmpty());

        //3.yol
        assertEquals(0,actualData.size());

    }
}
