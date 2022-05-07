package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyApiResponseBodyPojo {
    // 1)Create private variables for every key
    private String status;
    private DummyApiDataPojo data;
    private String message;
    // 2)Create Constructor with all parameters and without any parameters

    public DummyApiResponseBodyPojo(String status, DummyApiDataPojo data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public DummyApiResponseBodyPojo() {
    }
    //3)Create getters and setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyApiDataPojo getData() {
        return data;
    }

    public void setData(DummyApiDataPojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    // 4)Create toString() Method

    @Override
    public String toString() {
        return "DummyApiResponseBodyPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
