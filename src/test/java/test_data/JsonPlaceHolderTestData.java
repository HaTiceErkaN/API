package test_data;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
  public   int basariliStatusCode =200;

    public Map<String,Object> expectedDataWithAllKeys(Integer userId, String title, Boolean completed){
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId",userId);
        expectedData.put("title", title);
        expectedData.put("completed",completed);
        return expectedData;
    }

    public JSONObject expectedDataOlustur (){
        JSONObject body = new JSONObject();
        body.put("userId",3);
        body.put("id",3);
        body.put("title","dolor sint quo a velit explicabo quia nam");
        body.put("body","eos qui et ipsum ipsam suscipit aut\n" +
                "sed omnis non odio\n" +
                "expedita earum mollitia molestiae aut atque rem suscipit\n" +
                "nam impedit esse");
        return body;
    }

    public Map<String,Object> requestBodyMapOlustur() {
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("title", "Ahmet");
        requestBodyMap.put("body", "Merhaba");
        requestBodyMap.put("userId", 10);
        requestBodyMap.put("id", 70);
        return requestBodyMap;
    }

    public Map<String ,Object > expectedDatawithMissingKeys(Integer userId, String title, Boolean completed){
        Map<String, Object> expectedData = new HashMap<>();
        if (userId!=null){
            expectedData.put("userId",userId);
        }
        if (title!=null){
            expectedData.put("title",title);
        }
        if (completed!=null){
            expectedData.put("completed",completed);
        }
        return expectedData;
    }
}





