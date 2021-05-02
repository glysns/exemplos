package digytal.java.commons.http;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class Response {
	@ApiModelProperty(value = "A data da ocorrência do erro (ANO-MES-DIA HORA:MINUTO:SEGUNDO)", required = true, example = "2019-01-01 16:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime timestamp = LocalDateTime.now();

	@ApiModelProperty(value = "Status sucesso", example = "success")
	public String status = "success";

	@ApiModelProperty(value = "Retorno da requisição", example = "{id:1, nome:jose}")
	public Object content;

	@ApiModelProperty(value = "Código de status HTTP", example = "200")
	public int statusCode = 200;

	public static Response ok() {
		return ok(null);
	}

	public static Response ok(Object data) {
		Response response = new Response();
		response.content = data;
		return response;
	}
}
