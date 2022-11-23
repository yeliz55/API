package pojos;

public class DummyDeletePojo {
    /*
{
    "status": "success",
    "data": "2",
    "message": "Successfully! Record has been deleted"
}
 */
    private String status;
    private String data;
    private String message;

    @Override
    public String toString() {
        return "DummyDeletePojo{" +
                "status='" + status + '\'' +
                ", data='" + data + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

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

    public DummyDeletePojo(String status, String data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public DummyDeletePojo() {
    }
}
