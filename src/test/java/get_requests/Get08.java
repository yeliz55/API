package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonplaceholderBaseUrl {

    //De-Serialization=>Json datayi Java objesine cevirme
    //Serialization=>Java objesini Json datasina cevirme
    /*
    De-Serialization islemini iki sekilde yapacagiz
      1) Gson; Google tarafindan uretilmistir
      2) Object Mapper; Daha populer!!!
     */





       /*
           Given
              https://jsonplaceholder.typicode.com/todos/2
          When
              I send GET Request to the URL
          Then
              Status code is 200
              And "completed" is false
              And "userId" is 1
              And "title" is "quis ut nam facilis et officia qui"
              And header "Via" is "1.1 vegur"
              And header "Server" is "cloudflare"
              {
                  "userId": 1,
                  "id": 2,
                  "title": "quis ut nam facilis et officia qui",
                  "completed": false
              }
       */

    @Test
    public void get01() {
        //Set The Url
        //   Given
        //              https://jsonplaceholder.typicode.com/todos/2
        spec.pathParams("first","todos","second",2);

        //Set The Expected  Data(expectedData=payload diger adi)
        Map<String,Object> expectedData=new HashMap<>();//haspmap yaptıgımız ıcın hızlı ama sırasız gelır ekledıklerımız
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        System.out.println("expectedData = " + expectedData);


        //Send The Request and Get The Response
        //          When
        //              I send GET Request to the URL
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData=response.as(HashMap.class);//De-Serialization yani json i java ya cevirdik
        System.out.println("actualData = " + actualData);
        //          Then
        //              Status code is 200
        //              And "completed" is false
        //              And "userId" is 1
        //              And "title" is "quis ut nam facilis et officia qui"
        //              And header "Via" is "1.1 vegur"
        //              And header "Server" is "cloudflare"


        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));//bunu yapmamiza gerek yok cunku sıstem kendi atar
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));


        assertEquals("1.1 vegur",response.getHeader("Via"));
        assertEquals("cloudflare",response.getHeader("Server"));
        assertEquals(200,response.statusCode());

    }


             /*
             Dinamik yontem
              */
    @Test
    public void get01b() {
        //Set The Url
        //   Given
        //              https://jsonplaceholder.typicode.com/todos/2
        spec.pathParams("first","todos","second",2);

        //Set The Expected  Data(expectedData=payload diger adi)
        JsonPlaceHolderTestData objJsonPlcHldr=new JsonPlaceHolderTestData();
        Map<String,Object> expectedData=objJsonPlcHldr.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);
        System.out.println("expectedData = " + expectedData);
       /*
  Test data classindan map olusturmak ıcın once obje olusturduk ve oradaki paremetreleri girerek
  map olusturmus olduk ve bununla yukarıda yapdıgımız ıslemlerı daha dınamaık yapmıs olduk
       */

        //Send The Request and Get The Response
        //          When
        //              I send GET Request to the URL
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData=response.as(HashMap.class);//De-Serialization yani json i java ya cevirdik
        System.out.println("actualData = " + actualData);
        //          Then
        //              Status code is 200
        //              And "completed" is false
        //              And "userId" is 1
        //              And "title" is "quis ut nam facilis et officia qui"
        //              And header "Via" is "1.1 vegur"
        //              And header "Server" is "cloudflare"


        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));//bunu yapmamiza gerek yok cunku sıstem kendi atar
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));


        assertEquals("1.1 vegur",response.getHeader("Via"));
        assertEquals("cloudflare",response.getHeader("Server"));
        assertEquals(200,response.statusCode());

    }
}
