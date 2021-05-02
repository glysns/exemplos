package digytal.java.commons.http;

import io.swagger.annotations.ApiModelProperty;

public class ResponseError extends Response {
    @ApiModelProperty(value = "A mensagem de erro", example = "Não foi possível processar a operação solicitada..")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
