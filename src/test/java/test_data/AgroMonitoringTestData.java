package test_data;

import java.util.HashMap;
import java.util.Map;

public class AgroMonitoringTestData {

    public float[][][] coordinates={{{-121.1958f, 37.6683f},
                                     {-121.1779f,37.6687f},
                                     { -121.1773f,37.6792f},
                                     { -121.1958f,37.6792f},
                                     {-121.1958f,37.6683f} } };

    public Map<String,Object> geometrySetUp(){
        Map<String,Object> geometry=new HashMap<>();
        geometry.put("coordinates",coordinates);
        geometry.put("type","Polygon");

        return geometry;
    }

    public Map<String,Object> properties=new HashMap<>();

    public Map<String,Object> geo_jsonSetUp(){
        Map<String,Object> geo_json=new HashMap<>();
        geo_json.put("geometry",geometrySetUp());
        geo_json.put("properties",properties);
        geo_json.put("type","Feature");
        return geo_json;
    }

    public float[] center={-121.1867f,37.67385f};

    public Map<String,Object> requestBody(){
        Map<String,Object> requestBody=new HashMap<>();
        requestBody.put("geo_json",geo_jsonSetUp());
        requestBody.put("name","Polygon Sample");
        requestBody.put("area",190.9484);
        requestBody.put("center",center);
        return requestBody;
    }


}
