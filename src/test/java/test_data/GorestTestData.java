package test_data;

import java.util.HashMap;
import java.util.Map;

public class GorestTestData {

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
    public Map<String,String> dataMapMethod(String name,String email,String gender,String status){
       Map<String,String> dataMap=new HashMap<>();
       dataMap.put("name",name);
       dataMap.put("email",email);
       dataMap.put("gender",gender);
       dataMap.put("status",status);
      return dataMap;
    }

    public Map<String,Object> expectedDataMap(Object meta,Map<String,String> data){
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("meta",meta);
        expectedData.put("data",data);

        return expectedData;
    }

}
