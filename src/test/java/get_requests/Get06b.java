package get_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get06b extends ReqresBaseUrl {
     /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2
*/

    @Test
    public void get06() {
        // Given
        //          https://reqres.in/api/unknown/
        spec.pathParam("first","unknown");

        //   When
        //        I send GET Request to the URL
        Response response=given().spec(spec).get("/{first}");
        response.prettyPrint();

        //   Then
        //        1)Status code is 200
        response.then().assertThat().statusCode(200);
       /* assertEquals(200,response.statusCode());*/
        //        2)Print all pantone_values
        JsonPath jsonPath=response.jsonPath();
        System.out.println("data.pantone_value= " + jsonPath.getList("data.pantone_value"));


        /*
        artık list oldugu ıcın uzerınde butun lıst ozellıklerını kullanabılırız
        burada jsonpath artık lıste donusuyor
          System.out.println("data.pantone_value= " + jsonPath.getList("data.pantone_value").get(0));//birinciyi verir
         */


        //        3)Print all ids greater than 3 on the console
        System.out.println(jsonPath.getList("data.id"));//idlerin listi
        List<Integer> idList =jsonPath.getList("data.findAll{it.id>3}.id");//grovy language
        System.out.println("^den buyuk idler = "+idList);
        //data.findAll{it.id>3}=id si 3 den buyuk olanların hepsını bul demek
        //data.findAll{it.id>3}.id=id si 3 den buyuk olanların hepsını bul ve id leri getir demek
        //once data ile liste ulasıyoruz cunku datadan sonra list baslıyor.buradaki
        // (it=lambda exp.daki t demek) it her bir { } arasindaki elementleri tektek ifade eder


        //          Assert that there are 3 ids greater than 3
        assertEquals(3,idList.size());

        //        4)Print all names whose ids are less than 3 on the console
        List<String> nameList=jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("nameList = " + nameList);

        //          Assert that the number of names whose ids are less than 3 is 2
        assertEquals(2,nameList.size());


/*
bady ıle de ıslemler yaparız lıst ıcınde ama sadece dogrulama yapabiliriz dısarı alıp ıslem yapamayız bunu sadce json ıle yaparız
bady ıle ıcıne gırerız ama dısarı alamayız datayı ama jsonpath ile datanın ıcıne gırıp o datayi dısarıda alıp kullanabılırız
 */
        response.then().body("data",hasSize(6));//data ya karsılık gelen lıstın ıcınde 6 eleman var dogrulamasini yaptik


    }
}
