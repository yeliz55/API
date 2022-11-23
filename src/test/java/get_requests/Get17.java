package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDatesPojo;
import pojos.DummyRestApiResponsePojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {
/*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void get01() {
        spec.pathParams("first","employee","second",1);
        DummyRestApiDatesPojo dummyRestApiDatesPojo=new DummyRestApiDatesPojo("Tiger Nixon",320800,61,"");
        DummyRestApiResponsePojo expectedData=new DummyRestApiResponsePojo("success",dummyRestApiDatesPojo,"Successfully! Record has been fetched.");
        System.out.println("expectedData = " + expectedData);

        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        DummyRestApiResponsePojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());
        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());


    }
}
/*{
        "status": "success",
        "data": {
        "id": 1,
        "employee_name": "Tiger Nixon",
        "employee_salary": 320800,
        "employee_age": 61,
        "profile_image": ""
        },
        "message": "Successfully! Record has been fetched."
        }*/