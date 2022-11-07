package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {
    /*
    1) Postman, manuel API testleri icin kullandik,
    2) Otomasyon testleri icinde Rest Assured Library kullanacagiz
    3) Otomasyon testlerimizi yaparken asagidaki adimlari izleriz;

       a) Gereksinimleri anlamak,
       b) Test Case yaziyoruz,

           i)Test Case yaziminda "Gherkin" dilini kullanacagiz.Bizler yazilim dikline hakim olsakta
           karsimizdaki kisiler hakim olmayabilir ama bu dil ile yazilan testleri anlamakta zorluk cekmeyeceklerdir
           Gherkin dilinde kullanacagimiz keywordler;
           -Given : On kosullar
           -When : Yapilacak aksiyonlar icin(get(),put(),post(),patch() ve delete())
           -Then : Istek yaptiktan sonra (request gonderdikten sonra) dogrulama
           -And  : Coklu islemlerde kullanacagiz

       c) Test kodlarimizi yazmaya baslayacagiz

          i)   Set the URL,
          ii)  Set the expected Data(beklenen datanin olusturulmasi: put,post,patch )
          iii) Type code to send request (Talep gondermek icin kod yazimi)
          iv)  Do Assertion (dogrulama yapmak)

     */

    /*
    Given
            https://restful-booker.herokuapp.com/booking/101
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01() {
         // i)   Set the URL,
        String url="https://restful-booker.herokuapp.com/booking/101";
        //ii)  Set the expected Data(beklenen datanin olusturulmasi: put,post,patch )
        /*Bizden post,put yada patch istenmedigi icin bu case de kullanmayacagiz*/

        //iii) Type code to send request (Talep gondermek icin kod yazimi)
        Response response=given().when().get(url);
        response.prettyPrint();
        // iv)  Do Assertion (dogrulama yapmak)
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");


        //Status kodu konsola yazdiralim
        System.out.println("Status Code : "+response.getStatusCode());

        //Content Type'i konsola yazdiralim
        System.out.println("Content Type : "+response.getContentType());

        //Status Line konsola yazdiralim
        System.out.println("Status Line : "+response.getStatusLine());

        //Header konsolda yazdiralim
        System.out.println("Header : "+response.getHeader("Server"));//header daki server i getirir

        //Headers konsolda yazdiralim
        System.out.println("Headers : "+response.getHeaders());//butun headerlari getirir spesifik birsey secmiyoruz

        //Time konsola yazdiralim
        System.out.println("Time : "+ response.getTime());

    }
}
