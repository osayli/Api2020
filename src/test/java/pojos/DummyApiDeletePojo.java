package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyApiDeletePojo {
    // 1)Create private variables for every key
    private String status;
    private String data;
    private String message;
    // 2)Create Constructor with all parameters and without any parameters

    public DummyApiDeletePojo(String status, String data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public DummyApiDeletePojo() {
    }
    //3)Create getters and setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
        return "DummyApiDeletePojo{" +
                "status='" + status + '\'' +
                ", data='" + data + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
