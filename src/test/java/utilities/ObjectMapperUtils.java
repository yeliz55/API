package utilities;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {
    // new ObjectMapper().readValue(jsonInString, HashMap.class);

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();//yani static blok obje olusumundan once calısacak private etkilemeyecek degistirilmesin diye private yaptik
    }
    //<T> =>Generic method demek yani hangi data dondersin istersek onu donderir ne istiyorsak ona ceviririz yani
    //Generic Method
    public static <T> T convertJsonToJava(String json , Class<T> cls){//Hangi classa dersek ona cevirir

        T javaResult=null;

        try {
          javaResult = mapper.readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    return javaResult;
    }


}
