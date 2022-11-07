package post_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;

public class Post04Pojo extends RestfulBaseUrl {
    /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        },
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */

    @Test
    public void postPojo01() {
        //set the url
        spec.pathParam("first","booking");

        //Set The Expected Data
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo expectedData =new BookingPojo("Ali","Can",999,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);
        //Send The Request And Get The Response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        BookingResponseBodyPojo actualData=response.as(BookingResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);


        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertEquals(actualData.getBooking().getFirstname(),expectedData.getFirstname());
        softAssert.assertEquals(actualData.getBooking().getLastname(),expectedData.getLastname());
        softAssert.assertEquals(actualData.getBooking().getTotalprice(),expectedData.getTotalprice());
        softAssert.assertEquals(actualData.getBooking().getDepositpaid(),expectedData.getDepositpaid());
        softAssert.assertEquals(actualData.getBooking().getBookingdates().getCheckin(),bookingDatesPojo.getCheckin());
        softAssert.assertEquals(actualData.getBooking().getBookingdates().getCheckout(),bookingDatesPojo.getCheckout());
        softAssert.assertEquals(actualData.getBooking().getAdditionalneeds(),expectedData.getAdditionalneeds());
        softAssert.assertAll();

    }
}
