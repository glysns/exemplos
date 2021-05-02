package digytal.java.infra.exception;

import digytal.java.infra.exception.config.BusinessException;
import digytal.java.infra.exception.config.Erro;

public class CampoObrigatorioException extends BusinessException {
	public CampoObrigatorioException(String login) {
		super(Erro.CAMPO_OBRIGATORIO,login);
	}
}
