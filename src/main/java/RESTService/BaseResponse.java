package RESTService;

public class BaseResponse {
    private final String status;
    private final Integer code;

    public BaseResponse(HttpStatus status) {
        this.status = status.getReasonPhrase();
        this.code = status.value();
    }

    public String getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }
}
