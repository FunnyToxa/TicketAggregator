package RESTService.DTO;

import RESTService.DTO.Entries.ResponseEntry;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Описание ответа пользователю на запрос
 * создержит:
 * сообщение - message
 * список поездок - trips
 *
 */
public class RequestResponse {
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ResponseEntry responseEntry;

    public RequestResponse(String error){
        this.success = false;
        this.error = error;
    }

    public RequestResponse(ResponseEntry responseEntry){
        this.success = true;
        this.responseEntry = responseEntry;
    }

    public String getError() {
        return error;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResponseEntry getResponseEntry() {
        return responseEntry;
    }
}
