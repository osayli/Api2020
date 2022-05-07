package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper objectMapper;

    static{
        objectMapper=new ObjectMapper();
    }

    //1. method:This method convert Json data to Java Object(de -serialization)

    public static <T> T convertJsonToJavaObject(String json,Class<T> cls){//Generic Method

        T javaResult=null;

        try {
            javaResult=objectMapper.readValue(json,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return  javaResult;
    }
    //2.method:This method convert Java Object to Json data  (serialization)
     public static String convertJavaObjectToJson(Object obj){
       String jsonResult=null;
         try {
             jsonResult= objectMapper.writeValueAsString(obj);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return jsonResult;
     }
}
