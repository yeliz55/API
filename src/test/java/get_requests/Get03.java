package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 extends JsonplaceholderBaseUrl {
    /*
      Given
          https://jsonplaceholder.typicode.com/todos/23
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
      And
          Response format should be "application/json"
      And
          "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
      And
          "completed" is false
      And
          "userId" is 2
   */


    @Test
    public void get01() {
        //Set The URL
        spec.pathParams("first","todos","second",23);//Base url sonunda todos ve 23  url yok extends ettigimde o yuzden pathParams ile ekleme yaptık

        /*pathParams kullanma sebebimiz bir den fazla url eklentisi olmasi /todos ve /23 ornegin bu url de
       eger tek bir eklenti olsaydi o zaman pathParam kullanacaktik, yani /todos tan sonra baska bir sey olmasaydi mesela. */

         //Expected Data

        //Send The request and Get Response
        Response response=given().spec(spec).when().get("/{first}/{second}");
       // response.prettyPrint(); bu body dakileri yazdirir

        // Do Assert
        /*1.yol Hard Assert*/
        //body icin hata alinca hata aldigi yerde hemen kodu durdurur dıgerlerini calistirmaz
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));

        /*2.yol SoftAssert*/
        //body icinde nerede hata alırsa alsın sıra ıle hepsını calıstırır faıled verse bile devam eder soft assert yaptık
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),"userId",equalTo(2));

        /*
      hard assert : farklı body
      soft assert : aynı body
       */
    }
}
