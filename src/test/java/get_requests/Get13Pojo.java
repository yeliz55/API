package get_requests;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.GorestDataPojo;
import pojos.GorestPojo;

import static io.restassured.RestAssured.given;

public class Get13Pojo extends GorestBaseUrl {
         /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void pojo01() {
        //Set The Url
        spec.pathParams("first","users","second",2508);
        //Set The Expected Data
        GorestDataPojo gorestDataPojo=new GorestDataPojo(2508,"Sharmila Deshpande VM","deshpande_sharmila_vm@becker.name","female","active");
        GorestPojo expectedData=new GorestPojo(null,gorestDataPojo);
        System.out.println("expectedData = " + expectedData);


        //Send The Request And Get The Response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //Do Assertions
       GorestPojo actualData=response.as(GorestPojo.class);
        System.out.println("actualData = " + actualData);

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualData.getMeta(),expectedData.getMeta());
        softAssert.assertEquals(actualData.getData().getId(),gorestDataPojo.getId());
        softAssert.assertEquals(actualData.getData().getName(),gorestDataPojo.getName());
        softAssert.assertEquals(actualData.getData().getEmail(),gorestDataPojo.getEmail());
        softAssert.assertEquals(actualData.getData().getGender(),gorestDataPojo.getGender());
        softAssert.assertEquals(actualData.getData().getStatus(),gorestDataPojo.getStatus());
        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertAll();

    }
}
