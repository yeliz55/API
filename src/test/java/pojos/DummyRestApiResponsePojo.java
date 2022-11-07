package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyRestApiResponsePojo {
    private String status;
    private DummyRestApiDatesPojo data;
    private String message;

    public DummyRestApiResponsePojo(String status, DummyRestApiDatesPojo data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public DummyRestApiResponsePojo() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyRestApiDatesPojo getData() {
        return data;
    }

    public void setData(DummyRestApiDatesPojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyRestApiPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
