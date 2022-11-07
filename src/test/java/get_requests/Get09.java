package get_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get09 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
  {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
  }
 */

    @Test
    public void get01() {
     //   Given
        //Set The Url
        //        https://restful-booker.herokuapp.com/booking/91
        spec.pathParams("first","booking","second",91);

        //Set The Expected Data
        Map<String,String> bookingdatesMap=new HashMap<>();
        bookingdatesMap.put("checkin","2013-02-23");
        bookingdatesMap.put("checkout","2014-10-23");

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstname","Sally");
        expectedData.put("lastname","Brown");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdatesMap);
        expectedData.put("additionalneeds","Breakfast");

        System.out.println("expectedData = " + expectedData);

        //    When
        //        I send GET Request to the url
        Response response=given().spec(spec).when().get("/{first}/{second}");
        Map<String,Object> actualDataMap=response.as(HashMap.class);//Json formatındaki actual datayi map sekline cevirdik expected ile karsılastırabılmek icin
        System.out.println("actualDataMap = " + actualDataMap);

        //    Then
        //        Response body should be like that;
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualDataMap.get("firstname"),expectedData.get("firstname"));
        softAssert.assertEquals(actualDataMap.get("lastname"),expectedData.get("lastname"));
        softAssert.assertEquals(actualDataMap.get("totalprice"),expectedData.get("totalprice"));
        softAssert.assertEquals(actualDataMap.get("depositpaid"),expectedData.get("depositpaid"));
        softAssert.assertEquals(((Map)(actualDataMap.get("bookingdates"))).get("checkin"),bookingdatesMap.get("checkin"));
        softAssert.assertEquals(((Map)(actualDataMap.get("bookingdates"))).get("checkout"),bookingdatesMap.get("checkout"));
        /*
        Key Value ikilileri String-Object seklinde oldugundan
        BookingData value kismini actual kısmında  casting ile map yaptik

        expected da ise biz olusturdugumuz ıcın dırek olusturdugumuz map uzerinden aldik
         */
        softAssert.assertEquals(actualDataMap.get("additionalneeds"),expectedData.get("additionalneeds"));
        softAssert.assertAll();

    }
}
