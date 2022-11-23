package get_requests;

import base_urls.DummyRestApiBaseUrl;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;


public class Get16 extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */
    /*
        Given
           https://dummy.restapiexample.com/api/v1/employees
       When
          User send Get request
      Then
          i) Status code is 200
       And
          ii) There are 24 employees
       And
         iii) "Tiger Nixon" and "Garrett Winters" are among the employees
       And
          iv) The greatest age is 66
       And
           v) The name of the lowest age is "Tatyana Fitzpatrick"
       And
          vi) Total salary of all employees is 6,644,770
     */

    @Test
    public void get01() {
        spec.pathParam("first","employees");
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        //i) Status code is 200
        // ii) There are 24 employees
        // "Tiger Nixon" and "Garrett Winters" are among the employees
        response.then().assertThat().statusCode(200).
                body("data",hasSize(24),
                       "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        //The greatest age is 66
        JsonPath jsonPath=response.jsonPath();
        List<Integer> ages=jsonPath.getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);//List mutable oldugu icin uzerine yapilan degisikler kalici olur heap memory de yeni bir yer acilmaz uzerine yazar string olsaydi yeni yer acar eskisi aynen kalırdı cunku o mutable
        System.out.println("Sorted ages = " + ages);
        System.out.println(ages.get(ages.size()-1));
        assertEquals((Integer)66,ages.get(ages.size()-1));

        //  v) The name of the lowest age is "Tatyana Fitzpatrick"
       List<String> employeNmae=jsonPath.getList("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name");//FindAll {} icinde eleme yapar
        assertEquals("[Tatyana Fitzpatrick]",employeNmae.toString());

        //vi) Total salary of all employees is 6,644,770
        List<Integer> employeeSalarys=jsonPath.getList("data.employee_salary");
        System.out.println("employeeSalarys = " + employeeSalarys);
        //1.yol

        int sum=0;
        for (int w : employeeSalarys) {
            sum+=w;
        }
        System.out.println("sum = " + sum);
        assertEquals(6644770,sum);
        //2.yol
        int salarySum=employeeSalarys.stream().reduce(0,Integer::sum);
        //employeeSalarys.stream().reduce(0,(t,u)->t+u);
        assertEquals(6644770,salarySum);
        //3.yol
        int salarySum1=employeeSalarys.stream().reduce(0,Math::addExact);
        assertEquals(6644770,salarySum1);
    }
}
