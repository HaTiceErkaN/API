package utils;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

private static ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();
    }


    //1.method json datasini java objesine cevirir (de-serialization)

    public static <T> T  convertJsonToJavaObject(String json, Class<T> cls) { //Generic method= her turlu data tipi ile calisan methodlar
        T javaResult=null;
        try {
            javaResult= mapper.readValue(json,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaResult;
    }


    //2.method java objesini json dataya cevirir (serialization)
}
