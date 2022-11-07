package get_requests;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.GorestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GorestBaseUrl {


    /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
    {
   {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
    }
}
*/

    @Test
    public void get01() {
        //Given
        //       https://gorest.co.in/public/v1/users/2986
        spec.pathParams("first","users","second",2986);
        //   When
        //       User send GET Request to the URL
        Response response=given().spec(spec).when().get("/{first}/{second}");
        //   Then
        //       Status Code should be 200
        assertEquals(200,response.statusCode());

        //   And
        //       Response body should be like
        //    {
        //   {
        //    "meta": null,
        //    "data": {
        //        "id": 2986,
        //        "name": "Navin Talwar",
        //        "email": "navin_talwar@mclaughlin.name",
        //        "gender": "male",
        //        "status": "inactive"
        //    }
        //}
        GorestTestData gorestTestData=new GorestTestData();
        Map<String,String> dataKeyMap=gorestTestData.dataMapMethod("Navin Talwar","navin_talwar@mclaughlin.name","male","inactive");
        Map<String,Object> expectedDataMap=gorestTestData.expectedDataMap(null,dataKeyMap);
        System.out.println("expectedDataMap = " + expectedDataMap);


        Map<String,Object> actualDataMap=new HashMap<>();
        actualDataMap=response.as(HashMap.class);//HashMap class'ina  cevir diyoruz as methodu ile
        System.out.println("actualDataMap = " + actualDataMap);


        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualDataMap.get("meta"),expectedDataMap.get("meta"));
        softAssert.assertEquals(((Map)actualDataMap.get("data")).get("name"),dataKeyMap.get("name"));
        softAssert.assertEquals(((Map)actualDataMap.get("data")).get("email"),dataKeyMap.get("email"));
        softAssert.assertEquals(((Map)actualDataMap.get("data")).get("gender"),dataKeyMap.get("gender"));
        softAssert.assertEquals(((Map)actualDataMap.get("data")).get("status"),dataKeyMap.get("status"));
        softAssert.assertAll();



    }
}
