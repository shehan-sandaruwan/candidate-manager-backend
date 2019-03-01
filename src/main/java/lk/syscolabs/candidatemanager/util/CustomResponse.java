package lk.syscolabs.candidatemanager.util;

public class CustomResponse {
    private int status;
    private String message;
    private Object data;

    public CustomResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
