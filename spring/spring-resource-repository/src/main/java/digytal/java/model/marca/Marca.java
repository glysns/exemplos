package digytal.java.model.marca;

import digytal.java.commons.Dto;
import io.swagger.annotations.ApiModelProperty;

public class Marca implements Dto{
	@ApiModelProperty(name = "Id da marca", example = "1")
	public Integer id;
	@ApiModelProperty(name = "Nome da marca", example = "DELL COMPUTADORES")
	public String nome;
	@ApiModelProperty(name = "Se a marca está ativa", example = "true")
	public boolean ativo;
	
}
