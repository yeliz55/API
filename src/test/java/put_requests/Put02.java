package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDeletePojo;
import pojos.DummyRestApiDatesPojo;
import pojos.DummyRestApiResponsePojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     *//*
Given
    URL: https://dummy.restapiexample.com/api/v1/update/21
     {
                    "employee_name": "Ali Can",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image"
                 }
    When
   User Send PUT Request


   Then
            i) Status code is 200
            And
            ii) Response body should be like the following
                {
                    "status": "success",
                    "data": {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                    },
                    "message": "Successfully! Record has been updated."
                }
 */

    @Test
    public void put01() {
        spec.pathParams("first","update","second",21);
        DummyRestApiDatesPojo dummyRestApiDatesPojo=new DummyRestApiDatesPojo( "Ali Can",111111,23,"Perfect image");

        DummyRestApiResponsePojo expecteddata=new DummyRestApiResponsePojo("success",dummyRestApiDatesPojo,"Successfully! Record has been updated.");
        System.out.println("expecteddata = " + expecteddata);
        Response response=given().spec(spec).contentType(ContentType.JSON).body(dummyRestApiDatesPojo).when().put("/{first}/{second}");
        //dummyRestApiDatesPojo bunu gondermemizin sebebi bu bady icin degisiklik yapıyoruz expected datayi karsılastırma icin olusturduk
        response.prettyPrint();

        DummyRestApiResponsePojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponsePojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.getStatusCode());
        //author
        assertEquals(expecteddata.getStatus(),actualData.getStatus());
        assertEquals(expecteddata.getMessage(),actualData.getMessage());
        //inner
        assertEquals(dummyRestApiDatesPojo.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(dummyRestApiDatesPojo.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(dummyRestApiDatesPojo.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(dummyRestApiDatesPojo.getProfile_image(),actualData.getData().getProfile_image());


    }
}
