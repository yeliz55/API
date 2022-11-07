package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String,Object> expectedDataMethod(Integer userId,String title,Boolean completed){

        Map<String,Object> expectedDataMap=new HashMap<>();
        if (userId!=null){//UserId null'a esit degilse userId ata
            expectedDataMap.put("userId",userId);
        }
        if (title!=null){
            expectedDataMap.put("title",title);
        }
        if (completed!=null){
            expectedDataMap.put("completed",completed);
        }

        //id biz atamadıgımız her seferinde sistem kendi verdigi icin paremetre olarak yazmadik


        return expectedDataMap;
    }

    public String expectedDataInString(int userId,String title,boolean completed){//Dinamik expected Data Methodu: Json datayi string container olarak return eder.
        String expectedData="{\n" +
                "                 \"userId\": "+userId+",\n" +
                "                 \"title\": \""+title+"\",\n" +
                "                 \"completed\": "+completed+"\n" +
                "               }";
        //++ yaparak strıng icinde concat yapıyoruz biz ne girersek o oluyor boylece
        return expectedData;
    }





}
/*
               {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
 */