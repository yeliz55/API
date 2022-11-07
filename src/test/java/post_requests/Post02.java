package post_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post02 extends RestfulBaseUrl {
     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/

    @Test
    public void post01() {
        //Set the Url
        spec.pathParam("first","booking");
        //Set The Expected Data
        RestfulTestData obj=new RestfulTestData();
        Map<String,String> bookingDatesMaop=obj.bookingDatasMethod("2021-09-09","2021-09-21");

        Map<String,Object> expectedDataMap=obj.expectedDatesMethod("John","Doe",11111,true,bookingDatesMaop);
        //Set the request And Get The response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();
        //Do assertion
        Map<String,Object> actualDataMap=response.as(HashMap.class);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("firstname"),expectedDataMap.get("firstname"));
        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("lastname"),expectedDataMap.get("lastname"));
        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("totalprice"),expectedDataMap.get("totalprice"));
        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("depositpaid"),expectedDataMap.get("depositpaid"));
        softAssert.assertEquals(((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"),bookingDatesMaop.get("checkin"));
        softAssert.assertEquals(((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"),bookingDatesMaop.get("checkout"));
        softAssert.assertAll();
    }
}
