package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestResponseBodyPojo {
     /*
    1)Create private variables for every key
    2)Create Constructor with all parameters and without any parameters
    3)Create getters and setters
    4)Create toString() Method
     */
    // 1)Create private variables for every key
    private Object meta;
    private GoRestDataPojo data;
    //2)Create Constructor with all parameters and without any parameters

    public GoRestResponseBodyPojo(String meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestResponseBodyPojo() {
    }
    //3)Create getters and setters

    public Object getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }
    //4)Create toString() Method

    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta='" + meta + '\'' +
                ", data=" + data +
                '}';
    }
}
