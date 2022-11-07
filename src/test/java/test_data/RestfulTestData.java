package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {
    /*
     {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
     */

    public Map<String,String> bookingDatasMethod(String checkin,String checkout){
        Map<String,String> bookingDatesMap=new HashMap<>();
        bookingDatesMap.put("checkin",checkin);
        bookingDatesMap.put("checkout",checkout);

        return bookingDatesMap;
    }

    public Map<String,Object> expectedDatesMethod(String firstname,String lastname,Integer totalprice,Boolean depositpaid,Map<String,String> bookingdates){
        Map<String,Object> expectedDatesMap=new HashMap<>();
        expectedDatesMap.put("firstname",firstname);
        expectedDatesMap.put("lastname",lastname);
        expectedDatesMap.put("totalprice",totalprice);
        expectedDatesMap.put("depositpaid",depositpaid);
        expectedDatesMap.put("bookingdates",bookingdates);
     return  expectedDatesMap;
    }
}
